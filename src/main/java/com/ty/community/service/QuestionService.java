package com.ty.community.service;

import com.ty.community.dto.QuestionDto;
import com.ty.community.mapper.QuestionMapper;
import com.ty.community.mapper.UserMapper;
import com.ty.community.model.Question;
import com.ty.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ty.community.dto.pageDto;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;

    public pageDto list(Integer page, Integer size) {
        Integer totalpage;
        pageDto pagedto = new pageDto();
        Integer totalcount=questionMapper.count();
        if (totalcount%size==0){
            totalpage=totalcount/size;
        }else {
            totalpage=totalcount/size+1;
        }

        if (page<1){
            page=1;
        }
        if (page>totalpage){
            page=totalpage;
        }
        pagedto.setPagination(totalpage,page);

           Integer offset=(page-1)*size;
        List<Question> questions=questionMapper.list(offset,size);
        List<QuestionDto> questionDtoList=new ArrayList<>();

        for (Question question:questions){
            User user=userMapper.findbyId(question.getCreator());
            QuestionDto questionDto=new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);

        }
        pagedto.setQuestions(questionDtoList);

        return pagedto;
    }
    public pageDto list1(Integer userId, Integer page, Integer size) {
        Integer totalpage;
        pageDto pagedto = new pageDto();
        Integer totalcount=questionMapper.countByUserId(userId);
        if (totalcount%size==0){
            totalpage=totalcount/size;
        }else {
            totalpage=totalcount/size+1;
        }

        if (page<1){
            page=1;
        }
        if (page>totalpage){
            page=totalpage;
        }
        pagedto.setPagination(totalpage,page);

        Integer offset=(page-1)*size;
        List<Question> questions=questionMapper.listbyUserId(userId,offset,size);
        List<QuestionDto> questionDtoList=new ArrayList<>();

        for (Question question:questions){
            User user=userMapper.findbyId(question.getCreator());
            QuestionDto questionDto=new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);

        }
        pagedto.setQuestions(questionDtoList);

        return pagedto;
    }

    public QuestionDto getbyId(Integer id) {
        Question question=questionMapper.getbyId(id);
        QuestionDto questionDto=new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        User user=userMapper.findbyId(question.getCreator());
        questionDto.setUser(user);
        return  questionDto;

    }

    public void CreateOrUpdate(Question question) {
        if (question.getId()==null){
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modified(question.getGmt_create());
            questionMapper.create(question);
        }else {
            question.setGmt_modified(System.currentTimeMillis());
            questionMapper.update(question);
        }


    }
}

