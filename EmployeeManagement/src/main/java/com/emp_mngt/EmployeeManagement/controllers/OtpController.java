package com.emp_mngt.EmployeeManagement.controllers;

import com.emp_mngt.EmployeeManagement.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OtpController {

    @Autowired
    private OtpService otpService;

    @GetMapping("/otp")
    public String otpPage() {
        return "otp";
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String email,
            Model model) {

        otpService.sendOtp(email);

        model.addAttribute("email", email);
        model.addAttribute("message", "OTP sent successfully!");

        return "otp";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email,
            @RequestParam String otp,
            Model model) {

        boolean valid = otpService.verifyOtp(email, otp);

        if (valid) {
            model.addAttribute("message", "OTP Verified Successfully!");
        } else {
            model.addAttribute("message", "Invalid OTP!");
        }

        model.addAttribute("email", email);

        return "otp";
    }
}