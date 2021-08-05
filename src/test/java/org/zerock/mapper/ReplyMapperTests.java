package org.zerock.mapper;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.service.BoardServiceTests;

import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyMapperTests {

    private static final Logger log = Logger.getLogger(ReplyMapperTests.class);

    private Long[] bnoArr = { 1L, 2L, 3L, 4L, 5L };

    @Autowired
    private ReplyMapper mapper;

    @Test
    public void testMapper() {
        log.info(mapper);
    }

    @Test
    public void testCreate() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ReplyVO vo = new ReplyVO();

            vo.setBno(bnoArr[i % 5]);
            vo.setReply("댓글 테스트 " + i);
            vo.setReplyer("replyer " + i);

            mapper.insert(vo);
        });
    }

    @Test
    public void testRead() {
        ReplyVO vo = mapper.read(5L);
        log.info(vo);
    }

    @Test
    public void testDelete() {
        mapper.delete(1L);
    }

    @Test
    public void testUpdate() {
        ReplyVO vo = mapper.read(6L);

        vo.setReply("Update Reply");

        int count = mapper.update(vo);
        log.info("UPDATE COUNT: " + count);
    }

    @Test
    public void testGetListWithPaging() {
        Criteria cri = new Criteria();

        List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
        replies.forEach(reply -> log.info(reply));
    }

    @Test
    public void testGetListWithPagingUsingHashmap() {
        Criteria cri = new Criteria();

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("cri", cri);
        map.put("bno", bnoArr[0]);

        List<ReplyVO> replies = mapper.getListWithPagingUsingHashMap(map);
        replies.forEach(reply -> log.info(reply));

    }

}
