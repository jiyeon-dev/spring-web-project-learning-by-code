package org.zerock.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TodoDTO {

    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String toString() {
        return "TodoDTO [title=" + title + ", dueDate=" + dueDate + "]";
    }
}
