package co.id.gooddoctor.gundala.domain.settlement.dao;

import co.id.gooddoctor.gundala.domain.settlement.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantDao extends JpaRepository<Merchant, Long> {

    Optional<Merchant> findByVendorId(long vendorId);

}
