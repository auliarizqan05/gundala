package co.id.gooddoctor.gundala.domain.user.controller;

import co.id.gooddoctor.gundala.domain.user.dto.RoleDto;
import co.id.gooddoctor.gundala.domain.user.service.RoleService;
import co.id.gooddoctor.gundala.infrastructure.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping()
    public BaseResponse insertRole(@RequestBody RoleDto Role) {

        BaseResponse baseResponse;
        try {
            baseResponse = roleService.createRole(Role);
        } catch (Exception e) {
            baseResponse = new BaseResponse().failedProcess(e.getMessage());
        }

        return baseResponse;
    }

    @GetMapping()
    public BaseResponse getRoles() {

        BaseResponse baseResponse = roleService.getRoles();
        return baseResponse;
    }

    @GetMapping("/{id}")
    public BaseResponse getDetailRole(@PathVariable long id) {

        BaseResponse baseResponse = roleService.getDetailRole(id);
        return baseResponse;
    }

    @PutMapping("/{id}")
    public BaseResponse updateRole(@PathVariable long id, @RequestBody RoleDto roleDTO) {

        BaseResponse baseResponse = roleService.updateRole(id, roleDTO);
        return baseResponse;
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteRole(@PathVariable long id) {

        BaseResponse baseResponse = roleService.deleteRole(id);
        return baseResponse;
    }

}
