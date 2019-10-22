package co.id.gooddoctor.gundala.domain.settlement.controller;

import co.id.gooddoctor.gundala.infrastructure.model.BaseResponse;
import co.id.gooddoctor.gundala.domain.settlement.service.SettlementProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping(value = "/settlement")
@Slf4j
public class SettlementProcessController {

    @Autowired
    SettlementProcessService settlementProcessService;

    @PostMapping(value = "/upload")
    public BaseResponse generateReportSettlement(@RequestParam("fileUpload") MultipartFile fileUpload,
                                                 @RequestParam("dateUpload") Date dateUpload) {

        log.info("file name has been upload : {} to date {}", fileUpload.getOriginalFilename(), dateUpload);
        BaseResponse baseResponse;
        try {
            String result = settlementProcessService.extractData(fileUpload, dateUpload);
            baseResponse = new BaseResponse().successProcess(result);
        } catch (Exception e) {
            log.error("Failed to upload file ", e);
            baseResponse = new BaseResponse().failedProcess(e.getMessage());
        }

        return baseResponse;
    }

}
