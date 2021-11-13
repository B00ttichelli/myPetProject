package com.vovnenko.mypetproject.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubForumDto {

    private Long id;
    private String subForumName;
    private String description;
    private Integer numberOfPosts;
}
