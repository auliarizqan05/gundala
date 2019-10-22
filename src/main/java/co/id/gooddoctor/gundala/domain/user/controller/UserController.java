package co.id.gooddoctor.gundala.domain.user.controller;

import co.id.gooddoctor.gundala.domain.user.dto.LoginDto;
import co.id.gooddoctor.gundala.domain.user.dto.UserDto;
import co.id.gooddoctor.gundala.infrastructure.model.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @PostMapping()
    public BaseResponse registerUser(@RequestBody UserDto userDto) {

        return null;
    }


}
