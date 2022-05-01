package com.example.tanakinator2.repository;

import com.example.tanakinator2.domain.Choice;
import com.example.tanakinator2.repository.mybatis.ChoiceMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Tanakinator2RepositoryImpl implements ChoiceRepository {
    private static final Logger logger = LoggerFactory.getLogger(Tanakinator2RepositoryImpl.class);
    private final SqlSessionTemplate sqlSessionTemplate;

    public Tanakinator2RepositoryImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public List<Choice> findChoices(String choiceName) {
        return sqlSessionTemplate.getMapper(ChoiceMapper.class).find(choiceName);
    }

    @Override
    public Choice getChoice(int choiceId) {
        return sqlSessionTemplate.getMapper(ChoiceMapper.class).get(choiceId);
    }
}
