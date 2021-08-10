package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class SampleTxServiceTests {

    private static final Logger log = LoggerFactory.getLogger(SampleTxServiceTests.class);

    @Autowired
    private SampleTxService service;

    @Test
    public void testLong() {

        String str = "Starry\r\n" +
                "Starry night\r\n" +
                "Paint your palette blue and grey\r\n" +
                "Look out on a summer's day";

        log.info("" + str.getBytes().length);
        service.addData(str);

    }

}
