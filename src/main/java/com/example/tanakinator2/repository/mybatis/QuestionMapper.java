package com.example.tanakinator2.repository.mybatis;

import com.example.tanakinator2.domain.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {
    Question get(@Param("questionId") int questionId);
    Question lock(@Param("questionId") int questionId);
    int add(Question question);
    int set(Question question);
    int delete(Question question);
}
