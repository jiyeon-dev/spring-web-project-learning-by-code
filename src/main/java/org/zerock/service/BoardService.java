package org.zerock.service;

import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

public interface BoardService {

    public void register(BoardVO board);

    public BoardVO get(Long no);

    public boolean modify(BoardVO board);

    public boolean remove(Long no);

//    public List<BoardVO> getList();
    public List<BoardVO> getList(Criteria cri);

    public int getTotal(Criteria cri);

    public List<BoardAttachVO> getAttachList(Long no);  // 첨부파일 조회
}
