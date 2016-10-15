package com.example.monitor.services;

import com.example.monitor.domain.models.IpInfo;
import com.example.monitor.repositories.IpInfoRepository;
import com.example.monitor.utils.CsvFileUtils;
import com.example.monitor.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonitorService {

    @Autowired
    private IpInfoRepository ipInfoRepository;

    public List<String> getIpList() {

//        String fileName = "src/main/resources/static/files/csv/test.csv";
//        File file = new File(fileName);
//        boolean isFile = file.isFile();
//
//        List<String[]> list = new ArrayList<>();
//        if (isFile) {
//            list = CsvFileUtils.readCsvFile(fileName, "utf-8");
//        } else {
//            FileUtils.createFile(fileName);
//        }
//
//        List<String> ipList = new ArrayList<>();
//        list.forEach(i -> {
//            if (!StringUtils.isEmpty(i[0])) {
//                ipList.add(i[0]);
//            }
//        });

        List<IpInfo> ipInfos = ipInfoRepository.findAll();
        List<String> ipList = ipInfos.stream().map(i -> i.getIpAddr()).collect(Collectors.toList());
        return ipList;
    }


    public boolean ping(String ipAddress) {
        int  timeOut =  3000 ;
        boolean status;
        try {
            status = InetAddress.getByName(ipAddress).isReachable(timeOut);
        } catch (IOException e) {
            status = false;
        }
        return status;
    }

}
