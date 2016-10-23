package com.example.monitor.domain.models;

import javax.persistence.*;
import javax.validation.constraints.Max;

/**
 * Created by Administrator on 2016/10/15.
 */
@Entity
@Table(name = "ip_info")
public class IpInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String ipAddr;

    @Basic
    @Column(length = 100)
    private String name;

    @Basic
    @Column(length = 500)
    private String commit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }
}
