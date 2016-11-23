package com.example.monitor.repositories;

import com.example.monitor.domain.models.Help;
import com.example.monitor.domain.models.IpInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */
public interface HelpRepository extends JpaRepository<Help, Integer> {

    @Transactional
    @Modifying
    @Query(value="update Help h set h.question =:question, h.answer=:answer where h.id = :id")
    int updateHelp(@Param("question") String question, @Param("answer") String answer, @Param("id") Integer id);
}
