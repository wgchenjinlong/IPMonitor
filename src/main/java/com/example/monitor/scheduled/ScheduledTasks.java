package com.example.monitor.scheduled;

import com.example.monitor.domain.models.IpInfo;
import com.example.monitor.repositories.IpInfoRepository;
import com.example.monitor.services.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/19.
 */
@Component
public class ScheduledTasks {

    @Autowired
    private IpInfoRepository ipInfoRepository;

    @Autowired
    private MonitorService monitorService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));

        List<IpInfo> list = ipInfoRepository.findAll();
        System.out.println(list.size());

        for(IpInfo i : list) {
            monitorService.asyncPing(i.getIpAddr(), 10, 3000, i.getId());
        }
    }
}
