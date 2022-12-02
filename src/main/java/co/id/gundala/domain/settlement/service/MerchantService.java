package co.id.gundala.domain.settlement.service;

import co.id.gundala.domain.settlement.dao.MerchantDao;
import co.id.gundala.domain.settlement.dto.MerchantDTO;
import co.id.gundala.domain.settlement.entity.Merchant;
import co.id.gundala.domain.settlement.mapper.MerchantMapper;
import co.id.gundala.infrastructure.model.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {

    private static Logger logger = LoggerFactory.getLogger(MerchantService.class);

    @Autowired
    private MerchantDao merchantDao;
    @Autowired
    private MerchantMapper merchantMapper;

    public BaseResponse createMerchant(MerchantDTO merchantDTO) {
        try {
            Merchant merchant = merchantMapper.dtoToEntity(merchantDTO);
            merchantDao.save(merchant);

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create merchant", e);
            if (e instanceof DataIntegrityViolationException) {
                throw new IllegalArgumentException("The vendor id (" + merchantDTO.getVendorId() + ") is duplicate ");
            }
            throw new IllegalArgumentException(e);
        }
    }


    public BaseResponse getDetailMerchant(long id) {
        try {
            Merchant merchant = merchantDao.findById(id).orElse(null);

            return new BaseResponse().successProcess(merchant);
        } catch (Exception e) {
            logger.error("failed to get detail merchant", e);
            return new BaseResponse().failedProcess(e.getMessage(), null);
        }
    }

    public BaseResponse getMerchants() {
        try {
            List<Merchant> merchants = merchantDao.findAll();

            return new BaseResponse().successProcess(merchants);
        } catch (Exception e) {
            logger.error("failed to get list merchant", e);
            return new BaseResponse().failedProcess(e.getMessage(), null);
        }
    }

    public BaseResponse updateMerchant(long id, MerchantDTO merchantDTO) {
        try {
            Merchant merchant = merchantDao.findById(id).map(merchantDataMapper -> {
                merchantDataMapper = merchantMapper.dtoToEntity(merchantDTO);
                merchantDataMapper.setId(id);

                return merchantDao.save(merchantDataMapper);
            }).orElse(null);

            if (merchant == null) {
                return new BaseResponse().failedProcess("Merchant " + merchantDTO.getVendorId() + " not exist", null);
            }

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to update merchant", e);
            String messageError = e.getMessage();
            if (e instanceof DataIntegrityViolationException) {
                messageError = "The vendor id (" + merchantDTO.getVendorId() + ") is duplicate";
            }
            return new BaseResponse().failedProcess(messageError, null);
        }
    }

    public BaseResponse deleteMerchant(long id) {
        try {
            merchantDao.deleteById(id);

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create merchant", e);
            return new BaseResponse().failedProcess(e.getMessage(), null);
        }
    }

}
