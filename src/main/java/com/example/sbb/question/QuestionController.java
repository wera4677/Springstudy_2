package com.example.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionSevice questionSevice ;

    @GetMapping("/question/list")

    public String list(Model model){
        List<Question> questionList =this.questionSevice.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
}
