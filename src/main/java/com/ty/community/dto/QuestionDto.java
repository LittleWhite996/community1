package com.ty.community.dto;

import com.ty.community.model.User;
import lombok.Data;

@Data
public class QuestionDto {
    private Integer id;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer creator;
    private String tag;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private User user;
}
