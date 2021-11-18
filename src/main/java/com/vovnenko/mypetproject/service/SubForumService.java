package com.vovnenko.mypetproject.service;

import com.vovnenko.mypetproject.dto.SubForumDto;

import java.util.List;

public interface SubForumService {

    SubForumDto save(SubForumDto subForumDto);

    List<SubForumDto> getAll();

    SubForumDto update(SubForumDto subForumDto);
}
