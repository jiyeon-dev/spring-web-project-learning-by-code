package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class Criteria {

    // paging
    private int pageNum;
    private int amount;

    // search
    private String type;
    private String keyword;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    // Method for MyBatis SQL Mapper
    public int getPageStart() {
        return (this.pageNum - 1) * this.amount;
    }

    // Method for MyBatis SQL Mapper
    public int getPageEnd() {
        return this.pageNum * this.amount;
    }

    public Criteria() {
        this(1, 10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public String[] getTypeArr() {
        return type == null ? new String[] {} : type.split("");
    }

    public String getListLink() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
            .queryParam("pageNum", this.getPageNum())
            .queryParam("amount", this.getAmount())
            .queryParam("type", this.getType())
            .queryParam("keyword", this.getKeyword());
        return builder.toUriString();
    }

    @Override
    public String toString() {
        return "Criteria [pageNum=" + pageNum + ", amount=" + amount + "]";
    }
}
