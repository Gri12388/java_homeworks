package com.gmail.gri12388;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public final class FileUtils {
    public static Optional<Path> getPathForDir (String message) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(message);
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) return Optional.empty();
            Path path = Path.of(line);
            File file = path.toFile();

            if (file.exists()) {
                if (file.isDirectory()) {
                    return Optional.of(path);
                }
                else System.out.println(Texts.FILE_NOT_DIR);
            } else System.out.println(Texts.FILE_NOT_FOUND);
        } while (true);
    }

    public static Optional<String> getFileName (String message) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(message);
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) return Optional.empty();
            else return Optional.of(line);
        } while (true);
    }

    public static Optional<String> findFile(File dir, String fileName) {
        if (dir.isFile()) {
            if (dir.getName().equals(fileName)) return Optional.of(dir.getAbsolutePath());
            else return Optional.empty();
        }
        else {
            File[] files = dir.listFiles();
            for( File file : files) {
                Optional<String> absPath = findFile(file, fileName);
                if (absPath.isPresent()) return Optional.of(absPath.get());
            }
            return Optional.empty();
        }
    }

    private FileUtils() {}
}
