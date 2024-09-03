package com.example.sbb.answer;


import com.example.sbb.User.SiteUser;
import com.example.sbb.User.UserService;
import com.example.sbb.question.Question;
import com.example.sbb.question.QuestionSevice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@RequestMapping("/answer") //프리픽스 설정
@RequiredArgsConstructor //의존성 주입
@Controller //컨트롤러 명시
public class AnswerController {
    private final QuestionSevice questionSevice;
    private final AnswerService answerService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()") //로그인 필요
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id,
                               @Valid AnswerForm answerForm, BindingResult bindingResult,
                                Principal principal) {
        Question question = this.questionSevice.getQuestion(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
        this.answerService.create(question,answerForm.getContent(),siteUser);
        return String.format("redirect:/question/%s", id);
    }

}
