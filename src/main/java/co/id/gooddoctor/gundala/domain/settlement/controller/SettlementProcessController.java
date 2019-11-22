package co.id.gooddoctor.gundala.domain.settlement.controller;

import co.id.gooddoctor.gundala.domain.settlement.service.SettlementFileService;
import co.id.gooddoctor.gundala.domain.settlement.service.SettlementProcessService;
import co.id.gooddoctor.gundala.infrastructure.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping(value = "settlements")
@Slf4j
public class SettlementProcessController {

    @Autowired
    SettlementProcessService settlementProcessService;
    @Autowired
    SettlementFileService settlementFileService;


    @PostMapping("/upload")
    public BaseResponse generateReportSettlement(@RequestParam("fileUpload") MultipartFile fileUpload,
                                                 @RequestParam("dateUpload") Date dateUpload) {

        log.info("file name has been upload : {} to date {}", fileUpload.getOriginalFilename(), dateUpload);
        BaseResponse baseResponse;
        try {
            String result = settlementProcessService.uploadData(fileUpload, dateUpload);
            baseResponse = new BaseResponse().successProcess(result);
        } catch (Exception e) {
            log.error("Failed to upload file {} ", e.getMessage());
            baseResponse = new BaseResponse().failedProcess(e.getCause().getMessage(), null);
        }

        return baseResponse;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity downloadReportSettlement(@PathVariable("id") long id) {

        log.info("download file with id : {} ", id);
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(settlementFileService.getBase64ForDownloadFile(id));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponse().failedProcess(HttpStatus.BAD_REQUEST.value(),
                            e.getCause().getLocalizedMessage(), null));
        }
    }

}
