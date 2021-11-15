package com.vovnenko.mypetproject.security.repository;

import com.vovnenko.mypetproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    @Query(value = "update  User  SET  refreshTokenKey=:refreshTokenKey where userId=:id")
    int updateTokenKey(String refreshTokenKey, Long id);

}
