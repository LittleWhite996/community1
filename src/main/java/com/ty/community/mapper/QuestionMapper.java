package com.ty.community.mapper;

import com.ty.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,creator,description,tag,gmt_create,gmt_modified) values(#{title},#{creator},${description},#{tag},#{gmt_create},#{gmt_modified})")
    void create(Question question);
}
