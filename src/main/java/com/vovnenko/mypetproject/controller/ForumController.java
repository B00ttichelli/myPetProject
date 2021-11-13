package com.vovnenko.mypetproject.controller;

import com.vovnenko.mypetproject.dto.SubForumDto;
import com.vovnenko.mypetproject.model.SubForum;
import com.vovnenko.mypetproject.service.SubForumService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forums")
@AllArgsConstructor

public class ForumController {


    private final SubForumService subForumService;

    @PostMapping("/create")
    public ResponseEntity<SubForumDto> createSubForum(@RequestBody SubForumDto subForumDto){

        return new ResponseEntity<>(subForumService.save(subForumDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SubForumDto>> getAllForums(){
            return new ResponseEntity<>(subForumService.getAll(),HttpStatus.OK);
    }

}
