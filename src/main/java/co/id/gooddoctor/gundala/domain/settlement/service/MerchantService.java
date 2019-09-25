package co.id.gooddoctor.gundala.domain.settlement.service;

import co.id.gooddoctor.gundala.domain.settlement.dao.MerchantDao;
import co.id.gooddoctor.gundala.domain.settlement.dto.MerchantDTO;
import co.id.gooddoctor.gundala.domain.settlement.entity.Merchant;
import co.id.gooddoctor.gundala.domain.settlement.mapper.MerchantMapper;
import co.id.gooddoctor.gundala.domain.settlement.model.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {

    private static Logger logger = LoggerFactory.getLogger(MerchantService.class);

    @Autowired
    private MerchantDao merchantDao;

    public BaseResponse createMerchant(MerchantDTO merchantDTO) {
        try {
            Merchant merchant = MerchantMapper.INSTANCE.dtoToEntity(merchantDTO);
            merchantDao.save(merchant);

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create merchant", e);
            return new BaseResponse().failedProcess(e.getMessage());
        }
    }


    public BaseResponse getDetailMerchant(long id) {
        try {
            Merchant merchant = merchantDao.findById(id).orElse(null);

            return new BaseResponse().successProcess(merchant);
        } catch (Exception e) {
            logger.error("failed to get detail merchant", e);
            return new BaseResponse().failedProcess();
        }
    }

    public Merchant findByVendorId(long id) {
        try {
            return merchantDao.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("failed to get merchant by vendor id", e);
        }

        return null;
    }

    public BaseResponse getMerchants() {
        try {
            List<Merchant> merchants = merchantDao.findAll();

            return new BaseResponse().successProcess(merchants);
        } catch (Exception e) {
            logger.error("failed to get list merchant", e);
            return new BaseResponse().failedProcess();
        }
    }

    public BaseResponse updateMerchant(long id, MerchantDTO merchantDTO) {
        try {
            Merchant merchant = merchantDao.findById(id).map(merchantMapper -> {
                merchantMapper = MerchantMapper.INSTANCE.dtoToEntity(merchantDTO);
                return merchantDao.save(merchantMapper);
            }).orElse(null);

            if (merchant == null) {
                return new BaseResponse().failedProcess();
            }

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create merchant", e);
            return new BaseResponse().failedProcess();
        }
    }

    public BaseResponse deleteMerchant(long id) {
        try {
            merchantDao.deleteById(id);

            return new BaseResponse().successProcess();
        } catch (Exception e) {
            logger.error("failed to create merchant", e);
            return new BaseResponse().failedProcess();
        }
    }

}
