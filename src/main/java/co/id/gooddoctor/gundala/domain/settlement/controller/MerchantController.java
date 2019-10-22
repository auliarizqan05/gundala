package co.id.gooddoctor.gundala.domain.settlement.controller;

import co.id.gooddoctor.gundala.domain.settlement.dto.MerchantDTO;
import co.id.gooddoctor.gundala.infrastructure.model.BaseResponse;
import co.id.gooddoctor.gundala.domain.settlement.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "merchants")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @PostMapping()
    public BaseResponse insertMerchant(@RequestBody MerchantDTO merchant) {

        BaseResponse baseResponse;
        try {
            baseResponse = merchantService.createMerchant(merchant);
        } catch (Exception e) {
            baseResponse = new BaseResponse().failedProcess(e.getMessage());
        }

        return baseResponse;
    }

    @GetMapping()
    public BaseResponse getMerchants() {

        BaseResponse baseResponse = merchantService.getMerchants();
        return baseResponse;
    }

    @GetMapping("/{id}")
    public BaseResponse getDetailMerchant(@PathVariable long id){

        BaseResponse baseResponse = merchantService.getDetailMerchant(id);
        return baseResponse;
    }

    @PutMapping("/{id}")
    public BaseResponse updateMerchant(@PathVariable long id, @RequestBody MerchantDTO merchantDTO) {

        BaseResponse baseResponse = merchantService.updateMerchant(id, merchantDTO);
        return baseResponse;
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteMerchant(@PathVariable long id) {

        BaseResponse baseResponse = merchantService.deleteMerchant(id);
        return baseResponse;
    }

}
