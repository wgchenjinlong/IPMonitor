package com.example.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.xml.soap.SOAPPart;

@SpringBootApplication
@ServletComponentScan
public class IpMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(IpMonitorApplication.class, args);
    }
}
