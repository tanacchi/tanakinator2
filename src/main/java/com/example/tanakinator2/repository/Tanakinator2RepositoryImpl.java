package com.example.tanakinator2.repository;

import com.example.tanakinator2.domain.Choice;
import com.example.tanakinator2.domain.Question;
import com.example.tanakinator2.repository.mybatis.ChoiceMapper;
import com.example.tanakinator2.repository.mybatis.QuestionMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Tanakinator2RepositoryImpl implements Tanakinator2Repository {
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

    @Override
    public Question getQuestionById(int questionId) {
        Question question = sqlSessionTemplate.getMapper(QuestionMapper.class).get(questionId);
        if (question == null) {
            logger.info("Question not found (id = {}).", question.getQuestionId());
            throw new ResourceNotFoundException("Question not found.");
        }
        return question;
    }

    @Override
    public List<Question> getQuestionsById(List<Integer> questionIds) {
        List<Question> result = new ArrayList<>();
        for (int id: questionIds) {  // TODO: refactor
            result.add(getQuestionById(id));
        }
        return result;
    }

    @Override
    public Question lockQuestion(int questionId) {
        Question question = sqlSessionTemplate.getMapper(QuestionMapper.class).lock(questionId);
        if (question == null) {
            logger.info("Question not found (id = {}).", question.getQuestionId());
            throw new ResourceNotFoundException("Question not found.");
        }
        return question;
    }

    @Override
    public void insertQuestion(Question question) {
        sqlSessionTemplate.getMapper(QuestionMapper.class).add(question);
    }

    @Override
    public void updateQuestion(Question question) {
        int affected = sqlSessionTemplate.getMapper(QuestionMapper.class).set(question);
        if (affected != 1) {
            logger.info("Question not found (id = {}).", question.getQuestionId());
            throw new ResourceNotFoundException("Question not found.");
        }
    }

    @Override
    public void deleteQuestion(Question question) {
        int affected = sqlSessionTemplate.getMapper(QuestionMapper.class).delete(question);
        if (affected != 1) {
            logger.info("Question not found (id = {}).", question.getQuestionId());
            throw new ResourceNotFoundException("Question not found.");
        }
    }
}
