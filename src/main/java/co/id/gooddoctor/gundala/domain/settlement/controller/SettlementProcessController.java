package co.id.gooddoctor.gundala.domain.settlement.controller;

import co.id.gooddoctor.gundala.domain.settlement.model.BaseResponse;
import co.id.gooddoctor.gundala.domain.settlement.service.SettlementProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@RestController
@RequestMapping(value = "/settlement")
@Slf4j
public class SettlementProcessController {

    @Autowired
    SettlementProcess settlementProcess;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public BaseResponse generateReportSettlement(@RequestParam("fileUpload") MultipartFile fileUpload,
                                                 @RequestParam("dateUpload") Date dateUpload) {

        log.info("file name has been upload : {} ", fileUpload.getOriginalFilename());
        BaseResponse baseResponse;
        try {
            String result = settlementProcess.extractData(fileUpload, dateUpload);
            baseResponse = new BaseResponse().successProcess(result);
        } catch (Exception e) {
            log.error("Failed upload file ", e);
            baseResponse = new BaseResponse().failedProcess(e.getMessage());
        }

        return baseResponse;
    }

}
