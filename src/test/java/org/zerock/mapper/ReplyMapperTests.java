package org.zerock.mapper;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.service.BoardServiceTests;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyMapperTests {

    private static final Logger log = Logger.getLogger(ReplyMapperTests.class);

    @Autowired
    private ReplyMapper mapper;

    @Test
    public void testMapper() {
        log.info(mapper);
    }

}
