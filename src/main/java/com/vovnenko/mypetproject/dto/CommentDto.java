package com.vovnenko.mypetproject.dto;

import com.vovnenko.mypetproject.model.Post;
import com.vovnenko.mypetproject.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long commentId;
    @NotBlank(message = "Comment cant be empty")
    private String text;

    private Long userId;

    private Long postId;

    private Instant createDate;

}
