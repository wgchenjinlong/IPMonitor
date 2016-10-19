package com.example.monitor.repositories;

import com.example.monitor.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("from User u where u.username= :username")
    List<User> findByUsername(@Param("username") String username);
}
