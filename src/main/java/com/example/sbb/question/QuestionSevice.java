package com.example.sbb.question;

import com.example.sbb.User.SiteUser;
import com.example.sbb.answer.Answer;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    private Specification<Question> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true); //중복제거
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"),"%" + kw +"%"),
                        cb.like(q.get("content"),"%" + kw +"%"),
                        cb.like(u1.get("username"),"%" + kw +"%"),
                        cb.like(a.get("content"),"%" + kw +"%"),
                        cb.like(u2.get("username"),"%" + kw +"%"));
            }
        };

    }

    public Page<Question> getList(int page,String kw){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("conteDate"));//정렬 최신순
        Pageable pageable = PageRequest.of(page,10,Sort.by(sorts));
        Specification<Question> spec = search(kw);
        return this.questionRepository.findAll(spec,pageable);
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
