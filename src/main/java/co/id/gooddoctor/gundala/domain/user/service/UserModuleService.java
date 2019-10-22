package co.id.gooddoctor.gundala.domain.user.service;

import co.id.gooddoctor.gundala.domain.user.dao.UserModuleDao;
import co.id.gooddoctor.gundala.domain.user.dto.UserModuleDto;
import co.id.gooddoctor.gundala.domain.user.entity.UserModule;
import co.id.gooddoctor.gundala.domain.user.mapper.UserModuleMapper;
import co.id.gooddoctor.gundala.infrastructure.model.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserModuleService {

    private static Logger logger = LoggerFactory.getLogger(UserModuleService.class);

    @Autowired
    private UserModuleDao userModuleDao;

    public BaseResponse createUserModule(UserModuleDto userModuleDTO) {
        try {
            UserModule userModule = UserModuleMapper.INSTANCE.dtoToEntity(userModuleDTO);
            userModuleDao.save(userModule);

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create userModule", e);
            throw new IllegalArgumentException(e);
        }
    }


    public BaseResponse getDetailUserModule(long id) {
        try {
            UserModule userModule = userModuleDao.findById(id).orElse(null);

            return new BaseResponse().successProcess(userModule);
        } catch (Exception e) {
            logger.error("failed to get detail userModule", e);
            throw new IllegalArgumentException(e);
        }
    }

    public UserModule findByVendorId(long id) {
        try {
            return userModuleDao.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("failed to get userModule by vendor id", e);
            throw new IllegalArgumentException(e);
        }

    }

    public BaseResponse getUserModules() {
        try {
            List<UserModule> userModules = userModuleDao.findAll();

            return new BaseResponse().successProcess(userModules);
        } catch (Exception e) {
            logger.error("failed to get list userModule", e);
            throw new IllegalArgumentException(e);
        }
    }

    public BaseResponse updateUserModule(long id, UserModuleDto userModuleDto) {
        try {
            UserModule userModule = userModuleDao.findById(id).map(userModuleMapper -> {
                userModuleMapper = UserModuleMapper.INSTANCE.dtoToEntity(userModuleDto);
                return userModuleDao.save(userModuleMapper);
            }).orElse(null);

            if (userModule == null) {
                return new BaseResponse().failedProcess("UserModule not exist");
            }

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create userUserModule", e);
            throw new IllegalArgumentException(e);
        }
    }

    public BaseResponse deleteUserModule(long id) {
        try {
            userModuleDao.deleteById(id);

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create userUserModule", e);
            throw new IllegalArgumentException(e);
        }
    }

}
