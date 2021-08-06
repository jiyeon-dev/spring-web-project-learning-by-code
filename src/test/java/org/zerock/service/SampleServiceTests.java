package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.zerock.config.RootConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class SampleServiceTests {

    private static final Logger log = LoggerFactory.getLogger(SampleServiceTests.class);

    @Autowired
    private SampleService service;

    @Test
    public void testClass() {
        log.info("" + service);
        log.info(service.getClass().getName());
    }

    @Test
    public void testAdd() throws Exception {
        log.info("" + service.doAdd("123", "455"));
    }

    @Test
    public void testAddError() throws Exception {  // 고의적으로 에러 발생 @AfterThrowing
        log.info("" + service.doAdd("123", "ABC"));
    }

}
