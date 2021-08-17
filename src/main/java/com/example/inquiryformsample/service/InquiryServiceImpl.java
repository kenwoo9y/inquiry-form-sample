package com.example.inquiryformsample.service;

import java.util.List;

import com.example.inquiryformsample.entity.Inquiry;
import com.example.inquiryformsample.repository.InquiryDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquiryServiceImpl implements InquiryService {

    private final InquiryDao inquiryDao;

    @Autowired
    public InquiryServiceImpl(InquiryDao inquiryDao) {
        this.inquiryDao = inquiryDao;
    }

    @Override
    public void save(Inquiry inquiry) {
        inquiryDao.insertInquiry(inquiry);
    }

    @Override
    public List<Inquiry> getAll() {
        return inquiryDao.getAll();
    }

    @Override
    public void update(Inquiry inquiry) {
        if(inquiryDao.updateInquiry(inquiry) == 0) {
            throw new InquiryNotFoundException("can't find the same ID");
        }
    }
    
}
