package co.id.gundala.domain.user.controller;

import co.id.gundala.domain.user.dto.UserDto;
import co.id.gundala.domain.user.service.UserService;
import co.id.gundala.infrastructure.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public BaseResponse insertUser(@Validated @RequestBody UserDto User) {

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
    public BaseResponse updateUser(@PathVariable long id, @Validated @NonNull @RequestBody UserDto userDTO) {

        BaseResponse baseResponse = userService.updateUser(id, userDTO);
        return baseResponse;
    }

    @DeleteMapping("/{id}")
    public BaseResponse deleteUser(@PathVariable long id) {

        BaseResponse baseResponse = userService.deleteUser(id);
        return baseResponse;
    }


}
