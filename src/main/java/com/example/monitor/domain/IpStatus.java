package com.example.monitor.domain;

public enum IpStatus {

    ERROR("异常","danger"),
    NORMAL("正常", "");

    private String color;
    private String statusName;

    public String getColor() {
        return color;
    }

    public String getStatusName() {
        return statusName;
    }

    IpStatus(String statusName, String color) {
        this.statusName = statusName;
        this.color = color;
    }
}
