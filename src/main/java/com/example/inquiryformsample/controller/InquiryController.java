package com.example.inquiryformsample.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.example.inquiryformsample.entity.Inquiry;
import com.example.inquiryformsample.form.InquiryForm;
import com.example.inquiryformsample.service.InquiryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping
    public String index(Model model) {
        List<Inquiry> inquiries = inquiryService.getAll();
        model.addAttribute("inquiries", inquiries);
        model.addAttribute("title", "Inquiry Index");

        return "index";
    }

    @GetMapping("/form")
    public String form(InquiryForm inquiryForm, Model model, @ModelAttribute("complete") String complete) {
        model.addAttribute("title", "Inquiry Form");
        return "form";
    }

    @PostMapping("/form")
    public String formGoBack(InquiryForm inquiryForm, Model model) {
        model.addAttribute("title", "Inquiry Form");
        return "form";
    }

    @PostMapping("/confirm")
    public String confirm(@Validated InquiryForm inquiryForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("title", "InquiryForm");
            return "form";
        }

        model.addAttribute("title", "Confirm Page");
        return "confirm";
    }

    @PostMapping("/complete")
    public String complete(@Validated InquiryForm inquiryForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("title", "InquiryForm");
            return "form";
        }

        Inquiry inquiry = new Inquiry();

        inquiry.setName(inquiryForm.getName());
        inquiry.setEmail(inquiryForm.getEmail());
        inquiry.setContents(inquiryForm.getContents());
        inquiry.setCreatedDate(LocalDateTime.now());

        inquiryService.save(inquiry);

        redirectAttributes.addAttribute("complete", "Registered!");
        return "redirect:/inquiry/form";
    }
}