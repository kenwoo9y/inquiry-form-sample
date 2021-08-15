package com.example.inquiryformsample.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.inquiryformsample.entity.Inquiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InquiryDaoImpl implements InquiryDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InquiryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertInquiry(Inquiry inquiry) {
        String sql = "INSERT INTO inquiry(name, email, contents, created_date_time) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(
            sql, 
            inquiry.getName(),
            inquiry.getEmail(),
            inquiry.getContents(),
            inquiry.getCreatedDateTime()
        );
        
    }

    @Override
    public List<Inquiry> getAll() {
        String sql = "SELECT id, name, email, contents, created_date_time FROM inquiry";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        
        List<Inquiry> inquiryList = new ArrayList<>();

        for(Map<String, Object> result : resultList) {
            Inquiry inquiry = new Inquiry();
            inquiry.setId((int)result.get("id"));
            inquiry.setName((String)result.get("name"));
            inquiry.setEmail((String)result.get("email"));
            inquiry.setContents((String)result.get("contents"));
            inquiry.setCreatedDateTime(((Timestamp)result.get("created_date_time")).toLocalDateTime());
            
            inquiryList.add(inquiry);
        }

        return inquiryList;
    }
    
}
