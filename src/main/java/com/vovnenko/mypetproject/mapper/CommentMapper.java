package com.vovnenko.mypetproject.mapper;

import com.vovnenko.mypetproject.dto.CommentDto;
import com.vovnenko.mypetproject.model.Comment;
import com.vovnenko.mypetproject.model.Post;
import com.vovnenko.mypetproject.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CommentMapper {

    Comment commentDtoToComment(CommentDto commentDto);

    @Mapping(target = "userId", expression = "java(mapUserId(comment.getUser()))")
    @Mapping(target = "postId", expression = "java(mapPostId(comment.getPost()))")
    CommentDto commentToCommentDto(Comment comment);

    default Long mapUserId(User user){ return user.getUserId();}
    default Long mapPostId(Post post){ return  post.getPostId();}
}
