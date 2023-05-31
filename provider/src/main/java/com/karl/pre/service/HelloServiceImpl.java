package com.karl.pre.service;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.stereotype.Component;

@Service(version = "1.0.0")
@Component
public class HelloServiceImpl implements HelloService{
    @Autowired
    InetUtils inetUtils;

    @Override
    public String hello(String name) {
        return "[" + inetUtils.findFirstNonLoopbackAddress().getHostAddress() + "] -> " + name.toUpperCase();
    }
}
