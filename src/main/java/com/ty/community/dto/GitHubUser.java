package com.ty.community.dto;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;

}
