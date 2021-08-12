package com.example.inquiryformsample.service;

import java.util.List;

import com.example.inquiryformsample.entity.Inquiry;

public interface InquiryService {
    
    void save(Inquiry inquiry);

    List<Inquiry> getAll();
}
