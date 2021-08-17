package com.example.inquiryformsample.repository;

import java.util.List;

import com.example.inquiryformsample.entity.Inquiry;

public interface InquiryDao {

    void insertInquiry(Inquiry inquiry);

    int updateInquiry(Inquiry inquiry);

    List<Inquiry> getAll();
}
