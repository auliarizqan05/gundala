package co.id.gooddoctor.gundala.domain.user.dao;

import co.id.gooddoctor.gundala.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByUname(String uname);
}
