package co.id.gooddoctor.gundala.domain.user.service;

import co.id.gooddoctor.gundala.domain.settlement.entity.Merchant;
import co.id.gooddoctor.gundala.domain.settlement.mapper.MerchantMapper;
import co.id.gooddoctor.gundala.domain.user.dao.UserDao;
import co.id.gooddoctor.gundala.domain.user.dto.LoginDto;
import co.id.gooddoctor.gundala.domain.user.entity.User;
import co.id.gooddoctor.gundala.domain.user.mapper.UserMapper;
import co.id.gooddoctor.gundala.infrastructure.security.HMACHelper;
import co.id.gooddoctor.gundala.infrastructure.util.ConstantUtil;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {

    @Autowired
    ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider;

    @Autowired
    UserDao userDao;

    public String login(LoginDto loginDto, String time, String hmac) {

        try {

            if (!checkHMACSignature(time, hmac)) {
                throw new IllegalArgumentException("HMAC Signature not match");
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    loginDto.getUname(), loginDto.getPwd());
            Authentication auth = activeDirectoryLdapAuthenticationProvider.authenticate(authentication);

            if (!auth.isAuthenticated()) {
                return "Failed Login, not authentication";
            }

            User userExist = userDao.findByUname(auth.getName());
            if (userExist == null) {
                userExist = UserMapper.INSTANCE.loginDtoToEntity(loginDto);
                String fullName = StringUtils.capitalize(loginDto.getUname()).replace(".", " ");
                String emailUser = new StringBuilder()
                        .append(loginDto.getUname())
                        .append("@")
                        .append(ConstantUtil.DOMAIN_EMAIL)
                        .toString();

                userExist.setFullName(fullName);
                userExist.setEmail(emailUser);

            }

            userExist.setLastLogin(new Date());
            userDao.save(userExist);

            return "Success login";

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

    }

    private boolean checkHMACSignature(String time, String hmac) {
        HMACHelper hmacHelper = new HMACHelper();
        return hmacHelper.calculateHMACSignature(
                time, hmac);
    }
}
