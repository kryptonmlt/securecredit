package org.kryptonmlt.securecredit.service;

/**
 *
 * @author Kurt
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autologin(String username, String password);
}
