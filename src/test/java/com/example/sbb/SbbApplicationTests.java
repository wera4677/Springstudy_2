package com.example.sbb;

import java.util.List;
import java.util.Optional;

import com.example.sbb.answer.Answer;
import com.example.sbb.question.Question;
import com.example.sbb.answer.AnswerRepository;
import com.example.sbb.question.QuestionRepository;
import com.example.sbb.question.QuestionSevice;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class SbbApplicationTests {

    @Autowired //의존성 주입
    private QuestionSevice questionSevice;

    @Test
    void testJpa() {
        for (int i =1; i<=300;i++){
            String subject = String.format("테스트 테이터입니다:[%03d]",i); //300개의 테스트
            String content = "내용 없음";
            this.questionSevice.create(subject,content,null);
        }
    }

}
