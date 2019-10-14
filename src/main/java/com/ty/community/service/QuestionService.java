package com.ty.community.service;

import com.ty.community.dto.QuestionDto;
import com.ty.community.mapper.QuestionMapper;
import com.ty.community.mapper.UserMapper;
import com.ty.community.model.Question;
import com.ty.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;


       public List<QuestionDto> list() {
        List<Question> questions=questionMapper.list();
        List<QuestionDto> questionDtoList=new ArrayList<>();
        for (Question question:questions){
            User user=userMapper.findbyId(question.getCreator());
            QuestionDto questionDto=new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    }

