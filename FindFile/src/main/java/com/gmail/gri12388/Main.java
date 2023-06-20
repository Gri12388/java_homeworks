package com.gmail.gri12388;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(Texts.APP_IS_ON);

        String messageForDir = Texts.WRIGHT_DIR + System.lineSeparator() + Texts.WRIGHT_SPACE_FOR_EXIT;
        Optional<Path> dirPath = FileUtils.getPathForDir(messageForDir);
        if (dirPath.isEmpty()) {
            System.out.println(Texts.APP_IS_OVER);
            return;
        }

        String messageForFile = Texts.WRIGHT_FILE + System.lineSeparator() + Texts.WRIGHT_SPACE_FOR_EXIT;
        Optional<String> fileName = FileUtils.getFileName(messageForFile);
        if (fileName.isEmpty()) {
            System.out.println(Texts.APP_IS_OVER);
            return;
        }

        Optional<String> absPath = FileUtils.findFile(dirPath.get().toFile(), fileName.get());

        if (absPath.isPresent()) System.out.println(absPath.get());
        else System.out.println(Texts.FILE_NOT_FOUND);

        System.out.println(Texts.APP_IS_OVER);
    }
}
