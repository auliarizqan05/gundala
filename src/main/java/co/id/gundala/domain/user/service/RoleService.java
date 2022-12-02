package co.id.gundala.domain.user.service;

import co.id.gundala.domain.user.dao.RoleDao;
import co.id.gundala.domain.user.dto.RoleDto;
import co.id.gundala.domain.user.entity.Role;
import co.id.gundala.domain.user.mapper.RoleMapper;
import co.id.gundala.infrastructure.model.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private static Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleMapper roleMapper;

    public BaseResponse createRole(RoleDto roleDTO) {
        try {
            Role role = roleMapper.dtoToEntity(roleDTO);
            roleDao.save(role);

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create role {} ", roleDTO.getName(), e);

            if (e instanceof DataIntegrityViolationException) {
                throw new IllegalArgumentException("role '" + roleDTO.getName() + "' already exist ");
            }

            throw new IllegalArgumentException(e);
        }
    }


    public BaseResponse getDetailRole(long id) {
        try {
            Role role = roleDao.findById(id).orElse(null);

            return new BaseResponse().successProcess(role);
        } catch (Exception e) {
            logger.error("failed to get detail role", e);
            throw new IllegalArgumentException(e);
        }
    }

    public Role findByVendorId(long id) {
        try {
            return roleDao.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("failed to get role by vendor id", e);
            throw new IllegalArgumentException(e);
        }

    }

    public BaseResponse getRoles() {
        try {
            List<Role> roles = roleDao.findAll();

            return new BaseResponse().successProcess(roles);
        } catch (Exception e) {
            logger.error("failed to get list role", e);
            throw new IllegalArgumentException(e);
        }
    }

    public BaseResponse updateRole(long id, RoleDto roleDTO) {
        try {
            Role role = roleDao.findById(id).map(roleDataMapper -> {
                roleDataMapper = roleMapper.dtoToEntity(roleDTO, roleDataMapper);
                roleDataMapper.setId(id);

                return roleDao.save(roleDataMapper);
            }).orElse(null);

            if (role == null) {
                return new BaseResponse().failedProcess("Role not exist");
            }

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create role", e);
            throw new IllegalArgumentException(e);
        }
    }

    public BaseResponse deleteRole(long id) {
        try {
            roleDao.deleteById(id);

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create role", e);
            throw new IllegalArgumentException(e);
        }
    }

}
