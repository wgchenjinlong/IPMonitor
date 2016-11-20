package com.example.monitor.repositories;

import com.example.monitor.domain.dtos.IpInfoDto;
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
public interface IpInfoRepository extends JpaRepository<IpInfo, Integer> {

    @Query("from IpInfo info where info.ipAddr = :ipAddr")
    List<IpInfo> findByIp(@Param("ipAddr") String ipAddr);

    @Transactional
    @Modifying
    @Query(value="update IpInfo info set info.lost=:lost, info.color=:color, info.statusName = :statusName where info.id = :id")
    int updateIpInfo(@Param("lost") String lost, @Param("color") String color, @Param("statusName") String statusName, @Param("id") Integer id);
}
