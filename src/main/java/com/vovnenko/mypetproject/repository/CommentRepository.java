package com.vovnenko.mypetproject.repository;

import com.vovnenko.mypetproject.model.Comment;
import com.vovnenko.mypetproject.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Page<Comment> findAllByPost(Post post, Pageable pageable);
    Page<Comment> findAllByPost_PostId(Long id, Pageable pageable);


}
