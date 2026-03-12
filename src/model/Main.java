package model;

import java.util.Scanner;

// 작성한 기능들이 잘 작동하는지 테스트하기 위한 실행 전용 클래스입니다.
public class Main {
    public static void main(String[] args) {
        // 사용자 입력을 받기 위해 Scanner 객체를 생성합니다.
        Scanner scanner = new Scanner(System.in);
        // 회원 관리 기능을 사용하기 위해 MemberManager 객체를 생성합니다.
        MemberManager manager = new MemberManager();

        System.out.println("프로그램을 시작합니다.");

        // 무한 반복문을 통해 사용자가 '종료'를 선택할 때까지 메뉴를 계속 보여줍니다.
        while (true) {
            System.out.println("\n[ 메뉴 선택 ]");
            System.out.println("1. 회원 등록 (member-add)");
            System.out.println("2. 회원 목록 조회 (member-list)");
            System.out.println("0. 프로그램 종료");
            System.out.print("원하는 작업의 번호를 입력하세요: ");
            
            int choice = -1;
            try {
                // 사용자가 입력한 문자열을 정수(int)로 변환합니다.
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // 숫자가 아닌 문자를 입력했을 때의 에러 처리
                System.out.println("⚠ 숫자로 입력해주세요.");
                continue; // 반복문의 처음으로 돌아갑니다.
            }

            // 입력한 번호에 따라 다른 작업을 수행합니다.
            if (choice == 1) {
                // 회원 등록 기능
                System.out.println("[ 회원 정보 입력 ]");
                System.out.print("이름을 입력하세요: ");
                String name = scanner.nextLine();
                System.out.print("연락처를 입력하세요: ");
                String phone = scanner.nextLine();

                // MemberManager의 등록 기능을 호출합니다. (내부에서 ID 자동 생성)
                manager.addMember(name, phone);

            } else if (choice == 2) {
                // 회원 목록 출력 기능
                manager.printMemberList();

            } else if (choice == 0) {
                // 프로그램 종료
                System.out.println("프로그램을 종료합니다.");
                break; // 무한 반복문을 탈출합니다.

            } else {
                // 0, 1, 2 이외의 숫자를 입력했을 때
                System.out.println("⚠ 잘못된 번호입니다. 다시 입력해주세요.");
            }
        }
        
        // 프로그램 종료 시 사용했던 Scanner를 닫아줍니다.
        scanner.close();
    }
}
