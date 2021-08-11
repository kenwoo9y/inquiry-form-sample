package com.example.inquiryformsample.entity;

import java.time.LocalDateTime;

public class Inquiry {

    private int id;
    private String name;
    private String email;
    private String contents;
    private LocalDateTime createdDate;

    public Inquiry() {}

    public Inquiry(int id, String name, String email, String contents, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contents = contents;
        this.createdDate = createdDate;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
    
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

}
