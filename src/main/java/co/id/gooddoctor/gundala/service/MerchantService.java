package co.id.gooddoctor.gundala.service;

import co.id.gooddoctor.gundala.dao.MerchantDao;
import co.id.gooddoctor.gundala.entity.Merchant;
import co.id.gooddoctor.gundala.model.BaseResponse;
import co.id.gooddoctor.gundala.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

    private static Logger logger = LoggerFactory.getLogger(MerchantService.class);

    @Autowired
    public MerchantDao merchantDao;

    public BaseResponse createMerchant(Merchant merchant){

        logger.info("merchant contains {} " , JsonUtil.objectToJson(merchant));
        try {
            merchantDao.save(merchant);

            return new BaseResponse().successProcess();
        }catch (Exception e){
            logger.error("failed to create merchant", e);
            return new BaseResponse().failedProcess();
        }
    }


    public BaseResponse getDetailMerchant(long id){
        try {
            Merchant merchant = merchantDao.findById(id).orElse(null);

            return new BaseResponse().successProcess(merchant);
        }catch (Exception e){
            logger.error("failed to get detail merchant", e);
            return new BaseResponse().failedProcess();
        }
    }
}
