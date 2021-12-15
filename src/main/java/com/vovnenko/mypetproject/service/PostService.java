package com.vovnenko.mypetproject.service;

import com.vovnenko.mypetproject.dto.PostDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    PostDto create(PostDto postDto, String name);

    List<PostDto> findAllByForumIdPageable(Long id, Pageable pageable);

    PostDto getById(Long id);
}
