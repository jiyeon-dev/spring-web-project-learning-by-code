package org.zerock.service;

import org.zerock.domain.BoardVO;

import java.util.List;

public interface BoardService {

    public void register(BoardVO board);

    public BoardVO get(Long no);

    public boolean modify(BoardVO board);

    public boolean remove(Long no);

    public List<BoardVO> getList();

}
