package org.zerock.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class SampleDTOList {

    private List<SampleDTO> list;

    public SampleDTOList() {
        list = new ArrayList<SampleDTO>();
    }

    public List<SampleDTO> getList() {
        return list;
    }

    public void setList(List<SampleDTO> list) {
        this.list = list;
    }
}
