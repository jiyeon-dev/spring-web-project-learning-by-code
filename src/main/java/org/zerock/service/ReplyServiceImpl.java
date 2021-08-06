package org.zerock.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import java.util.List;

@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private static final Logger log = LoggerFactory.getLogger(ReplyServiceImpl.class);

    @Setter(onMethod_=@Autowired)
    private ReplyMapper mapper;

//    public ReplyServiceImpl(ReplyMapper mapper) {
//        this.mapper = mapper;
//    }

    @Override
    public int register(ReplyVO vo) {
        log.info("register ... " + vo);
        return mapper.insert(vo);
    }

    @Override
    public ReplyVO get(Long rno) {
        log.info("get ... " + rno);
        return mapper.read(rno);
    }

    @Override
    public int modify(ReplyVO vo) {
        log.info("modify ... " + vo);
        return mapper.update(vo);
    }

    @Override
    public int remove(Long rno) {
        log.info("remove ... " + rno);
        return mapper.delete(rno);
    }

    @Override
    public List<ReplyVO> getList(Criteria cri, Long bno) {
        log.info("get Reply List of a Board " + bno);
        return mapper.getListWithPaging(cri, bno);
    }

}
