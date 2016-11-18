package com.example.monitor.domain.models;


import javax.persistence.*;

/**
 * Created by Administrator on 2016/11/18.
 */
@Entity
@Table(name = "ping_ip_result")
public class PingIpResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Enum networkStatus;

    @Column(nullable = false)
    private String lost;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String statusName;

    @Column(nullable = false)
    private Integer ipInfoId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Enum getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(Enum networkStatus) {
        this.networkStatus = networkStatus;
    }

    public String getLost() {
        return lost;
    }

    public void setLost(String lost) {
        this.lost = lost;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getIpInfoId() {
        return ipInfoId;
    }

    public void setIpInfoId(Integer ipInfoId) {
        this.ipInfoId = ipInfoId;
    }
}