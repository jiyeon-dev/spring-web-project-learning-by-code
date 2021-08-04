package org.zerock.mapper;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.service.BoardServiceTests;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTests {

    private static final Logger log = Logger.getLogger(BoardServiceTests.class);

    @Autowired
    private BoardMapper mapper;

    @Test
    public void testGetList() {
        System.out.println(mapper.getList());
    }

    @Test
    public void testPaging() {
        Criteria cri = new Criteria();

        cri.setPageNum(2);
        cri.setAmount(10);

        List<BoardVO> list = mapper.getListWithPaging(cri);

        list.forEach(board -> log.info(board));
    }

    @Test
    public void testInsert() {
        BoardVO board = new BoardVO();
        board.setTitle("TEST - 새로 작성하는 제목");
        board.setContent("TEST - 새로 작성하는 내용");
        board.setWriter("TEST - newbie");

        mapper.insert(board);

        System.out.println(board);
    }

    @Test
    public void testInsertSelectKey() {
        BoardVO board = new BoardVO();
        board.setTitle("TEST - New Title");
        board.setContent("TEST - New Content");
        board.setWriter("TEST - newbie");

        mapper.insertSelectKey(board);

        System.out.println(board);
    }

    @Test
    public void testRead() {
        BoardVO board = mapper.read(7L);
        System.out.println(board);
    }

    @Test
    public void testDelete() {
        System.out.println("DELETE COUNT: " + mapper.delete(6L));
    }

    @Test
    public void testUpdate() {

        BoardVO board = new BoardVO();

        board.setNo(6L);
        board.setTitle("new title");
        board.setContent("new content");
        board.setWriter("new writer");

        int count = mapper.update(board);
        System.out.println("UPDATE COUNT: " + count);

    }


    @Test
    public void testGetTotalCount() {
        Criteria cri = new Criteria();
        System.out.println(mapper.getTotalCount(cri));
    }

}
