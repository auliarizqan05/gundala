package co.id.gundala.domain.settlement.dao;

import co.id.gundala.domain.settlement.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantDao extends JpaRepository<Merchant, Long> {

    Optional<Merchant> findByVendorName(String vendorName);

}
