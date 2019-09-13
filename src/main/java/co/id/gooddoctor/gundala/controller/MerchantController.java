package co.id.gooddoctor.gundala.controller;

import co.id.gooddoctor.gundala.entity.Merchant;
import co.id.gooddoctor.gundala.model.BaseResponse;
import co.id.gooddoctor.gundala.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "merchants")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @PostMapping()
    public BaseResponse insertMerchant(@RequestBody Merchant merchant){

        BaseResponse baseResponse = merchantService.createMerchant(merchant);
        return baseResponse;
    }

    @GetMapping()
    public BaseResponse getDetailMerchant(@PathVariable long id){

        BaseResponse baseResponse = merchantService.getDetailMerchant(id);
        return baseResponse;
    }

}
