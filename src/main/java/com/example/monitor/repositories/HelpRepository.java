package com.example.monitor.repositories;

import com.example.monitor.domain.models.Help;
import com.example.monitor.domain.models.IpInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */
public interface HelpRepository extends JpaRepository<Help, Integer> {

}
