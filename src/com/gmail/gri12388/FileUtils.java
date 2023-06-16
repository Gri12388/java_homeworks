package com.gmail.gri12388;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public final class FileUtils {
    private static void copyFile (Path what, Path newDirPath, File copiedFile) {
        try {
            Files.copy(what, newDirPath);
            if (copiedFile.isDirectory()) {
                Arrays.stream(copiedFile.listFiles()).forEach(item -> tryCopyFile(Path.of(item.toString()), newDirPath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteFile(File file) {
        if (file.isFile()) file.delete();
        else {
            File[] fileList = file.listFiles();
            if (fileList.length > 0) Arrays.stream(fileList).forEach(item -> deleteFile(item));
            file.delete();
        }
    }

    private static boolean getIsNeedToDeleteFile (File file) {
        Scanner scanner = new Scanner(System.in);
        String message = file.isFile()
                ? Texts.IS_NEED_TO_DELETE_FILE.formatted(file.getAbsoluteFile())
                : Texts.IS_NEED_TO_DELETE_DIR.formatted(file.getAbsoluteFile());
        do {
            System.out.println(message);
            String line = scanner.nextLine().trim();
            if (line.equals("0")) return false;
            if (line.equals("1")) return true;
            System.out.println(Texts.NOT_PARSED);
        } while (true);
    }

    public static Optional<Path> getPath (String message, Optional<String> srcDirPath) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(message);
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) return Optional.empty();
            Path path = Path.of(line);
            File file = path.toFile();

            if (file.exists()) {
                if (file.isDirectory()) {
                    if (srcDirPath.isEmpty() || !path.toAbsolutePath().startsWith(srcDirPath.get())) return Optional.of(path);
                    else System.out.println(Texts.SRC_AND_DEST_ARE_EQUALS);
                }
                else System.out.println(Texts.FILE_NOT_DIR);
            } else System.out.println(Texts.FILE_NOT_FOUND);
        } while (true);
    }

    public static void tryCopyFile(Path what, Path where) {
        if (where.toFile().isFile()) return;

        File copiedFile = what.toFile();
        String copiedFileName = copiedFile.getName();
        String destDirPath = where.toString();
        Path newDirPath = Path.of(destDirPath, copiedFileName);
        File newDir = newDirPath.toFile();

        if (newDir.exists()) {
            boolean isNeedToDeleteFile = getIsNeedToDeleteFile(newDir);
            if (isNeedToDeleteFile) {
                deleteFile(newDir);
                copyFile(what, newDirPath, copiedFile);
            }
            else System.out.println(Texts.CANCEL_COPY);
        } else copyFile(what, newDirPath, copiedFile);
    }

    private FileUtils() {}
}
