package com.example.tanakinator2.repository.mybatis;

import com.example.tanakinator2.domain.Choice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChoiceMapper {
    List<Choice> find(@Param("choiceName") String choiceName);
    Choice get(@Param("choiceId") int choiceId);
}
