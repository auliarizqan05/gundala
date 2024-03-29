package co.id.gundala.domain.user.dao;

import co.id.gundala.domain.user.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleDao extends JpaRepository<Module, Long> {
}
