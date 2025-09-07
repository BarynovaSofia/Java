package ua.pro.baynova.ThreadsExample.File_Indexer_Project;

import java.time.LocalDateTime;
import java.util.Objects;

public class FileInfo {
    private final String path;
    private final String hash;
    private final long size;
    private final LocalDateTime lastModified;
    private final LocalDateTime indexedAt;

    public FileInfo(String path, String hash, long size, LocalDateTime lastModified, LocalDateTime indexedAt) {
        this.path = path;
        this.hash = hash;
        this.size = size;
        this.lastModified = lastModified;
        this.indexedAt = indexedAt;
    }

    public String getPath() {
        return path;
    }

    public String getHash() {
        return hash;
    }

    public long getSize() {
        return size;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public LocalDateTime getIndexedAt() {
        return indexedAt;
    }

    public boolean needsReindexing(LocalDateTime currentLastModified) {
        return currentLastModified.isAfter(this.lastModified);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileInfo fileInfo = (FileInfo) o;
        return size == fileInfo.size &&
                Objects.equals(path, fileInfo.path) &&
                Objects.equals(hash, fileInfo.hash) &&
                Objects.equals(lastModified, fileInfo.lastModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, hash, size, lastModified);
    }

    @Override
    public String toString() {
        return String.format("FileInfo{path='%s', hash='%s', size=%d, lastModified=%s, indexedAt=%s}",
                path, hash, size, lastModified, indexedAt);
    }
}