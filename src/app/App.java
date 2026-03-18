package app;

import java.util.Scanner;
import repository.RentalRepository;

public class App {
	
	Scanner sc = new Scanner(System.in);
	
	public void run() {
		
		// 프로그램 시작 안내문구
		System.out.println("[이멤버 리멤버 - 만화책 대여 관리 프로그램] 실행되었습니다.");
		
		while (true) {
			
			// 명령어 요청 및 명령어 분기점
			System.out.print("cmd> ");
			String cmd = sc.nextLine();
			
            // exit - 프로그램 종료
            if ("exit".equals(cmd)) {
            	System.out.println("프로그램을 종료합니다.");
            	break;
        	}
            
            // comic-add - 만화책 등록
            else if ("comic-add".equals(cmd)) {
            	System.out.println("comic-add 기능 실행");
        	}

            // comic-list - 만화책 목록
            else if ("comic-list".equals(cmd)) {
            	System.out.println("comic-list 기능 실행");
        	}
            
            // comic-detail - 만화책 상세
            else if ("comic-detail".equals(cmd)) {
            	System.out.println("comic-detail 기능 실행");
        	}
            
            // omic-update - 만화책 수정
            else if ("comic-update".equals(cmd)) {
            	System.out.println("comic-update 기능 실행");
        	}

            // comic-delete - 만화책 삭제
            else if ("comic-delete".equals(cmd)) {
            	System.out.println("comic-delete 기능 실행");
        	}

            // member-add - 회원 등록
            else if ("member-add".equals(cmd)) {
            	System.out.println("member-add 기능 실행");
        	}

            // member-list - 회원 목록
            else if ("member-list".equals(cmd)) {
            	System.out.println("member-list 기능 실행");
        	}

            // rent - 만화책 대여
            else if ("rent".equals(cmd)) {
            	System.out.println("[만화책 대여] 정보를 입력하세요.");

                System.out.print("회원 번호: ");
                int memberId = Integer.parseInt(sc.nextLine());

                System.out.print("만화책 번호: ");
                int comicId = Integer.parseInt(sc.nextLine());
                
                RentalRepository rentalRepository = new RentalRepository();
                
                boolean result = rentalRepository.rent(memberId, comicId);
                
                if (result) {
                	System.out.println("[대여] [회원 번호: " + memberId + "]님이 [만화책 번호: " + comicId + "]를 대여하였습니다.");
                }
        	}
            
            // return - 만화책 반납
            else if ("return".equals(cmd)) {
                System.out.println("[만화책 반납] 정보를 입력하세요.");
                
                System.out.print("만화책 번호: ");
                int comicId = Integer.parseInt(sc.nextLine());
                
                RentalRepository rentalRepository = new RentalRepository();
                
                boolean result = rentalRepository.returnComic(comicId);
                
                if (result) {
                    System.out.println("[반납] [만화책 번호: " + comicId + "]가 반납되었습니다.");
                }
        	}
            
            // rental-list - 만화책 대여 목록
            else if ("rental-list".equals(cmd)) {
            	System.out.println("rental-list 기능 실행");
        	}
            
            // 정의하지 않은 명령어
            else {
            	System.out.println("존재하지 않는 명령어 입니다.");
        	}
            
            // 명령어 실행 임시 확인용
            System.out.println("[temp] 명령 실행 : " + cmd);
            
        }
		
	}
	
}
