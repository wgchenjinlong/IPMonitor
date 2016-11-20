package com.example.monitor.domain;

import java.text.NumberFormat;

public class PingResult {

    private Enum networkStatus;

    private String lost;

    private String color;

    private String statusName;

    public String getColor() {
        return color;
    }

    public String getStatusName() {
        return statusName;
    }

    public Enum getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(double percent) {

        if (percent == 0) {
            // 网络异常
            this.networkStatus = IpStatus.ERROR;
            this.color = IpStatus.ERROR.getColor();
            this.statusName = IpStatus.ERROR.getStatusName();
        } else if (percent == 1) {
            // 网络良好
            this.networkStatus = IpStatus.NORMAL;
            this.color = IpStatus.NORMAL.getColor();
            this.statusName = IpStatus.NORMAL.getStatusName();
        } else {
            // 网络阻塞
            this.networkStatus = IpStatus.CONGESTION;
            this.color = IpStatus.CONGESTION.getColor();
            this.statusName = IpStatus.CONGESTION.getStatusName();
        }
    }

    public String getLost() {
        return lost;
    }

    public void setLost(double percent) {
        NumberFormat nt = NumberFormat.getPercentInstance();
        nt.setMinimumFractionDigits(0);
        this.lost = nt.format(percent);
    }
}
