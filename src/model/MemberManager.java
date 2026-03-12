package model;

import java.util.ArrayList;
import java.util.List;

// 회원 정보를 관리(등록, 목록 조회)하는 기능을 담당하는 클래스
public class MemberManager {
    
    // DB를 아직 사용하지 않으므로 회원 정보를 임시로 저장할 빈 리스트(List)를 생성합니다.
    private List<Member> memberList = new ArrayList<>();

    // DB의 시퀀스처럼 자동으로 증가할 회원 번호 (1부터 시작)
    private static int memberCount = 1;

    // 1. 회원 등록 (member-add)
    public void addMember(String name, String phone) {
        // "user" 뒤에 숫자를 붙여 자동으로 ID를 생성합니다. (예: user1, user2...)
        String autoId = "user" + memberCount;
        
        // 새로운 회원 객체를 생성합니다.
        Member newMember = new Member(autoId, name, phone);
        
        // 리스트에 추가합니다.
        memberList.add(newMember);
        
        System.out.println("✅ [" + name + "] 회원님이 성공적으로 등록되었습니다. (할당된 ID: " + autoId + ")");
        
        // 다음 회원을 위해 번호를 1 증가시킵니다.
        memberCount++;
    }


    // 2. 회원 목록 출력 (member-list)
    public void printMemberList() {
        // 리스트에 데이터가 없는 경우
        if (memberList.isEmpty()) {
            System.out.println("⚠ 등록된 회원이 없습니다.");
            return; // 메서드를 여기서 종료합니다.
        }

        System.out.println("===== 등록된 회원 목록 =====");
        // 향상된 for문(for-each)을 사용하여 리스트에 있는 회원 정보를 하나씩 꺼내와 출력합니다.
        for (Member member : memberList) {
            // Member 클래스에서 정의한 toString() 메서드의 형태대로 출력됩니다.
            System.out.println(member.toString()); 
        }
        System.out.println("===========================");
    }
}
