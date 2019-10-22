package co.id.gooddoctor.gundala.domain.user.controller;

import co.id.gooddoctor.gundala.domain.user.dto.UserModuleDto;
import co.id.gooddoctor.gundala.domain.user.service.UserModuleService;
import co.id.gooddoctor.gundala.infrastructure.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "userModules")
public class UserModuleController {

    @Autowired
    UserModuleService userModuleService;

    @PostMapping()
    public BaseResponse insertModule(@RequestBody UserModuleDto userModule) {

        BaseResponse baseResponse;
        try {
            baseResponse = userModuleService.createUserModule(userModule);
        } catch (Exception e) {
            baseResponse = new BaseResponse().failedProcess(e.getMessage());
        }

        return baseResponse;
    }

    @GetMapping()
    public BaseResponse getModules() {

        BaseResponse baseResponse = userModuleService.getUserModules();
        return baseResponse;
    }

    @GetMapping("/{id}")
    public BaseResponse getDetailModule(@PathVariable long id) {

        BaseResponse baseResponse = userModuleService.getDetailUserModule(id);
        return baseResponse;
    }

    @PutMapping("/{id}")
    public BaseResponse updateModule(@PathVariable long id, @RequestBody UserModuleDto userModuleDTO) {

        BaseResponse baseResponse = userModuleService.updateUserModule(id, userModuleDTO);
        return baseResponse;
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteModule(@PathVariable long id) {

        BaseResponse baseResponse = userModuleService.deleteUserModule(id);
        return baseResponse;
    }

}
