package com.vovnenko.mypetproject.service;

import com.vovnenko.mypetproject.dto.CommentDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    CommentDto create(CommentDto commentDto, String name);

    List<CommentDto> findAllByPostIdPageable(Long id, Pageable pageable);
}
