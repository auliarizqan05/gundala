package co.id.gundala.domain.similarity.controller;

import co.id.gundala.domain.similarity.service.SimilarityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/skuCheck")
public class SimilarityController {


    @Autowired
    SimilarityService similarityService;

    @PostMapping
    public void similarityProcess(@RequestParam("threshold") BigDecimal threshold,
                                  @RequestParam("currentFile") MultipartFile currentFile,
                                  @RequestParam("databaseDrugsFile") MultipartFile databaseDrugsFile) {

        try {
            similarityService.compareDatabaseDrugsToVendorDrugs(threshold, currentFile, databaseDrugsFile);
        } catch (Exception e) {

        }
    }
}
