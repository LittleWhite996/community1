package com.ty.community.Controller;

import com.ty.community.dto.QuestionDto;
import com.ty.community.dto.pageDto;
import com.ty.community.mapper.QuestionMapper;
import com.ty.community.mapper.UserMapper;
import com.ty.community.model.Question;
import com.ty.community.model.User;
import com.ty.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class indexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){

        pageDto pagination= questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
