package com.vovnenko.mypetproject.service;

import com.vovnenko.mypetproject.dto.CommentDto;

public interface CommentService {

    CommentDto create(CommentDto commentDto, String name);
}
