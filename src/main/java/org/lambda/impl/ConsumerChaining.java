package org.lambda.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.function.Consumer;

public class ConsumerChaining {

    private static final Path LOG_FILE = Path.of("./logs/logger.txt");

    public static void main(String[] args) {
        Consumer<String> outLogger = System.out::println;
        Consumer<String> errorLogger = System.err::println;
        Consumer<String> fileLogger = str -> {
            try {
                Files.writeString(LOG_FILE, str, StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Consumer<String> chain = outLogger.andThen(errorLogger).andThen(fileLogger);
        chain.accept("I'm Logging Text Into Consumer Chain.");
    }
}
