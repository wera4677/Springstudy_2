package com.example.sbb;

import java.util.List;
import java.util.Optional;

import com.example.sbb.answer.Answer;
import com.example.sbb.question.Question;
import com.example.sbb.answer.AnswerRepository;
import com.example.sbb.question.QuestionRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    @Test
    void testJpa() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswersList();

        assertEquals(1,answerList.size());
        assertEquals("네 자동으로 생성됩니다.",answerList.get(0).getContent());
    }

}
