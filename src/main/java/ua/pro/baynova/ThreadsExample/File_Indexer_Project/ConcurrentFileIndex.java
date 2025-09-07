package ua.pro.baynova.ThreadsExample.File_Indexer_Project;

import ua.pro.baynova.ThreadsExample.File_Indexer_Project.FileInfo;
import ua.pro.baynova.ThreadsExample.File_Indexer_Project.FileIndex;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class ConcurrentFileIndex implements FileIndex {

    private final ConcurrentHashMap<String, FileInfo> pathIndex;

    private final ConcurrentHashMap<String, Set<String>> hashIndex;

    private final ReentrantReadWriteLock lock;

    public ConcurrentFileIndex() {
        this.pathIndex = new ConcurrentHashMap<>();
        this.hashIndex = new ConcurrentHashMap<>();
        this.lock = new ReentrantReadWriteLock();
    }

    @Override
    public void addOrUpdate(FileInfo fileInfo) {
        lock.writeLock().lock();
        try {
            FileInfo oldFileInfo = pathIndex.get(fileInfo.getPath());
            if (oldFileInfo != null) {
                removeFromHashIndex(oldFileInfo.getHash(), oldFileInfo.getPath());
            }

            pathIndex.put(fileInfo.getPath(), fileInfo);
            addToHashIndex(fileInfo.getHash(), fileInfo.getPath());

        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Optional<FileInfo> getByPath(String path) {
        return Optional.ofNullable(pathIndex.get(path));
    }

    @Override
    public boolean remove(String path) {
        lock.writeLock().lock();
        try {
            FileInfo fileInfo = pathIndex.remove(path);
            if (fileInfo != null) {
                removeFromHashIndex(fileInfo.getHash(), path);
                return true;
            }
            return false;
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<FileInfo> getAllFiles() {
        return new ArrayList<>(pathIndex.values());
    }

    @Override
    public List<List<FileInfo>> findDuplicates() {
        lock.readLock().lock();
        try {
            return hashIndex.entrySet().stream()
                    .filter(entry -> entry.getValue().size() > 1)
                    .map(entry -> entry.getValue().stream()
                            .map(pathIndex::get)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList()))
                    .filter(list -> list.size() > 1)
                    .collect(Collectors.toList());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public int size() {
        return pathIndex.size();
    }

    @Override
    public void clear() {
        lock.writeLock().lock();
        try {
            pathIndex.clear();
            hashIndex.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean contains(String path) {
        return pathIndex.containsKey(path);
    }

    private void addToHashIndex(String hash, String path) {
        hashIndex.computeIfAbsent(hash, k -> ConcurrentHashMap.newKeySet()).add(path);
    }

    private void removeFromHashIndex(String hash, String path) {
        Set<String> paths = hashIndex.get(hash);
        if (paths != null) {
            paths.remove(path);
            if (paths.isEmpty()) {
                hashIndex.remove(hash);
            }
        }
    }

    public String getStatistics() {
        lock.readLock().lock();
        try {
            long totalSize = pathIndex.values().stream()
                    .mapToLong(FileInfo::getSize)
                    .sum();

            int duplicateGroups = (int) hashIndex.values().stream()
                    .filter(paths -> paths.size() > 1)
                    .count();

            return String.format("Files: %d, Total size: %d bytes, Duplicate groups: %d",
                    pathIndex.size(), totalSize, duplicateGroups);
        } finally {
            lock.readLock().unlock();
        }
    }
}
