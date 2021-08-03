package org.zerock.mapper;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

public interface BoardMapper {

    public List<BoardVO> getList();
    public List<BoardVO> getListWithPaging(Criteria cri);
    
    // CREATE : Insert 만 처리되고 생성된 PK 값을 알 필요가 업는 경우
    public void insert(BoardVO board);

    // CREATE : Insert 실행되고 생성된 PK 값 알아야하는 경우우
    public void insertSelectKey(BoardVO board);

    // READ : Select
    public BoardVO read(Long no);

    // DELETE : Delete
    public int delete(Long no);

    // UPDATE : Update
    public int update(BoardVO board);
}
