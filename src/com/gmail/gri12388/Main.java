package com.gmail.gri12388;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(Texts.APP_IS_ON);

        String messageForSource = Texts.WRIGHT_DIR + System.lineSeparator() + Texts.WRIGHT_SPACE_FOR_EXIT;
        Optional<Path> srcPath = FileUtils.getPath(messageForSource, Optional.empty());
        if (srcPath.isEmpty()) {
            System.out.println(Texts.APP_IS_OVER);
            return;
        }

        String messageForDestination = Texts.WRIGHT_DEST_DIR + System.lineSeparator() + Texts.WRIGHT_SPACE_FOR_EXIT;
        Optional<Path> destPath = FileUtils.getPath(messageForDestination, Optional.of(srcPath.get().toAbsolutePath().toString()));
        if (destPath.isEmpty()) {
            System.out.println(Texts.APP_IS_OVER);
            return;
        }

        FileUtils.tryCopyFile(srcPath.get(), destPath.get());

        System.out.println(Texts.APP_IS_OVER);
    }
}
