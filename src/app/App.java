package app;

import java.util.Scanner;

public class App {
	Scanner sc = new Scanner(System.in);
	
	public void run() {
		
		// 프로그램 시작을 알리는 안내문구
		System.out.println("[이멤버 리멤버 - 만화책 대여 관리 프로그램] 실행이 완료되었습니다.");
		
		while (true) {
			
			// 명령어 분기점 안내 문구
			System.out.print("cmd> ");
			String cmd = sc.nextLine();
			
			Rq rq = new Rq(cmd);
            String action = rq.getAction();
			
            // 명령어 - 프로그램 종료
            if ("exit".equals(action)) {
            	System.out.println("프로그램 종료");
            	break;
            	}
            
            // 명령어 - 만화책 등록
            else if ("comic-add".equals(action)) {
            	System.out.println("comic-add 기능 실행");
            	}

            // 명령어 - 만화책 목록
            else if ("comic-list".equals(action)) {
            	System.out.println("comic-list 기능 실행");
            	}
            
            // 명령어 - 만화책 상세
            else if ("comic-detail".equals(action)) {
            	System.out.println("comic-detail 기능 실행");
            	}
            
            // 명령어 - 만화책 수정
            else if ("comic-update".equals(action)) {
            	System.out.println("comic-update 기능 실행");
            	}

            // 명령어 - 만화책 삭제
            else if ("comic-delete".equals(action)) {
            	System.out.println("comic-delete 기능 실행");
            	}

            // 명령어 - 회원 등록
            else if ("member-add".equals(action)) {
            	System.out.println("member-add 기능 실행");
            	}

            // 명령어 - 회원 목록
            else if ("member-list".equals(action)) {
            	System.out.println("member-list 기능 실행");
            	}

            // 명령어 - 만화책 대여 **
            else if ("rent".equals(action)) {
            	System.out.println("rent 기능 실행");
            	}

            // 명령어 - 만화책 반납 **
            else if ("return".equals(action)) {
            	System.out.println("return 기능 실행");
            	}

            // 명령어 - 만화책 대여 목록
            else if ("rental-list".equals(action)) {
            	System.out.println("rental-list 기능 실행");
            	}
            
            // 명령어 - 명령어 없음
            else {
            	System.out.println("존재하지 않는 명령어 입니다.");
            	}
            
            System.out.println("명령 실행 : " + rq.getAction());
            
        }
		
	}
	
}
