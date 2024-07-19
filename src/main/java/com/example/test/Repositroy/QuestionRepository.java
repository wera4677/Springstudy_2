package com.example.test.Repositroy;


import com.example.test.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    Question findBySubject(String subject); // 제목
    Question findBySubjectAndContent(String subject, String content);//제목과 내용
    List<Question> findBySubjectLike(String subject); //특수문자
}
