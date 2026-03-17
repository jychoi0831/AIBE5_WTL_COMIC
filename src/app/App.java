package app;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import model.Rental;
import repository.RentalRepository;

public class App {
    Scanner sc = new Scanner(System.in);
    RentalRepository rentalRepository = new RentalRepository();

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

            // 명령어 - 만화책 대여
            else if ("rent".equals(action)) {
                System.out.println("rent 기능 실행");
            }

            // 명령어 - 만화책 반납
            else if ("return".equals(action)) {
                System.out.println("return 기능 실행");
            }

            // 명령어 - 만화책 대여 목록
            else if ("rental-list".equals(action)) {
                List<Rental> rentals = rentalRepository.findAll();
                printRentalList(rentals);
                continue;
            }

            // 명령어 - 명령어 없음
            else {
                System.out.println("존재하지 않는 명령어 입니다.");
            }

            System.out.println("명령 실행 : " + rq.getAction());
        }
    }

    private void printRentalList(List<Rental> rentals) {
        if (rentals.isEmpty()) {
            System.out.println("=> 대여 내역이 없습니다.");
            return;
        }

        System.out.println("대여id | 만화id | 회원id | 대여일     | 반납예정일 | 반납일");
        System.out.println("------------------------------------------------------------");

        for (Rental rental : rentals) {
            LocalDate dueDate = rental.getRentDate().plusDays(2);
            String returnDate = rental.getReturnDate() == null ? "-" : rental.getReturnDate().toString();

            System.out.printf("%d | %d | %d | %s | %s | %s%n",
                    rental.getRentId(),
                    rental.getComicId(),
                    rental.getMemberId(),
                    rental.getRentDate(),
                    dueDate,
                    returnDate
            );
        }
    }
}