package co.id.gundala.domain.settlement.controller;

import co.id.gundala.domain.settlement.dto.MerchantDTO;
import co.id.gundala.domain.settlement.service.MerchantService;
import co.id.gundala.infrastructure.model.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "merchants")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @Operation(summary = "Input new merchant")
    @PostMapping()
    public BaseResponse insertMerchant(@RequestBody MerchantDTO merchant) {

        BaseResponse baseResponse;
        try {
            baseResponse = merchantService.createMerchant(merchant);
        } catch (Exception e) {
            baseResponse = new BaseResponse().failedProcess(e.getMessage(), null);
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
