package com.example.test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.test.Entity.Question;
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

    @Test
    void testJpa() {
        assertEquals(2,questionRepository.count());//행갯수 리턴
        Optional<Question> oq = questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1,this.questionRepository.count());
    }

}
