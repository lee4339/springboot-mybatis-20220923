package com.boot.mybatis20220923.controller.api;

import com.boot.mybatis20220923.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RequestMapping("/download")
@RestController
public class DownloadController {

    @Value("${file.path}")
    private String filePath;

    @GetMapping("/news")
    public ResponseEntity<?> download(@RequestParam String originFileName, @RequestParam String tempFileName) throws IOException {

        Path path = Paths.get(filePath + "news/" + tempFileName);
        String contentType = Files.probeContentType(path);   //  probeContentType  --> MIME 형식으로 바꿔줌
        log.info("ContentType: {}", contentType);
        // MIME 형식 파일주소

        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(originFileName, StandardCharsets.UTF_8)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        // 다운로드 응답

        Resource resource = new InputStreamResource(Files.newInputStream(path));  // 외부데이터를 inputStream으로 입력

        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
