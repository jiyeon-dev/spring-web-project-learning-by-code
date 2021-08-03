package org.zerock.service;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardServiceTests {

    private static final Logger LOGGER = Logger.getLogger(BoardServiceTests.class);

    @Autowired
    private BoardService service;

    @Test
    public void TestServiceExist() {
        LOGGER.info(service);
        Assert.assertNotNull(service);
    }

    @Test
    public void testRegister() {

        BoardVO board = new BoardVO();
        board.setTitle("new !!");
        board.setContent("new Content!!");
        board.setWriter("jiyeon");

        service.register(board);

        LOGGER.info("생성된 게시물 번호: " + board.getNo());

    }

//    @Test
//    public void testGetList() {
//        service.getList().forEach(board -> LOGGER.info(board));
//    }
    @Test
    public void testGetList() {
        service.getList(new Criteria(1, 10)).forEach(board -> LOGGER.info(board));
    }

    @Test
    public void testGet() {
        LOGGER.info(service.get(1L));
    }

    @Test
    public void testDelete() {
        LOGGER.info("REMOVE RESULT: " + service.remove(7L));
    }

    @Test
    public void testUpdate() {

        BoardVO board = service.get(6L);
        if (board == null) {
            return;
        }

        board.setTitle("updated title");
        LOGGER.info("MODIFY RESULT: " + service.modify(board));

    }
}
