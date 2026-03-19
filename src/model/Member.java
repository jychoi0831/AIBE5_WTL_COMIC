package model;

import java.time.LocalDate;

// 회원 정보를 담는 데이터 클래스 (DTO)
public class Member {
    // 회원의 고유 아이디, 이름, 연락처 정보를 저장할 변수 (필드)
    private String memberId;
    private String name;
    private String phone;
    // 가입일 정보를 저장할 변수 추가 (reg_date)
    private LocalDate regDate;

    // 생성자 (Member 객체를 새로 만들 때 초기값을 세팅해주는 역할)
    public Member(String memberId, String name, String phone) {
        this.memberId = memberId;
        this.name = name;
        this.phone = phone;
        // 가입일은 객체가 만들어지는 순간의 오늘 날짜로 자동 기록합니다.
        this.regDate = LocalDate.now();
    }

    // 외부에서 비공개 변수(private) 값을 가져갈 수 있도록 하는 Getter 메서드들
    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    // 회원 정보를 출력할 때 보기 좋게 문자열로 만들어주는 메서드
    @Override
    public String toString() {
        return "회원ID: " + memberId + ", 이름: " + name + ", 연락처: " + phone + ", 가입일: " + regDate;
    }
}
