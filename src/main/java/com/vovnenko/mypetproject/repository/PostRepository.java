package com.vovnenko.mypetproject.repository;

import com.vovnenko.mypetproject.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Post getByPostName (String postName);

}
