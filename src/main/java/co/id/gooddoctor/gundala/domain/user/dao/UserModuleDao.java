package co.id.gooddoctor.gundala.domain.user.dao;

import co.id.gooddoctor.gundala.domain.user.entity.UserModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserModuleDao extends JpaRepository<UserModule, Long> {
}
