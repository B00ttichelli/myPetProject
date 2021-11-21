package com.vovnenko.mypetproject.service;

import com.vovnenko.mypetproject.dto.PostDto;
import com.vovnenko.mypetproject.model.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    PostDto create(PostDto postDto);

    List<PostDto> findAllByForumIdPageable(Long id, Pageable pageable);

}
