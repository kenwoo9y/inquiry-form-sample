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
        String sql = "INSERT INTO inquiry(name, email, contents, created_date) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(
            sql, 
            inquiry.getName(),
            inquiry.getEmail(),
            inquiry.getContents(),
            inquiry.getCreatedDate()
        );
        
    }

    @Override
    public List<Inquiry> getAll() {
        String sql = "SELECT id, name, email, contents, created_date FROM inquiry";
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
        
        List<Inquiry> inquiryList = new ArrayList<>();

        for(Map<String, Object> result : resultList) {
            Inquiry inquiry = new Inquiry();
            inquiry.setId((int)result.get("id"));
            inquiry.setName((String)result.get("name"));
            inquiry.setEmail((String)result.get("email"));
            inquiry.setContents((String)result.get("contents"));
            inquiry.setCreatedDate(((Timestamp)result.get("created_date")).toLocalDateTime());
            
            inquiryList.add(inquiry);
        }

        return inquiryList;
    }
    
}
