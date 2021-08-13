package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardAttachMapper;
import org.zerock.mapper.BoardMapper;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper mapper;
    @Autowired
    private BoardAttachMapper attachMapper;

    @Transactional
    @Override
    public void register(BoardVO board) {
        System.out.println("register ....... " + board);
        mapper.insertSelectKey(board);

        if (board.getAttachList() == null || board.getAttachList().size() <= 0) {
            return;
        }

        board.getAttachList().forEach(attach -> {
            attach.setBno(board.getNo());
            attachMapper.insert(attach);
        });
    };

    @Override
    public BoardVO get(Long no) {
        System.out.println("get ....... " + no);
        return mapper.read(no);
    }

    @Override
    public boolean modify(BoardVO board) {
        System.out.println("modify ....... " + board);

        attachMapper.deleteAll(board.getNo());  // 기존 첨부파일 모두 삭제
        boolean modifyResult = mapper.update(board) == 1;  // 게시글 수정

        // 첨부파일 존재하는 경우 첨부파일 추가
        if (modifyResult && board.getAttachList() != null && board.getAttachList().size() > 0) {
            board.getAttachList().forEach(attach -> {
                attach.setBno(board.getNo());
                attachMapper.insert(attach);
            });
        }

        return modifyResult;
    }
    
    @Transactional
    @Override
    public boolean remove(Long no) {
        System.out.println("remove ....... " + no);
        attachMapper.deleteAll(no);  // 첨부파일 삭제
        return mapper.delete(no) == 1;
    }

//    @Override
//    public List<BoardVO> getList() {
//        System.out.println("getList ....... ");
//        return mapper.getList();
//    }
    @Override
    public List<BoardVO> getList(Criteria cri) {
        System.out.println("get list with criteria: " + cri);
        return mapper.getListWithPaging(cri);
    }

    @Override
    public int getTotal(Criteria cri) {
        System.out.println("get total count");
        return mapper.getTotalCount(cri);
    }

    @Override
    public List<BoardAttachVO> getAttachList(Long no) {
        System.out.println("get Attach list by bno: " + no);
        return attachMapper.findByBno(no);
    }
}
