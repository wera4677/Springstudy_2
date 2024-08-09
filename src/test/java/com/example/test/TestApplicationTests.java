package com.example.test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.test.Entity.Answer;
import com.example.test.Entity.Question;
import com.example.test.Repositroy.AnswerRepository;
import com.example.test.Repositroy.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class TestApplicationTests {

    @Autowired //의존성 주입
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void testJpa() {
        Optional<Answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2,a.getQuestion().getId());
    }

}
