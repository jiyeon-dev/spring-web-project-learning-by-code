package org.zerock.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import java.util.HashMap;
import java.util.List;

public interface ReplyMapper {

    public int insert (ReplyVO vo);

    public ReplyVO read(Long rno);  // 특정 댓글 읽기

    public int delete(Long rno);

    public int update (ReplyVO reply);

    public List<ReplyVO> getListWithPaging(  // @Param 이용
            @Param("cri") Criteria cri,
            @Param("bno") Long bno
    );
    public List<ReplyVO> getListWithPagingUsingHashMap(HashMap<String, Object> map);  // Hashmap 이용

    public int getCountByBno(Long bno);  // 댓글 총 수

}
