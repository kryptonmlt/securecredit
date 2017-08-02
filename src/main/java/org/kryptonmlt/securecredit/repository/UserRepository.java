package org.kryptonmlt.securecredit.repository;

import org.kryptonmlt.securecredit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Kurt
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
