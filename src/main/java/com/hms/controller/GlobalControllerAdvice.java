package com.hms.controller;

import com.hms.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.Year;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private VisitorService visitorService;

    @ModelAttribute
    public void addAttributes(Model model, jakarta.servlet.http.HttpServletRequest request) {
        model.addAttribute("liveVisitors", visitorService.getLiveVisitors());
        model.addAttribute("totalVisitors", visitorService.getTotalVisitors());
        model.addAttribute("currentYear", Year.now().getValue());
        model.addAttribute("requestURI", request.getRequestURI());
    }
}
