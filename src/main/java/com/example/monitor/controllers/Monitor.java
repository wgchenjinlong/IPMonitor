package com.example.monitor.controllers;

import com.example.monitor.models.IpStatus;
import com.example.monitor.models.dtos.IpInfo;
import com.example.monitor.services.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenjinlong on 16/9/19.
 */
@Controller
public class Monitor {

    @Autowired
    private MonitorService monitorService;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {

        System.out.println("2222");
        List<IpInfo> list = new ArrayList();
        List<String> ipList = monitorService.getIpList();

        ipList.forEach(i -> {
            IpInfo ipInfo = new IpInfo();
            ipInfo.setIpAddress(i);
            ipInfo.setStatus(IpStatus.NORMAL);
            list.add(ipInfo);
        });

        model.put("ipInfos", list);

        return "monitor/index";
    }

    @RequestMapping("/ping")
    @ResponseBody
    public List<IpInfo> ping() {
        List<IpInfo> list = new ArrayList();
        List<String> ipList = monitorService.getIpList();

        ipList.forEach(i -> {
            IpInfo ipInfo = new IpInfo();
            ipInfo.setIpAddress(i);
            boolean pingResult = monitorService.ping(i);
            ipInfo.setStatus(pingResult ? IpStatus.NORMAL : IpStatus.ERROR);
            ipInfo.setColor(pingResult ? IpStatus.NORMAL.getColor() : IpStatus.ERROR.getColor());
            ipInfo.setStatusName(pingResult ? IpStatus.NORMAL.getStatusName() : IpStatus.ERROR.getStatusName());
            list.add(ipInfo);
        });
        return list;
    }
}