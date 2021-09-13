package io.oseven.strangefs;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class FilesController {

    private FileService fileService;

    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public List<String> list() throws IOException {
        return fileService.list();
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<FileAndDigest> getFile(@PathVariable String fileName) throws IOException {
        
        Optional<FileAndDigest> o = fileService.load(fileName);
        if(o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
