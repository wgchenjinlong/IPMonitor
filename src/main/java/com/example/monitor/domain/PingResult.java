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

//        System.out.println(percent);
        if (percent == 0) {
            // 网络异常
            this.networkStatus = IpStatus.ERROR;
            this.color = IpStatus.ERROR.getColor();
            this.statusName = IpStatus.ERROR.getStatusName();
            this.lost = "100%";
        } else if (percent == 1) {
            // 网络良好
            this.networkStatus = IpStatus.NORMAL;
            this.color = IpStatus.NORMAL.getColor();
            this.statusName = IpStatus.NORMAL.getStatusName();
            this.lost = "0%";
        } else {
            // 网络阻塞
            this.networkStatus = IpStatus.CONGESTION;
            this.color = IpStatus.CONGESTION.getColor();
            this.statusName = IpStatus.CONGESTION.getStatusName();
            NumberFormat nt = NumberFormat.getPercentInstance();
            nt.setMinimumFractionDigits(0);
            this.lost = nt.format(1-percent);
        }
    }

    public String getLost() {
        return lost;
    }

}
