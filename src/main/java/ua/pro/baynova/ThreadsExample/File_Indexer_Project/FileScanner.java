package ua.pro.baynova.ThreadsExample.File_Indexer_Project;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileScanner {
    private static final Logger logger = Logger.getLogger(FileScanner.class.getName());

    private final Predicate<Path> fileFilter;
    private final boolean followSymlinks;

    public FileScanner() {
        this(path -> true, false);
    }

    public FileScanner(Predicate<Path> fileFilter, boolean followSymlinks){
        this.fileFilter = fileFilter;
        this.followSymlinks = followSymlinks;
    }

    public List<FileTask> scanDirectory(String directoryPath) throws IOException {
        List<FileTask> fileTasks = new ArrayList<>();
        Path startPath = Paths.get(directoryPath);

        if (!Files.exists(startPath)) {
            throw new IOException("Directory does not exist: " + directoryPath);
        }

        if (!Files.isDirectory(startPath)) {
            throw new IOException("Path is not a directory: " + directoryPath);
        }

        logger.info(String.format("Starting scan of directory: %s [Thread: %s]",
                directoryPath, Thread.currentThread().getName()));

        Files.walkFileTree(startPath, getFileVisitOptions(), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (attrs.isRegularFile() && fileFilter.test(file)) {
                    LocalDateTime lastModified = LocalDateTime.ofInstant(
                            attrs.lastModifiedTime().toInstant(),
                            ZoneId.systemDefault()
                    );

                    FileTask task = new FileTask(
                            file.toAbsolutePath().toString(),
                            attrs.size(),
                            lastModified
                    );

                    fileTasks.add(task);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                logger.log(Level.WARNING, "Failed to visit file: " + file, exc);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                logger.fine("Entering directory: " + dir + " [Thread: " + Thread.currentThread().getName() + "]");
                return FileVisitResult.CONTINUE;
            }
        });

        logger.info(String.format("Scan completed. Found %d files in %s [Thread: %s]",
                fileTasks.size(), directoryPath, Thread.currentThread().getName()));
        return fileTasks;
    }

    public void scanDirectoryAsync(String directoryPath, BlockingQueue<FileTask> taskQueue) throws IOException {
        Path startPath = Paths.get(directoryPath);

        if (!Files.exists(startPath)) {
            throw new IOException("Directory does not exist: " + directoryPath);
        }

        logger.info(String.format("Starting async scan of directory: %s [Thread: %s]",
                directoryPath, Thread.currentThread().getName()));

        Files.walkFileTree(startPath, getFileVisitOptions(), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (attrs.isRegularFile() && fileFilter.test(file)) {
                    LocalDateTime lastModified = LocalDateTime.ofInstant(
                            attrs.lastModifiedTime().toInstant(),
                            ZoneId.systemDefault()
                    );

                    FileTask task = new FileTask(
                            file.toAbsolutePath().toString(),
                            attrs.size(),
                            lastModified
                    );

                    try {
                        taskQueue.put(task);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return FileVisitResult.TERMINATE;
                    }
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                logger.log(Level.WARNING, "Failed to visit file: " + file, exc);
                return FileVisitResult.CONTINUE;
            }
        });

        logger.info(String.format("Async scan completed for: %s [Thread: %s]",
                directoryPath, Thread.currentThread().getName()));
    }

    private Set<FileVisitOption> getFileVisitOptions() {
        return followSymlinks ? Set.of(FileVisitOption.FOLLOW_LINKS) : Set.of();
    }

    public static class FileTask {
        private final String path;
        private final long size;
        private final LocalDateTime lastModified;

        public FileTask(String path, long size, LocalDateTime lastModified) {
            this.path = path;
            this.size = size;
            this.lastModified = lastModified;
        }

        public String getPath() { return path; }
        public long getSize() { return size; }
        public LocalDateTime getLastModified() { return lastModified; }

        @Override
        public String toString() {
            return String.format("FileTask{path='%s', size=%d}", path, size);
        }
    }

    public static class Filters {
        public static Predicate<Path> byExtension(String... extensions) {
            Set<String> extSet = Set.of(extensions);
            return path -> {
                String fileName = path.getFileName().toString().toLowerCase();
                return extSet.stream().anyMatch(fileName::endsWith);
            };
        }

        public static Predicate<Path> minSize(long minBytes) {
            return path -> {
                try {
                    return Files.size(path) >= minBytes;
                } catch (IOException e) {
                    return false;
                }
            };
        }

        public static Predicate<Path> excludeHidden() {
            return path -> !path.getFileName().toString().startsWith(".");
        }
    }
}
