package com.example.monitor.domain.forms;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;

/**
 * Created by Administrator on 2016/10/20.
 */
public class IpInfoForm {

    @NotBlank
    @Length(max = 20)
    private String ipAddr;
    @Length(max = 100)
    private String name;
    @Length(max = 500)
    private String commit;

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }
}
