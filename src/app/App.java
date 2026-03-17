package app;

import model.Rental;
import repository.RentalRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {
	Scanner sc = new Scanner(System.in);
	RentalRepository rentalRepository = new RentalRepository();
	
	public void run() {

		System.out.println("이멤바 리멤버 만화책 대여점");

		while (true) {
			System.out.print("명령어) ");
			String cmd = sc.nextLine();

			Rq rq = new Rq(cmd);

			if (rq.getAction().equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}

			if (rq.getAction().equals("rental-list")) {
				List<Rental> rentals = rentalRepository.findAll();
				printRentalList(rentals);
				continue;
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
