package co.id.gooddoctor.gundala.domain.settlement.service;

import co.id.gooddoctor.gundala.domain.settlement.dao.SettlementFileDao;
import co.id.gooddoctor.gundala.domain.settlement.entity.SettlementFile;
import co.id.gooddoctor.gundala.infrastructure.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Slf4j
public class SettlementFileService {

    @Autowired
    SettlementFileDao settlementFileDao;

    public BaseResponse getDetailSettlementFile(long id) {
        try {
            SettlementFile settlementFile = settlementFileDao.findById(id).orElse(null);

            return new BaseResponse().successProcess(settlementFile);
        } catch (Exception e) {
            log.error("failed to get detail settlement file", e);
            return new BaseResponse().failedProcess(e.getMessage(), null);
        }
    }

    public BaseResponse getSettlementFiles() {
        try {
            List<SettlementFile> settlementFiles = settlementFileDao.findAll();

            return new BaseResponse().successProcess(settlementFiles);
        } catch (Exception e) {
            log.error("failed to get list settlement file", e);
            return new BaseResponse().failedProcess(e.getMessage(), null);
        }
    }

    public BaseResponse getBase64ForDownloadFile(long id) {
        try {
            SettlementFile settlementFile = settlementFileDao.findById(id)
                    .orElseThrow(() -> new NullPointerException("The data is not exist, ID : " + id));
            String path = settlementFile.getFilePath();
            String name = settlementFile.getFileName();
            String base64Result;
            String fileName = new StringBuilder()
                    .append(path)
                    .append("/")
                    .append(name)
                    .toString();

            // encode file to base64
            try (InputStream fileToBase64 = new FileInputStream(fileName)) {
                byte[] fileBytes = IOUtils.toByteArray(fileToBase64);
                base64Result = Base64.encodeBase64String(fileBytes);
            } catch (IOException e) {
                log.error("cannot get file {} to base 64, maybe file doesn't exist ", fileName, e);
                throw new IllegalArgumentException(e);
            }

            return new BaseResponse().successProcess(base64Result);
        } catch (Exception e) {
            log.error("failed to create base64 for downlaod file", e);
            return new BaseResponse().failedProcess(e.getMessage(), null);
        }
    }
}
