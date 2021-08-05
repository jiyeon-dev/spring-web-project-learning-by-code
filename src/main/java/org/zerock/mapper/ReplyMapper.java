package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import java.util.List;

public interface ReplyMapper {

    public int insert (ReplyVO vo);

    public ReplyVO read(Long rno);  // 특정 댓글 읽기

    public int delete(Long rno);

    public int update (ReplyVO reply);

    public List<ReplyVO> getListWithPaging(
            @Param("cri") Criteria cri,
            @Param("bno") Long bno
    );

}
