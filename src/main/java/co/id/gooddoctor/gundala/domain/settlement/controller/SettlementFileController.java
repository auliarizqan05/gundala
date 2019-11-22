package co.id.gooddoctor.gundala.domain.settlement.controller;

import co.id.gooddoctor.gundala.domain.settlement.service.SettlementFileService;
import co.id.gooddoctor.gundala.infrastructure.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "settlementFiles")
public class SettlementFileController {

    @Autowired
    SettlementFileService settlementFileService;

    @GetMapping()
    public BaseResponse getSettlementFiles() {
        BaseResponse baseResponse = settlementFileService.getSettlementFiles();
        return baseResponse;
    }

    @GetMapping("/{id}")
    public BaseResponse getDetailSettlement(@PathVariable("id") long id) {
        BaseResponse baseResponse = settlementFileService.getDetailSettlementFile(id);
        return baseResponse;
    }
}
