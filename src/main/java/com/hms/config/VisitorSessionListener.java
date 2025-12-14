package com.hms.config;

import com.hms.service.VisitorService;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VisitorSessionListener implements HttpSessionListener {

    @Autowired
    private VisitorService visitorService;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        visitorService.incrementLiveVisitors();
        visitorService.incrementTotalVisitors();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        visitorService.decrementLiveVisitors();
    }
}
