package ua.pro.baynova.ThreadsExample.File_Indexer_Project;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class BasicTest {

    private static final Logger logger = Logger.getLogger(BasicTest.class.getName());

    public static void main(String[] args) {
        System.out.println("=== File Indexer Basic Test ===\n");

        testFileIndex();
        testFileScanner();
    }

    private static void testFileIndex() {
        System.out.println("--- Test 1: FileIndex functionality ---");

        FileIndex index = new ConcurrentFileIndex();

        FileInfo file1 = new FileInfo(
                "/test/file1.txt",
                "hash123",
                1000,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now()
        );

        FileInfo file2 = new FileInfo(
                "/test/file2.txt",
                "hash456",
                2000,
                LocalDateTime.now().minusDays(2),
                LocalDateTime.now()
        );

        FileInfo duplicate = new FileInfo(
                "/test/duplicate.txt",
                "hash123",
                1000,
                LocalDateTime.now().minusDays(3),
                LocalDateTime.now()
        );

        index.addOrUpdate(file1);
        index.addOrUpdate(file2);
        index.addOrUpdate(duplicate);

        System.out.println("Added 3 files to index");
        System.out.println("Index size: " + index.size());

        System.out.println("Finding file1: " + index.getByPath("/test/file1.txt").isPresent());

        List<List<FileInfo>> duplicates = index.findDuplicates();
        System.out.println("Found duplicate groups: " + duplicates.size());
        if (!duplicates.isEmpty()) {
            System.out.println("First duplicate group size: " + duplicates.get(0).size());
        }

        if (index instanceof ConcurrentFileIndex) {
            System.out.println("Statistics: " + ((ConcurrentFileIndex) index).getStatistics());
        }

        System.out.println("✓ FileIndex test passed\n");
    }

    private static void testFileScanner() {
        System.out.println("--- Test 2: FileScanner functionality ---");

        FileScanner scanner = new FileScanner();

        String[] testDirectories = {
                ".", System.getProperty("user.home"),
                "/tmp", "C:\\temp"
        };

        for (String dir : testDirectories) {
            try {
                System.out.println("Trying to scan directory: " + dir);
                List<FileScanner.FileTask> files = scanner.scanDirectory(dir);

                System.out.printf("✓ Successfully scanned '%s' - found %d files%n", dir, files.size());

                files.stream()
                        .limit(5)
                        .forEach(file -> System.out.println("  - " + file));

                if (files.size() > 5) {
                    System.out.println("  ... and " + (files.size() - 5) + " more files");
                }
                break;

            } catch (IOException e) {
                System.out.println("✗ Failed to scan '" + dir + "': " + e.getMessage());
            }
        }
        System.out.println("\n✓ FileScanner test completed");
    }

    private static void testFiltersExample() {
        System.out.println("--- Filter Example ---");

        FileScanner filteredScanner = new FileScanner(
                FileScanner.Filters.byExtension(".txt", ".java")
                        .and(FileScanner.Filters.minSize(1024))
                        .and(FileScanner.Filters.excludeHidden()),
                false
        );

        try {
            List<FileScanner.FileTask> files = filteredScanner.scanDirectory(".");
            System.out.printf("Found %d filtered files%n", files.size());
        } catch (IOException e) {
            System.out.println("Filter test failed: " + e.getMessage());
        }
    }
}
