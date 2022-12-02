package co.id.gundala.domain.user.controller;

import co.id.gundala.domain.user.dto.LoginDto;
import co.id.gundala.domain.user.entity.User;
import co.id.gundala.domain.user.message.LoginErrorMessage;
import co.id.gundala.domain.user.service.LoginService;
import co.id.gundala.infrastructure.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    LoginService loginService;

    //    @Cacheable(value = "users", key = "#loginDto.uname", unless = "#result.code == 1" )
    @PostMapping("login")
    public BaseResponse loginUser(@RequestHeader("time") String time,
                                  @RequestHeader("hmac") String hmac,
                                  @RequestBody LoginDto loginDto) {
        log.info("Enter to login ");
        try {
            User user = loginService.login(loginDto, time, hmac);
            return new BaseResponse().successProcess(user);
        } catch (Exception e) {
            String errorMessage = LoginErrorMessage.equalsMessage(e.getCause().getLocalizedMessage());
            return new BaseResponse().failedProcess(errorMessage, null);
        }
    }

    //    @CacheEvict(value = "users", key = "#loginDto.uname")
    @PostMapping("logout")
    public BaseResponse logoutUser(@RequestBody LoginDto loginDto) {
        log.info("succes to logout");
        return null;
    }
}
