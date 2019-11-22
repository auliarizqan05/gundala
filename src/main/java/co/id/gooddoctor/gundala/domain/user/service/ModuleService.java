package co.id.gooddoctor.gundala.domain.user.service;

import co.id.gooddoctor.gundala.domain.user.dao.ModuleDao;
import co.id.gooddoctor.gundala.domain.user.dto.ModuleDto;
import co.id.gooddoctor.gundala.domain.user.entity.Module;
import co.id.gooddoctor.gundala.domain.user.mapper.ModuleMapper;
import co.id.gooddoctor.gundala.infrastructure.model.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

    private static Logger logger = LoggerFactory.getLogger(ModuleService.class);

    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private ModuleMapper moduleMapper;

    public BaseResponse createModule(ModuleDto moduleDTO) {
        try {
            Module module = moduleMapper.dtoToEntity(moduleDTO);
            moduleDao.save(module);

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create module", e);
            throw new IllegalArgumentException(e);
        }
    }


    public BaseResponse getDetailModule(long id) {
        try {
            Module module = moduleDao.findById(id).orElse(null);

            return new BaseResponse().successProcess(module);
        } catch (Exception e) {
            logger.error("failed to get detail module", e);
            throw new IllegalArgumentException(e);
        }
    }

    public Module findByVendorId(long id) {
        try {
            return moduleDao.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("failed to get module by vendor id", e);
            throw new IllegalArgumentException(e);
        }

    }

    public BaseResponse getModules() {
        try {
            List<Module> modules = moduleDao.findAll();

            return new BaseResponse().successProcess(modules);
        } catch (Exception e) {
            logger.error("failed to get list module", e);
            throw new IllegalArgumentException(e);
        }
    }

    public BaseResponse updateModule(long id, ModuleDto moduleDTO) {
        try {
            Module module = moduleDao.findById(id).map(moduleDataMapper -> {
                moduleDataMapper = moduleMapper.dtoToEntity(moduleDTO);
                moduleDataMapper.setId(id);

                return moduleDao.save(moduleDataMapper);
            }).orElse(null);

            if (module == null) {
                return new BaseResponse().failedProcess("Module not exist");
            }

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create module", e);
            throw new IllegalArgumentException(e);
        }
    }

    public BaseResponse deleteModule(long id) {
        try {
            moduleDao.deleteById(id);

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create module", e);
            throw new IllegalArgumentException(e);
        }
    }

}
