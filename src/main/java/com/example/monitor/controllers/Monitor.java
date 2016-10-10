package com.example.monitor.controllers;

import com.example.monitor.models.IpStatus;
import com.example.monitor.models.dtos.IpInfo;
import com.example.monitor.services.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class Monitor {

    @Autowired
    private MonitorService monitorService;

    @RequestMapping("/monitor")
    public String index(Map<String, Object> model) {

        List<IpInfo> list = new ArrayList();
        List<String> ipList = monitorService.getIpList();

        ipList.forEach(i -> {
            IpInfo ipInfo = new IpInfo();
            ipInfo.setIpAddress(i);
            ipInfo.setStatus(IpStatus.NORMAL);
            list.add(ipInfo);
        });
        System.out.println(11);
        model.put("ipInfos", list);
        return "monitor/index";
    }

//    @RequestMapping("/ping")
//    @ResponseBody
//    public List<IpInfo> ping() {
//        List<IpInfo> list = new ArrayList();
//        List<String> ipList = monitorService.getIpList();
//
//        ipList.forEach(i -> {
//            IpInfo ipInfo = new IpInfo();
//            ipInfo.setIpAddress(i);
//            boolean pingResult = monitorService.ping(i);
//            ipInfo.setStatus(pingResult ? IpStatus.NORMAL : IpStatus.ERROR);
//            ipInfo.setColor(pingResult ? IpStatus.NORMAL.getColor() : IpStatus.ERROR.getColor());
//            ipInfo.setStatusName(pingResult ? IpStatus.NORMAL.getStatusName() : IpStatus.ERROR.getStatusName());
//            list.add(ipInfo);
//        });
//        return list;
//    }

    @RequestMapping("/monitor/ping")
    @ResponseBody
    public IpInfo ping(@RequestParam String ipAddress) {
        IpInfo ipInfo = new IpInfo();
        ipInfo.setIpAddress(ipAddress);
        boolean pingResult = monitorService.ping(ipAddress);
        ipInfo.setStatus(pingResult ? IpStatus.NORMAL : IpStatus.ERROR);
        ipInfo.setColor(pingResult ? IpStatus.NORMAL.getColor() : IpStatus.ERROR.getColor());
        ipInfo.setStatusName(pingResult ? IpStatus.NORMAL.getStatusName() : IpStatus.ERROR.getStatusName());
        return ipInfo;
    }
}
