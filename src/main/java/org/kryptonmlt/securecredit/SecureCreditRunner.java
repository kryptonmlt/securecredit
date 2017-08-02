package org.kryptonmlt.securecredit;

import org.kryptonmlt.securecredit.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kurt
 */
@Component
public class SecureCreditRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecureCreditRunner.class);

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Starts the Game Search Scraper
     *
     * @param strings no arguments used
     * @throws Exception all exceptions should be caught
     */
    @Override
    public void run(String... strings) throws Exception {
        LOGGER.debug("startin secure credit");
        userServiceImpl.createAdminUser();
    }

}
