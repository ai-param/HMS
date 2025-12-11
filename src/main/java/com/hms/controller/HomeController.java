package com.hms.controller;

import com.hms.entity.Appointment;
import com.hms.entity.Doctor;
import com.hms.entity.Patient;
import com.hms.service.HmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private HmsService hmsService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Patient
    @GetMapping("/patients")
    public String patients(Model model) {
        model.addAttribute("patients", hmsService.getAllPatients());
        model.addAttribute("newPatient", new Patient());
        return "patient";
    }

    @PostMapping("/patients")
    public String addPatient(@ModelAttribute Patient patient) {
        hmsService.savePatient(patient);
        return "redirect:/patients";
    }

    // Doctor
    @GetMapping("/doctors")
    public String doctors(Model model) {
        model.addAttribute("doctors", hmsService.getAllDoctors());
        model.addAttribute("newDoctor", new Doctor());
        return "doctor";
    }

    @PostMapping("/doctors")
    public String addDoctor(@ModelAttribute Doctor doctor) {
        hmsService.saveDoctor(doctor);
        return "redirect:/doctors";
    }

    // Appointment
    @GetMapping("/appointments")
    public String appointments(Model model) {
        model.addAttribute("appointments", hmsService.getAllAppointments());
        model.addAttribute("doctors", hmsService.getAllDoctors());
        model.addAttribute("patients", hmsService.getAllPatients());
        model.addAttribute("newAppointment", new Appointment());
        return "appointment";
    }

    @PostMapping("/appointments")
    public String addAppointment(@ModelAttribute Appointment appointment) {
        appointment.setStatus("Scheduled");
        hmsService.saveAppointment(appointment);
        return "redirect:/appointments";
    }
}
