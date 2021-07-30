package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeMapper {

    @Select("SELECT SYSDATE()")
    public String getTime();
}
