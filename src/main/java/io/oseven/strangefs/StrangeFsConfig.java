package io.oseven.strangefs;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StrangeFsConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    @Value("${root}")
    public String root;

    @Value("${secret}")
    public String secret;

    @Bean
    public FileService fileService() {

        if (secret == null || secret.isEmpty()) {
            LOGGER.error("Secret not set");
            throw new IllegalStateException("Secret not set");
        }

        Path rootDir = Paths.get(root);
        if (!(Files.exists(rootDir) && Files.isDirectory(rootDir, LinkOption.NOFOLLOW_LINKS))) {
            LOGGER.error("Path not properly set, {}", root);
            throw new IllegalStateException("Path not properly set");
        }
        return new FileService(rootDir, secret);
    }

}