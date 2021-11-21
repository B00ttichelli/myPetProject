package com.vovnenko.mypetproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {


    private Long postId;

    @NotBlank(message = "Post name can't be empty")
    private String postName;

    /*private String url;*/

    private String description;


    private Long userId;

    private Long subForumId;

    @NotBlank(message = "Post body can't be empty")
    private String postBody;

}
