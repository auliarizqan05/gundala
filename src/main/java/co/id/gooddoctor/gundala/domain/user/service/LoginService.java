package co.id.gooddoctor.gundala.domain.user.service;

import co.id.gooddoctor.gundala.domain.user.dao.RoleDao;
import co.id.gooddoctor.gundala.domain.user.dao.UserDao;
import co.id.gooddoctor.gundala.domain.user.dto.LoginDto;
import co.id.gooddoctor.gundala.domain.user.entity.Role;
import co.id.gooddoctor.gundala.domain.user.entity.User;
import co.id.gooddoctor.gundala.domain.user.mapper.UserMapper;
import co.id.gooddoctor.gundala.infrastructure.security.HMACHelper;
import co.id.gooddoctor.gundala.infrastructure.util.ConstantUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class LoginService {

    private static long ROLE_DEFAULT = 1l;

    @Autowired
    ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider;

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    public User login(LoginDto loginDto, String time, String hmac) {

        try {

            if (!checkHMACSignature(time, hmac)) {
                throw new IllegalArgumentException("HMAC Signature not match");
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    loginDto.getUname(), loginDto.getPwd());
            Authentication auth = activeDirectoryLdapAuthenticationProvider.authenticate(authentication);

            if (!auth.isAuthenticated()) {
                throw new IllegalArgumentException("User cannot authenticated");
            }
            log.info("User {} verified", loginDto.getUname());

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

            Set<Role> roles = new HashSet<>();
            if (userExist.getRoles() == null) {
                //default
                Role role = roleDao.findById(ROLE_DEFAULT).orElse(null);
                if (role == null) {
                    throw new IllegalArgumentException("Role is not exist, please create role first");
                }

                roles.add(role);

            } else {
                roles = userExist.getRoles();
            }

            userExist.setRoles(roles);
            userDao.save(userExist);

            return userExist;
        } catch (Exception e) {
            log.error("error when  login", e);
            throw new IllegalArgumentException(e);
        }

    }

    private boolean checkHMACSignature(String time, String hmac) {
        HMACHelper hmacHelper = new HMACHelper();
        return hmacHelper.calculateHMACSignature(
                time, hmac);
    }
}
