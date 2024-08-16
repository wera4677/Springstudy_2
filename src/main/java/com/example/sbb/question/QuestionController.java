package com.example.sbb.question;

import com.example.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionSevice questionSevice ;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page",defaultValue = "0") int page){
        Page<Question> paging =this.questionSevice.getList(page);
        model.addAttribute("paging",paging);
        return "question_list";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        Question question = this.questionSevice.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "question_form";
    }
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "question_form";//질문 저장후 질문 목록으로 이동
        }
        this.questionSevice.create(questionForm.getSubject(),questionForm.getContent());// 질문저장
        return "redirect:/question/list";
    }
}
