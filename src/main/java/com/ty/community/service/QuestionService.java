package com.ty.community.service;

import com.ty.community.dto.QuestionDto;
import com.ty.community.mapper.QuestionMapper;
import com.ty.community.mapper.UserMapper;
import com.ty.community.model.Question;
import com.ty.community.model.QuestionExample;
import com.ty.community.model.User;
import org.apache.ibatis.session.RowBounds;
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
        Integer totalcount=(int)questionMapper.countByExample(new QuestionExample());
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
        List<Question> questions=questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
        List<QuestionDto> questionDtoList=new ArrayList<>();

        for (Question question:questions){
            User user=userMapper.selectByPrimaryKey(question.getCreator());
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
        QuestionExample questionExample=new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalcount=(int)questionMapper.countByExample(questionExample);
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
        QuestionExample example=new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions=questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));
        List<QuestionDto> questionDtoList=new ArrayList<>();

        for (Question question:questions){
            User user=userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto=new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);

        }
        pagedto.setQuestions(questionDtoList);

        return pagedto;
    }

    public QuestionDto getbyId(Integer id) {
        Question question=questionMapper.selectByPrimaryKey(id);
        QuestionDto questionDto=new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        User user=userMapper.selectByPrimaryKey(question.getCreator());
        questionDto.setUser(user);
        return  questionDto;

    }

    public void CreateOrUpdate(Question question) {
        if (question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else {
            Question updatequestion=new Question();
            updatequestion.setGmtModified(System.currentTimeMillis());
            updatequestion.setTitle(question.getTitle());
            updatequestion.setDescription(question.getDescription());
            updatequestion.setTag(question.getTag());
            QuestionExample example=new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(updatequestion,example);
        }


    }
}

