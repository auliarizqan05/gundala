package co.id.gooddoctor.gundala.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class MerchantPayout {

    @GetMapping(value = "import-file")
    public Response importFile(){

        System.out.println("get in this api");
        return null;
    }
}
