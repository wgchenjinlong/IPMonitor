package com.example.monitor.controllers;

import com.example.monitor.domain.IpStatus;
import com.example.monitor.domain.dtos.IpInfoDto;
import com.example.monitor.domain.models.IpInfo;
import com.example.monitor.repositories.IpInfoRepository;
import com.example.monitor.services.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @Autowired
    private IpInfoRepository ipInfoRepository;

    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public ModelAndView index(Map<String, Object> model) {

        List<IpInfoDto> list = new ArrayList();
        List<IpInfo> ipList = monitorService.getIpList();

        ipList.forEach(i -> {
            IpInfoDto ipInfo = new IpInfoDto();
            ipInfo.setId(i.getId());
            ipInfo.setIpAddress(i.getIpAddr());
            ipInfo.setStatus(IpStatus.NORMAL);
            list.add(ipInfo);
        });
        model.put("ipInfos", list);
        return new ModelAndView("monitor/index", model);
    }

    @RequestMapping(value = "/monitor/ping", method = RequestMethod.GET)
    public IpInfoDto ping(@RequestParam String ipAddress) {

        IpInfoDto ipInfo = new IpInfoDto();
        ipInfo.setIpAddress(ipAddress);
        boolean pingResult = monitorService.ping(ipAddress);
        ipInfo.setStatus(pingResult ? IpStatus.NORMAL : IpStatus.ERROR);
        ipInfo.setColor(pingResult ? IpStatus.NORMAL.getColor() : IpStatus.ERROR.getColor());
        ipInfo.setStatusName(pingResult ? IpStatus.NORMAL.getStatusName() : IpStatus.ERROR.getStatusName());
        return ipInfo;
    }

    @RequestMapping(value = "/monitor/add", method = RequestMethod.POST)
    public Map<String, Object> create(@RequestParam String ipAddr,
                                      @RequestParam String name,
                                      @RequestParam String commit) {

        IpInfo ipInfo = new IpInfo();
        ipInfo.setIpAddr(ipAddr);
        ipInfo.setName(name);
        ipInfo.setCommit(commit);
        ipInfoRepository.save(ipInfo);

        Map<String, Object> result = new HashMap<>();
        result.put("status", "success");

        return result;
    }

    @RequestMapping(value = "/monitor/delete/{id}", method = RequestMethod.DELETE)
    public ModelAndView delete(@PathVariable Integer id) {

        if (id == null) {
            // error
        }

        IpInfo ipInfo = ipInfoRepository.findOne(id);
        if (ipInfo == null) {
            // error
        } else {

            ipInfoRepository.delete(id);
        }

        return new ModelAndView(new RedirectView("/monitor", true));
    }
}
