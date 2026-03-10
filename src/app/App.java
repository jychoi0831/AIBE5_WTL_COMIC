package app;

import java.util.Scanner;

public class App {
	Scanner sc = new Scanner(System.in);
	
	public void run() {
		
		System.out.println("이멤버 리멤버 만화책 대여점");
		
		while (true) {
			
			System.out.print("명령어) ");
			String cmd = sc.nextLine();
			
			Rq rq = new Rq(cmd);
			
			if (rq.getAction().equals("exit")) {
				System.out.println("프로그램 종료");
				break;
				}
			
			System.out.println("명령 실행 : " + rq.getAction());
			}
		}
	}
