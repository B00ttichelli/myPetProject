package com.vovnenko.mypetproject.repository;

import com.vovnenko.mypetproject.model.SubForum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubForumRepository extends JpaRepository<SubForum,Long> {
    @Modifying
    @Query("update SubForum f  set  f.subForumName = :subForumName, f.description = :desc where f.id = :id")
    void  updateNameOrDescription(@Param(value = "id") Long id,
                                  @Param(value = "subForumName") String subForumName,
                                  @Param(value = "desc") String description);

}
