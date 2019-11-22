package co.id.gooddoctor.gundala.domain.user.controller;

import co.id.gooddoctor.gundala.domain.user.dto.UserDto;
import co.id.gooddoctor.gundala.domain.user.service.UserService;
import co.id.gooddoctor.gundala.infrastructure.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public BaseResponse insertUser(@Valid @RequestBody UserDto User) {

        BaseResponse baseResponse;
        try {
            baseResponse = userService.createUser(User);
        } catch (Exception e) {
            baseResponse = new BaseResponse().failedProcess(e.getMessage());
        }

        return baseResponse;
    }

    @GetMapping()
    public BaseResponse getUsers() {

        BaseResponse baseResponse = userService.getUsers();
        return baseResponse;
    }

    @GetMapping("/{id}")
    public BaseResponse getDetailUser(@PathVariable long id) {

        BaseResponse baseResponse = userService.getDetailUser(id);
        return baseResponse;
    }

    @PutMapping("/{id}")
    public BaseResponse updateUser(@PathVariable long id, @Valid @RequestBody UserDto userDTO) {

        BaseResponse baseResponse = userService.updateUser(id, userDTO);
        return baseResponse;
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteUser(@PathVariable long id) {

        BaseResponse baseResponse = userService.deleteUser(id);
        return baseResponse;
    }


}
