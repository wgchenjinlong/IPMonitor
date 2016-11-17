package com.example.monitor.services;

import com.example.monitor.domain.PingResult;
import com.example.monitor.domain.models.IpInfo;
import com.example.monitor.repositories.IpInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public PingResult ping(String ipAddress, int pingTimes, int timeOut) {
        BufferedReader in = null;
        Runtime r = Runtime.getRuntime();
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes + " -w " + timeOut;
        PingResult pingResult = new PingResult();
        try {
            System.out.println(pingCommand);
            Process p = r.exec(pingCommand);
            if (p == null) {
                pingResult.setLost(0);
                pingResult.setNetworkStatus(0);
                return pingResult;
            }
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));   // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数
            int connectedCount = 0;
            String line = null;
            while ((line = in.readLine()) != null) {
                connectedCount += getCheckResult(line);
            }
            pingResult.setLost(connectedCount/pingTimes);
            pingResult.setNetworkStatus(connectedCount/pingTimes);


            return pingResult;
        } catch (Exception ex) {
            ex.printStackTrace();
            pingResult.setLost(0);
            pingResult.setNetworkStatus(0);
            return pingResult;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //若line含有=18ms TTL=16字样,说明已经ping通,返回1,否則返回0.
    private int getCheckResult(String line) {
        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            return 1;
        }
        return 0;
    }
}
