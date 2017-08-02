package org.kryptonmlt.securecredit.service;

import org.kryptonmlt.securecredit.model.User;

/**
 *
 * @author Kurt
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
