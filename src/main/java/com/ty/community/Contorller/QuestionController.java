package com.ty.community.Contorller;

import com.ty.community.dto.QuestionDto;
import com.ty.community.mapper.QuestionMapper;
import com.ty.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id,
                           Model model){
        QuestionDto questionDto =questionService.getbyId(id);
        model.addAttribute("question",questionDto);
        return "question";
    }
}
