package org.zerock.domain;

import lombok.ToString;

import java.util.List;

@ToString
public class ReplyPageDTO {

    private int replyCnt;        // 전체 댓글 수
    private List<ReplyVO> list;

    public int getReplyCnt() {
        return replyCnt;
    }

    public void setReplyCnt(int replyCnt) {
        this.replyCnt = replyCnt;
    }

    public List<ReplyVO> getList() {
        return list;
    }

    public void setList(List<ReplyVO> list) {
        this.list = list;
    }

    public ReplyPageDTO(int replyCnt, List<ReplyVO> list) {
        this.replyCnt = replyCnt;
        this.list = list;
    }
}
