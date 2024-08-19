package com.example.sbb.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor //생성자 주입
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm){
        return "signup_form";
    }


    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "signup_form";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){//비밀번호 확인
            bindingResult.rejectValue("password2","passwordInCorrect","2개의 비밀번호가 일치하지 않습니다.");
            return "signup_form";
        }

        userService.create(userCreateForm.getUsername(),userCreateForm.getEmail(),userCreateForm.getPassword1());
        return "redirect:/";

    }
}
