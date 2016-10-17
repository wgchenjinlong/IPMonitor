package com.example.monitor.services;

import com.example.monitor.domain.models.IpInfo;
import com.example.monitor.repositories.IpInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

@Service
public class MonitorService {

    @Autowired
    private IpInfoRepository ipInfoRepository;

    public List<IpInfo> getIpList() {

        List<IpInfo> ipInfos = ipInfoRepository.findAll();
        return ipInfos;
    }


    public boolean ping(String ipAddress) {
        int timeOut = 3000;
        boolean status;
        try {
            status = InetAddress.getByName(ipAddress).isReachable(timeOut);
        } catch (IOException e) {
            status = false;
        }
        return status;
    }

}
