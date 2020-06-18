package com.davidparry.controller;

import com.davidparry.PropertyConfig;
import com.davidparry.service.LocalWorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
public class Backend {

    private final Logger logger = LoggerFactory.getLogger(Backend.class);

    @Autowired
    LocalWorkService localWorkService;

    @Autowired
    PropertyConfig propertyConfig;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/api/{message}")
    public String printDate(@RequestHeader(name = "user_name", required = false) String username, @PathVariable(name = "message") String message) {
        backendWorkService(300);
        String resp = "";

        localWorkService.doWorkFor(600);

        if (username != null) {
            resp = new Date().toString() + " " + username + " time waited to " + message;
        } else {
            resp = new Date().toString() + " time waited to " + message;
        }
        logger.info("Sending back to the caller {}", resp);
        return resp;
    }

    public void backendWorkService(long time) {
        logger.info("Backend doing work for {}", time);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            logger.error("Backend Error simulating work for {} milliseconds ", time, e);
        }
        logger.debug("Backend Completed doing the work for {} milliseconds", time);
    }

}
