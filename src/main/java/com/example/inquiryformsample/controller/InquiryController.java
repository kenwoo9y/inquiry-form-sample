package com.example.inquiryformsample.controller;

import com.example.inquiryformsample.form.InquiryForm;

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

        redirectAttributes.addAttribute("complete", "Registered!");
        return "redirect:/inquiry/form";
    }
}