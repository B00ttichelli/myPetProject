package com.vovnenko.mypetproject.controller.admin;

import com.vovnenko.mypetproject.dto.SubForumDto;
import com.vovnenko.mypetproject.service.SubForumService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminForumController {


    private final SubForumService subForumService;

    @PostMapping("/forums/create")
    public ResponseEntity<SubForumDto> createSubForum(@RequestBody SubForumDto subForumDto){

        return new ResponseEntity<>(subForumService.save(subForumDto), HttpStatus.CREATED);
    }

    @PutMapping("/forums/update")
    public ResponseEntity<SubForumDto> updateSubForum(@RequestBody SubForumDto subForumDto){
            SubForumDto response = subForumService.update(subForumDto);

        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }


}
