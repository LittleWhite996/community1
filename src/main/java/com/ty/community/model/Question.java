package com.ty.community.model;

import lombok.Data;

@Data
public class Question {
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
}
