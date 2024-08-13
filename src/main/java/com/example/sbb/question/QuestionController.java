package com.example.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionSevice questionSevice ;

    @GetMapping("/list")

    public String list(Model model){
        List<Question> questionList =this.questionSevice.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model,@PathVariable("id") Integer id){
        Question question = this.questionSevice.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }
    @GetMapping("/create")
    public String questionCreate(){
        return "question_form";
    }
    @PostMapping("/create")
    public String questionCreate(@RequestParam(value = "subject") String subject,
                                 @RequestParam(value = "content") String content){
        this.questionSevice.create(subject,content);// 질문저장
        return "redirect:/question/list";//질문 저장후 질문 목록으로 이동
    }
}
