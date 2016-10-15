package com.example.monitor.repositories;

import com.example.monitor.domain.models.IpInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */
public interface IpInfoRepository extends JpaRepository<IpInfo, Integer> {

    public List<IpInfo> findAll();
}
