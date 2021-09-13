package io.oseven.strangefs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

public class FileService {

    private static Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    private Path rootDir;

    private String secret;

    public FileService(Path rootDir, String secret) {
        this.rootDir = rootDir;
        this.secret = secret;
    }

    public List<String> list() throws IOException {
        return Files.list(rootDir).filter(Files::isRegularFile).map(Path::toFile).map(File::getName)
                .collect(Collectors.toList());
    }

    public Optional<FileAndDigest> load(String fileName) throws IOException {
        
        Path p = Paths.get(rootDir.toString(), fileName);

        if(!Files.exists(p)){
            return Optional.empty();
        }

        String content = new String(Files.readAllBytes(p));
        byte[] digest = DigestUtils.sha256(String.join(":", content, secret));
        return Optional.of(new FileAndDigest(content, Base64Utils.encodeToString(digest)));
    }

}
