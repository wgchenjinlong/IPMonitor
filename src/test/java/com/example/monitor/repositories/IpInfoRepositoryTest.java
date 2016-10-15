package com.example.monitor.repositories;

import com.example.monitor.domain.models.IpInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IpInfoRepositoryTest {

    @Autowired
    private IpInfoRepository ipInfoRepository;

    @Test
    public void findAll() throws Exception {

        IpInfo ipInfo = new IpInfo();
        ipInfo.setIpAddr("192.168.0.1");
        ipInfo.setName("test");
        ipInfoRepository.save(ipInfo);

        List<IpInfo> ipInfos = ipInfoRepository.findAll();
        Assert.assertEquals(ipInfos.size(), 1);
    }

}