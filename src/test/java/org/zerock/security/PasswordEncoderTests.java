package org.zerock.security;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        org.zerock.config.RootConfig.class,
        org.zerock.config.SecurityConfig.class
})
@Log4j
public class PasswordEncoderTests {

    @Autowired
    private PasswordEncoder pwEncoder;

    @Test
    public void testEncode() {
        String str = "member";
        String enStr = pwEncoder.encode(str);

        log.info(enStr); // 매번 달라질 수 있음: $2a$10$ATt/KOfHtXzm.IbdxuAcpeNlJeN5nedl6gw0LFy3wwBON4oDuI3uG
    }

}
