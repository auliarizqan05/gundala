package co.id.gundala.domain.settlement.dao;

import co.id.gundala.domain.settlement.entity.SettlementFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementFileDao extends JpaRepository<SettlementFile, Long> {

    SettlementFile findByVendorIdAndFileName(long vendorId, String filename);

    SettlementFile findByFileName(String filename);
}
