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
	
	// 회원ID 반환
	public long getMemberId() {
		return memberId;
	}
	
	// 회원ID 저장 및 수정
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	
	// 이름 반환
	public String getName() {
		return name;
	}
	
	// 이름 저장 및 수정
	public void setName(String name) {
		this.name = name;
	}
	
	// 연락처 반환
	public String getPhone() {
		return phone;
	}
	
	// 연락처 저장 및 수정
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	// 가입일 반환
	public LocalDate getRegDate() {
		return regDate;
	}
	
	// 가입일 저장 및 수정
	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}
}
