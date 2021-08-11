package org.zerock.mapper;

import org.zerock.domain.BoardAttachVO;

import java.util.List;

public interface BoardAttachMapper {

    public void insert(BoardAttachVO vo);

    public void delete(String uuid);

    public List<BoardAttachVO> findByBno(Long bno);

    public void deleteAll(Long bno);  // 게시물 삭제시 관련 첨부파일 모두 삭제
    
    public List<BoardAttachVO> getOldFiles();  // 어제 등록된 파일 조회

}
