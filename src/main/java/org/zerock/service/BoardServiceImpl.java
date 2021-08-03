package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper mapper;

    @Override
    public void register(BoardVO board) {
        System.out.println("register ....... " + board);
        mapper.insertSelectKey(board);
    };

    @Override
    public BoardVO get(Long no) {
        System.out.println("get ....... " + no);
        return mapper.read(no);
    }

    @Override
    public boolean modify(BoardVO board) {
        System.out.println("modify ....... " + board);
        return mapper.update(board) == 1;
    }

    @Override
    public boolean remove(Long no) {
        System.out.println("remove ....... " + no);
        return mapper.delete(no) == 1;
    }

    @Override
    public List<BoardVO> getList() {
        System.out.println("getList ....... ");
        return mapper.getList();
    }

}