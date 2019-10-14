package com.ty.community.Controller;

import com.ty.community.dto.QuestionDto;
import com.ty.community.mapper.QuestionMapper;
import com.ty.community.mapper.UserMapper;
import com.ty.community.model.Question;
import com.ty.community.model.User;
import com.ty.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
                        Model model){
        Cookie[] cookies =request.getCookies();
        if (cookies!=null&&cookies.length!=0){
            for(Cookie cookie:cookies){
                if (cookie.getName().equals("token")){
                    String token=cookie.getValue();
                    User user=userMapper.findbytoken(token);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        List<QuestionDto> questionList= questionService.list();
        System.out.println(questionList);
        model.addAttribute("questions",questionList);
        return "index";
    }
}
