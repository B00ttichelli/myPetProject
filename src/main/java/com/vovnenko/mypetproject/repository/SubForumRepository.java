package com.vovnenko.mypetproject.repository;

import com.vovnenko.mypetproject.model.SubForum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubForumRepository extends JpaRepository<SubForum,Long> {
}
