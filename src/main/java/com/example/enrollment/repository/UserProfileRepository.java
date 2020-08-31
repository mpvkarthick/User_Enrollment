package com.example.enrollment.repository;

import com.example.enrollment.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {

    @Query("select u from UserProfile u where u.uname=:uname")
    public UserProfile findByUname(@Param("uname") String uname);

    @Modifying
    @Query("update UserProfile u set u.hpwd=:hpwd, u.salt=:salt where u.id=:id")
    public void updatePassword(@Param("hpwd") String hpwd, @Param("salt") String salt, @Param("id") Long id);
}
