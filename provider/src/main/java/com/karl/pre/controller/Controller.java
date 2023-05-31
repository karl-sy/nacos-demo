package com.karl.pre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;


@RestController
public class Controller {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    InetUtils inetUtils;

    @Value("spring.application.name")
    String appName;

    @GetMapping("/services")
    public String getServices() {
        return discoveryClient.getInstances(appName).toString();
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) throws ExecutionException, InterruptedException {

        return "[" + inetUtils.findFirstNonLoopbackAddress().getHostAddress() + "]";
    }
}
