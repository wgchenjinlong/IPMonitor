package com.example.monitor.repositories;

import com.example.monitor.domain.dtos.IpInfoDto;
import com.example.monitor.domain.models.IpInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */
public interface IpInfoRepository extends JpaRepository<IpInfo, Integer> {

    @Query("from IpInfo info where info.ipAddr = :ipAddr")
    List<IpInfo> findByIp(@Param("ipAddr") String ipAddr);

    @Query("from IpInfo info, PingIpResult res where info.id = res.ipInfoId")
    List<IpInfoDto> findIpInfos();
}
