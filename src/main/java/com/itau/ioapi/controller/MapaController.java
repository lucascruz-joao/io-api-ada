package com.itau.ioapi.controller;

import ch.qos.logback.core.util.FileUtil;
import com.itau.ioapi.model.Pessoa;
import com.itau.ioapi.service.MapaService;
import com.itau.ioapi.service.PessoaFileSystemService;
import com.itau.ioapi.service.SignosService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/api/mapa")
@RequiredArgsConstructor
public class MapaController {
    private final MapaService mapaService;

    @PostMapping(produces = "application/zip")
    public ResponseEntity<byte[]> getMapaAstral(@RequestBody MultipartFile file) throws IOException {

        var baos = mapaService.generateOutputStream(file);

        return ResponseEntity.ok(baos.toByteArray());
    }

}
