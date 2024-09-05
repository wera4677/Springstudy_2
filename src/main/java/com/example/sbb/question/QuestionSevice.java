package com.example.sbb.question;

import com.example.sbb.User.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.sbb.DataNotFoundException;

@RequiredArgsConstructor
@Service
public class QuestionSevice {
    private final QuestionRepository questionRepository;

    public Page<Question> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("conteDate"));//정렬 최신순
        Pageable pageable = PageRequest.of(page,10,Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
    }

    public List<Question> getList(){
        return questionRepository.findAll();
    }
    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if(question.isPresent()) {
            return question.get();
        }else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void create(String subject, String content, SiteUser user) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setConteDate(LocalDateTime.now());
        q.setAuthor(user);
        this.questionRepository.save(q); // 질문 란에 입력된것 저장
    }

    public void modify(Question question, String subject, String content){//질문 수정
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }

    public void delete(Question question) {
        this.questionRepository.delete(question);
    }

    public void vote(Question question ,SiteUser siteUser){
        question.getVoter().add(siteUser);
        this.questionRepository.save(question);
    }
}
