package ua.pro.baynova.ThreadsExample.File_Indexer_Project;

import java.util.List;
import java.util.Optional;

public interface FileIndex {

    void addOrUpdate(FileInfo fileInfo);

    Optional<FileInfo> getByPath(String path);

    boolean remove(String path);

    List<FileInfo> getAllFiles();

    List<List<FileInfo>> findDuplicates();

    int size();

    void clear();

    boolean contains(String path);
}
