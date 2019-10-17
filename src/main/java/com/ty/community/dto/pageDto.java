package com.ty.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class pageDto {
    private List<QuestionDto> questions;
    private boolean showprevious;
    private boolean showfirstpage;
    private boolean shownext;
    private boolean showendpage;
    private Integer page;
    private List<Integer> pages=new ArrayList<>();
    private Integer totalpage;
    public void setPagination(Integer totalpage, Integer page) {
        this.totalpage=totalpage;
        this.page=page;

        this.page=page;
        pages.add(page);
        for (int i=1;i<=3;i++){
            if (page-i>0){
                pages.add(0,page-i);
            }
            if (page+i<=totalpage){
                pages.add(page+i);
            }
        }

        if (page==1){
            showprevious=false;
        }else {
            showprevious=true;
        }
        if (page==totalpage){
            shownext=false;
        }else {
            shownext=true;
        }

        if (pages.contains(1)){
            showfirstpage=false;
        }else {
            showfirstpage=true;
        }
        if (pages.contains(totalpage)){
            showendpage=false;
        }else {
            showendpage=true;
        }

    }
}
