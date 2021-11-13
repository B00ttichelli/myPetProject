package com.vovnenko.mypetproject.mapper;

import com.vovnenko.mypetproject.dto.SubForumDto;
import com.vovnenko.mypetproject.model.SubForum;
import org.mapstruct.Mapper;

@Mapper
public interface SubForumMapper {

    SubForumDto subForumToSubForumDto (SubForum subForum);
    SubForum subForumDtoToSubForum (SubForumDto subForumDto);

}
