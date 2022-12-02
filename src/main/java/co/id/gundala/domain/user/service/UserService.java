package co.id.gundala.domain.user.service;

import co.id.gundala.domain.user.dao.UserDao;
import co.id.gundala.domain.user.dto.UserDto;
import co.id.gundala.domain.user.entity.User;
import co.id.gundala.domain.user.mapper.UserMapper;
import co.id.gundala.infrastructure.model.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapper userMapper;

    public BaseResponse createUser(UserDto userDTO) {
        try {
            User user = userMapper.dtoToEntity(userDTO);
            userDao.save(user);

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create user", e);
            throw new IllegalArgumentException(e);
        }
    }


    public BaseResponse getDetailUser(long id) {
        try {
            User user = userDao.findById(id).orElse(null);

            if (user == null) {
                return new BaseResponse().failedProcess(404, "User not exist", null);
            }
            
            return new BaseResponse().successProcess(user);
        } catch (Exception e) {
            logger.error("failed to get detail user", e);
            throw new IllegalArgumentException(e);
        }
    }

    public User findByVendorId(long id) {
        try {
            return userDao.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("failed to get user by vendor id", e);
            throw new IllegalArgumentException(e);
        }

    }

    public BaseResponse getUsers() {
        try {
            List<User> users = userDao.findAll();

            return new BaseResponse().successProcess(users);
        } catch (Exception e) {
            logger.error("failed to get list user", e);
            throw new IllegalArgumentException(e);
        }
    }

    public BaseResponse updateUser(long id, UserDto userDTO) {
        try {
            User user = userDao.findById(id).map(userDataMapper -> {
                userDataMapper = userMapper.dtoToEntity(userDTO);
                userDataMapper.setId(id);

                return userDao.save(userDataMapper);
            }).orElse(null);

            if (user == null) {
                return new BaseResponse().failedProcess(404, "User not exist", null);
            }

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create user", e);
            throw new IllegalArgumentException(e);
        }
    }

    public BaseResponse deleteUser(long id) {
        try {
            userDao.deleteById(id);

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create user", e);
            throw new IllegalArgumentException(e);
        }
    }

}
