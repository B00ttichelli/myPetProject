package com.vovnenko.mypetproject.mapper;

import com.vovnenko.mypetproject.dto.PostDto;
import com.vovnenko.mypetproject.model.Post;
import com.vovnenko.mypetproject.model.SubForum;
import com.vovnenko.mypetproject.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PostMapper {

    Post postDtoToPost(PostDto postDto);


    @Mapping(target = "userId", expression = "java(mapUserId(post.getUser()))")
    @Mapping(target = "subForumId", expression = "java(mapForumId(post.getSubForum()))")
    PostDto postToPostDto(Post post);

    default Long mapForumId(SubForum subForum) {
        return subForum.getId();
    }

    default Long mapUserId(User user) {
        return user.getUserId();
    }






}
