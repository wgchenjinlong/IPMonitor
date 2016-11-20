package com.example.monitor.repositories;

import com.example.monitor.domain.models.PingIpResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */
public interface PingIpResultRepository extends JpaRepository<PingIpResult, Integer> {


    @Query("from PingIpResult res where res.ipInfoId = :ipInfoId")
    List<PingIpResult> findByIpInfoId(@Param("ipInfoId") Integer ipInfoId);

}
