package ua.pro.baynova.ThreadsExample.File_Indexer_Project;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

public class HashCalculator implements Callable<String> {
    private final File file;
    private final String algorithm;

    public HashCalculator(File file, String algorithm) {
        this.file = file;
        this.algorithm = algorithm;
    }

    public HashCalculator(File file){
        this(file, "MD5");
    }

    @Override
    public String call() throws Exception {
        System.out.printf("[%s] 🔄 Начинаю хеширование: %s (размер: %d байт)%n",
                Thread.currentThread().getName(),
                file.getName(),
                file.length());

        long startTime = System.currentTimeMillis();

        try {
            String hash = calculateFileHash();

            long endTime = System.currentTimeMillis();
            System.out.printf("[%s] ✅ Завершено хеширование: %s -> %s... (время: %d мс)%n",
                    Thread.currentThread().getName(),
                    file.getName(),
                    hash.substring(0, 8),
                    endTime - startTime);

            return hash;
        } catch (Exception e){
            System.out.printf("[%s] ❌ Ошибка хеширования файла %s: %s%n",
                    file.getName(),
                    e.getMessage());

            throw e;
        }
    }

    private String calculateFileHash() throws Exception{
        MessageDigest digest = MessageDigest.getInstance(algorithm);

        try (FileInputStream fis = new FileInputStream(file)){
            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) == -1){
                digest.update(buffer, 0, bytesRead);
            }
        }

        byte[] hashBytes = digest.digest();
        return bytesToHex(hashBytes);
    }

    private String bytesToHex(byte[] bytes){
        StringBuilder result = new StringBuilder();

        for (byte b : bytes){
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public static String calculateHash(File file, String algorithm) throws Exception {
        HashCalculator calculator = new HashCalculator(file, algorithm);
        return calculator.call();
    }

    public static String calculateMD5(File file) throws Exception {
        return calculateHash(file,"MD5");
    }

    public File getFile() {
        return file;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    @Override
    public String toString(){
        return String.format("HashCalculator{file='%s', algorithm='%s'}",
                file.getName(), algorithm);
    }
}
