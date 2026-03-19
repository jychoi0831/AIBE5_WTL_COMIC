package model;

import java.time.LocalDate;

public class Member {
	
	private long memberId;        // 회원ID (PK)
	private String name;          // 이름
	private String phone;         // 연락처
	private LocalDate regDate;    // 가입일
	
	public Member(long memberId, String name, String phone, LocalDate regDate) {
		this.memberId = memberId;
		this.name = name;
		this.phone = phone;
		this.regDate = regDate;
    }
	
	public long getMemberId() {
		return memberId;
	}
	
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public LocalDate getRegDate() {
		return regDate;
	}
	
	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}
}
