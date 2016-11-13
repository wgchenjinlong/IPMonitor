package com.example.monitor.controllers;

import com.example.monitor.domain.IpStatus;
import com.example.monitor.domain.dtos.IpInfoDto;
import com.example.monitor.domain.forms.IpInfoForm;
import com.example.monitor.domain.models.IpInfo;
import com.example.monitor.repositories.IpInfoRepository;
import com.example.monitor.services.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        for(IpInfo i : ipList) {
            IpInfoDto ipInfo = new IpInfoDto();
            ipInfo.setId(i.getId());
            ipInfo.setIpAddress(i.getIpAddr());
            ipInfo.setStatus(IpStatus.NORMAL);
            ipInfo.setName(i.getName());
            ipInfo.setCommit(i.getCommit());
            list.add(ipInfo);
        }
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

    @RequestMapping(value = "/monitor/validate", method = RequestMethod.POST)
    public Map<String, String> validate(@RequestParam String ipAddr, @RequestParam String name, @RequestParam String commit) {

        String checkIpAddr = checkIpAddr(ipAddr);
        String checkName = checkName(name);
        String checkCommit = checkCommit(commit);

        Map<String, String> map = new HashMap<>();
        if (!StringUtils.isEmpty(checkIpAddr)) {
            map.put("ipAddr", checkIpAddr);
        }
        if (!StringUtils.isEmpty(checkName)) {
            map.put("name", checkName);
        }
        if (!StringUtils.isEmpty(checkCommit)) {
            map.put("commit", checkCommit);
        }
        return map;
    }

    private String checkIpAddr(String ipAddr) {

        String msg = "";
        if (StringUtils.isEmpty(ipAddr)) {
            msg = "不能为空";
        } else {
            String pattern = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";
            Pattern r = Pattern.compile(pattern);
            Matcher matcher = r.matcher(ipAddr);

            if (!matcher.find()) {
                msg = "请输入一个合法的IP地址";
            } else {
                List<IpInfo> ipInfos = ipInfoRepository.findByIp(ipAddr);
                int size = ipInfos.size();
                if (size > 0) {
                    msg = "该IP已经被监控，请输入一个新的IP地址";
                }
            }
        }
        return msg;
    }

    private String checkName(String name) {
        String msg = "";
        if (!StringUtils.isEmpty(name) && name.length() > 100) {
            msg = "名称不能超过100个字符";
        }
        return msg;
    }

    private String checkCommit(String commit) {
        String msg = "";
        if (!StringUtils.isEmpty(commit) && commit.length() > 500) {
            msg = "名称不能超过500个字符";
        }
        return msg;
    }

    @RequestMapping(value = "/monitor/add", method = RequestMethod.POST)
    public ModelAndView create(@Valid IpInfoForm ipInfoForm, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            for (FieldError field: result.getFieldErrors()) {

                System.out.println(field.getDefaultMessage());
            }
            attr.addFlashAttribute("error", "添加失败");
            return new ModelAndView(new RedirectView("/monitor", true));
        }

        IpInfo ipInfo = new IpInfo();
        ipInfo.setIpAddr(ipInfoForm.getIpAddr());
        ipInfo.setName(ipInfoForm.getName());
        ipInfo.setCommit(ipInfoForm.getCommit());
        ipInfoRepository.save(ipInfo);
        attr.addFlashAttribute("success", "添加成功");
        return new ModelAndView(new RedirectView("/monitor", true));
    }

    @RequestMapping(value = "/monitor/delete/{id}", method = RequestMethod.DELETE)
    public ModelAndView delete(@PathVariable Integer id, RedirectAttributes attr) {

        if (id == null) {
            attr.addFlashAttribute("error", "删除失败");
        }
        IpInfo ipInfo = ipInfoRepository.findOne(id);
        if (ipInfo == null) {
            attr.addFlashAttribute("error", "删除失败");
        } else {
            ipInfoRepository.delete(id);
            attr.addFlashAttribute("success", "删除成功");
        }

        return new ModelAndView(new RedirectView("/monitor"));
    }
}
