package com.itau.ioapi.controller;

import com.itau.ioapi.model.Pessoa;
import com.itau.ioapi.service.PessoaFileSystemService;
import com.itau.ioapi.service.SignosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mapa")
@RequiredArgsConstructor
public class MapaController {
    private static final String HOME_DIR = System.getProperty("user.dir");

    private final SignosService signosService;
    private final PessoaFileSystemService pessoaFileSystemService;

    @PostMapping
    public ResponseEntity<Void> getMapaAstral(@RequestBody MultipartFile file) throws IOException {
        Path tempPath = Path.of(HOME_DIR, System.currentTimeMillis() + ".txt");

        pessoaFileSystemService.serialize(file.getBytes(), tempPath);

        List<Pessoa> pessoaList = pessoaFileSystemService.desserialize(tempPath);

        var p = pessoaList.stream()
                .map(signosService::getInformacoesSignosEmString)
                .map(pessoaslist -> pessoaslist
                        .stream()
                        .map(pessoa -> pessoa.getBytes())
                        .collect(Collectors.toList())
                )
//                .map(list1 -> list1.stream().reduce((acc, el) -> new Byte[]{acc,el}))
                .collect(Collectors.toList());

        return null;
    }

}
