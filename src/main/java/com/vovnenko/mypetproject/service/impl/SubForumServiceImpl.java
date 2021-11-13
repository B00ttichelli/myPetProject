package com.vovnenko.mypetproject.service.impl;

import com.vovnenko.mypetproject.dto.SubForumDto;
import com.vovnenko.mypetproject.mapper.SubForumMapper;
import com.vovnenko.mypetproject.model.SubForum;
import com.vovnenko.mypetproject.repository.SubForumRepository;
import com.vovnenko.mypetproject.service.SubForumService;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubForumServiceImpl implements SubForumService {
    private final SubForumMapper subForumMapper;
    private final SubForumRepository subForumRepository;

    @Override
    @Transactional
    public List<SubForumDto> getAll() {
        return subForumRepository.findAll().stream().map(subForumMapper::subForumToSubForumDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SubForumDto save(SubForumDto subForumDto) {
        SubForum subForum = subForumMapper.subForumDtoToSubForum(subForumDto);
        SubForum save = subForumRepository.save(subForum);
        return subForumMapper.subForumToSubForumDto(save);

    }
}
