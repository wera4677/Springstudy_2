package com.example.sbb.question;

import com.example.sbb.User.SiteUser;
import com.example.sbb.User.UserService;
import com.example.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionSevice questionSevice ;
    private final UserService userService;

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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult,
                                 Principal principal){
        if(bindingResult.hasErrors()){
            return "question_form";//질문 저장후 질문 목록으로 이동
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.questionSevice.create(questionForm.getSubject(),questionForm.getContent(),siteUser);// 질문저장
        return "redirect:/question/list";
    }
}
