package co.id.gooddoctor.gundala.domain.similarity.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/skuCheck")
public class SimilarityController {

    @PostMapping
    public void similarityProcess(final BigDecimal threshold,
                                  final File currentFile,
                                  final File databaseDrugsFile) {

    }
}
