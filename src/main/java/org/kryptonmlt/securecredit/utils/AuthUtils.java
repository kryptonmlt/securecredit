package org.kryptonmlt.securecredit.utils;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Kurt
 */
public class AuthUtils {

    private AuthUtils() {

    }

    public static boolean isAdmin(Collection<GrantedAuthority> roles) {
        for (GrantedAuthority role : roles) {
            if (role.getAuthority().equals("ADMIN")) {
                return true;
            }
        }
        return false;
    }
}
