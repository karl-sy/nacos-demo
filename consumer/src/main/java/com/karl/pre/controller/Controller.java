package com.karl.pre.controller;

import com.karl.pre.service.HelloService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.dubbo.config.annotation.Reference;

@RestController
public class Controller {
    @Autowired
    @Qualifier("loadBalancedRestTemplate")
    private RestTemplate loadBalancedRestTemplate;

    @Autowired
    InetUtils inetUtils;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("spring.application.name")
    String appName;

    @GetMapping("/services")
    public String getServices() {
        return discoveryClient.getInstances(appName).toString();
    }

    @Reference(application = "${dubbo.application.id}", version = "1.0.0")
    private HelloService helloService;


    @GetMapping("/hello")
    public String hello(HttpServletRequest request) throws ExecutionException, InterruptedException {

        ResponseEntity<String> responseEntity = loadBalancedRestTemplate.getForEntity("http://provider/hello", String.class);
        HttpStatus status = responseEntity.getStatusCode();
        String result = responseEntity.getBody() + status.value();

        return "[" + inetUtils.findFirstNonLoopbackAddress().getHostAddress() + "]" + " -> " + result;
    }

    @GetMapping("/dubbo/{name}")
    public String dubbo(HttpServletRequest request,@PathVariable("name") String name) {
        return "[" + inetUtils.findFirstNonLoopbackAddress().getHostAddress() + "]" + " -> " + helloService.hello(name);
    }

}
