package org.zerock.domain;

public class Criteria {

    private int pageNum;
    private int amount;

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

    @Override
    public String toString() {
        return "Criteria [pageNum=" + pageNum + ", amount=" + amount + "]";
    }
}
