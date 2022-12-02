package co.id.gundala.domain.user.controller;

import co.id.gundala.domain.user.dto.ModuleDto;
import co.id.gundala.domain.user.service.ModuleService;
import co.id.gundala.infrastructure.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "modules")
public class ModuleController {

    @Autowired
    ModuleService moduleService;

    @PostMapping()
    public BaseResponse insertModule(@RequestBody ModuleDto Module) {

        BaseResponse baseResponse;
        try {
            baseResponse = moduleService.createModule(Module);
        } catch (Exception e) {
            baseResponse = new BaseResponse().failedProcess(e.getMessage());
        }

        return baseResponse;
    }

    @GetMapping()
    public BaseResponse getModules() {

        BaseResponse baseResponse = moduleService.getModules();
        return baseResponse;
    }

    @GetMapping("/{id}")
    public BaseResponse getDetailModule(@PathVariable long id) {

        BaseResponse baseResponse = moduleService.getDetailModule(id);
        return baseResponse;
    }

    @PutMapping("/{id}")
    public BaseResponse updateModule(@PathVariable long id, @RequestBody ModuleDto moduleDTO) {

        BaseResponse baseResponse = moduleService.updateModule(id, moduleDTO);
        return baseResponse;
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteModule(@PathVariable long id) {

        BaseResponse baseResponse = moduleService.deleteModule(id);
        return baseResponse;
    }

}
