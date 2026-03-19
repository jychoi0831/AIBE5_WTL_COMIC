package app;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

import model.Comic;
import model.Member;
import model.Rental;
import repository.ComicRepository;
import repository.MemberRepository;
import repository.RentalRepository;

public class App {
	
	Scanner sc = new Scanner(System.in);
	ComicRepository comicRepository = new ComicRepository();
	MemberRepository memberRepository = new MemberRepository();
	RentalRepository rentalRepository = new RentalRepository();
	
	public void run() {
		
		// 프로그램 시작 안내문구
		System.out.println();

		System.out.println("███████╗███╗   ███╗███████╗███╗   ███╗███████╗██████╗ ");
		System.out.println("██╔════╝████╗ ████║██╔════╝████╗ ████║██╔════╝██╔══██╗");
		System.out.println("█████╗  ██╔████╔██║█████╗  ██╔████╔██║█████╗  ██████╔╝");
		System.out.println("██╔══╝  ██║╚██╔╝██║██╔══╝  ██║╚██╔╝██║██╔══╝  ██╔══██╗");
		System.out.println("███████╗██║ ╚═╝ ██║███████╗██║ ╚═╝ ██║███████╗██║  ██║");
		System.out.println("╚══════╝╚═╝     ╚═╝╚══════╝╚═╝     ╚═╝╚══════╝╚═╝  ╚═╝");
		System.out.println();

		System.out.println("██████╗ ███████╗███╗   ███╗███████╗███╗   ███╗██████╗ ");
		System.out.println("██╔══██╗██╔════╝████╗ ████║██╔════╝████╗ ████║██╔══██╗");
		System.out.println("██████╔╝█████╗  ██╔████╔██║█████╗  ██╔████╔██║██████╔╝");
		System.out.println("██╔══██╗██╔══╝  ██║╚██╔╝██║██╔══╝  ██║╚██╔╝██║██╔══██╗");
		System.out.println("██║  ██║███████╗██║ ╚═╝ ██║███████╗██║ ╚═╝ ██║██████╔╝");
		System.out.println("╚═╝  ╚═╝╚══════╝╚═╝     ╚═╝╚══════╝╚═╝     ╚═╝╚═════╝ ");
		System.out.println();

		System.out.println("======================================================");
		System.out.println("            이멤버 리멤버 | 만화책 대여 관리 프로그램");
		System.out.println("======================================================");
		
		System.out.println();
		System.out.println("       ────────────── Command List ─────────────");
		System.out.println();
		System.out.println("         comic-add          │  만화책 등록");
		System.out.println("         comic-list         │  만화책 목록");
		System.out.println("         comic-detail       │  만화책 상세보기");
		System.out.println("         comic-update       │  만화책 수정");
		System.out.println("         comic-delete       │  만화책 삭제");
		System.out.println("         member-add         │  회원 등록");
		System.out.println("         member-list        │  회원 목록");
		System.out.println("         rent               │  대여");
		System.out.println("         return             │  반납");
		System.out.println("         rental-list        │  대여 목록");
		System.out.println("         exit               │  프로그램 종료");
		System.out.println();
		System.out.println("       ─────────────────────────────────────────");
		System.out.println();
		
		while (true) {
			System.out.print("Command > ");
			String cmd = sc.nextLine().trim();

			Rq rq = new Rq(cmd);
			String action = rq.getAction();
			
            // exit - 프로그램 종료
            if ("exit".equals(action)) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            
            // comic-add - 만화책 등록
            else if ("comic-add".equals(action)) {
            	doAddComic();
        	}

            // comic-list - 만화책 목록
            else if ("comic-list".equals(action)) {
            	doListComics();
        	}
            
            // comic-detail - 만화책 상세
            else if ("comic-detail".equals(action)) {
            	doDetailComic();
        	}
            
            // comic-update - 만화책 수정
            else if ("comic-update".equals(action)) {
            	doUpdateComic();
        	}

            // comic-delete - 만화책 삭제
            else if ("comic-delete".equals(action)) {
            	doDeleteComic();
        	}

            // member-add - 회원 등록
            else if ("member-add".equals(action)) {
            	doAddMember();
        	}

            // member-list - 회원 목록
            else if ("member-list".equals(action)) {
            	doListMembers();
        	}

            // rent - 만화책 대여
            else if ("rent".equals(action)) {
            	doRent();
            }
            
            // return - 만화책 반납
            else if ("return".equals(action)) {
            	doReturn();
            }
            
            // rental-list - 만화책 대여 목록
            else if ("rental-list".equals(action)) {
            	doRentalList();
            }
            
            // 없는 명령어
            else {
            	System.out.println("존재하지 않는 명령어 입니다.");
        	}
        }
    }
	
    // comic-add - 만화책 등록
	private void doAddComic() {
		System.out.println("[만화책 등록] 정보를 입력하세요.");

        try {
            System.out.print("제목: ");
            String title = sc.nextLine().trim();

            System.out.print("작가: ");
            String author = sc.nextLine().trim();

            System.out.print("시리즈 권수: ");
            long volumeNumber = Integer.parseInt(sc.nextLine().trim());

            System.out.print("대여 가격: ");
            long rentPrice = Integer.parseInt(sc.nextLine().trim());

            if (title.isBlank() || author.isBlank()) {
                System.out.println("제목과 작가는 공백일 수 없습니다.");
                return;
            }

            if (volumeNumber <= 0) {
                System.out.println("시리즈 권수는 1 이상의 숫자여야 합니다.");
                return;
            }

            if (rentPrice < 0) {
                System.out.println("대여 가격은 0 이상이어야 합니다.");
                return;
            }

            long newId = comicRepository.save(title, author, volumeNumber, rentPrice);

            if (newId == -1) {
                System.out.println("만화책 등록에 실패했습니다.");
                return;
            }

            System.out.println(newId + "번 만화책이 등록되었습니다.");

        } catch (NumberFormatException e) {
            System.out.println("시리즈 권수와 대여 가격은 숫자로 입력해주세요.");
        }
    }
	
	// comic-list - 만화책 목록
    private void doListComics() {
        List<Comic> comics = comicRepository.findAll();

        if (comics.isEmpty()) {
            System.out.println("등록된 만화책이 없습니다.");
            return;
        }

        System.out.println("만화책ID | 제목 | 작가 | 시리즈 권수 | 대여 가격 | 등록일");
        System.out.println("--------------------------------------------------");

        for (Comic comic : comics) {
            System.out.printf("%d | %s | %s | %d | %d | %s%n",
                    comic.getComicId(),
                    comic.getTitle(),
                    comic.getAuthor(),
                    comic.getVolumeNumber(),
                    comic.getRentPrice(),
                    comic.getCreatedAt());
        }
    }
    
    // comic-detail - 만화책 상세
    private void doDetailComic() {
        System.out.println("[만화책 상세] 조회할 만화책ID를 입력하세요.");

        try {
            System.out.print("만화책ID: ");
            long comicId = Long.parseLong(sc.nextLine().trim());

            Comic comic = comicRepository.findById(comicId);

            if (comic == null) {
                System.out.println("해당 만화책이 존재하지 않습니다.");
                return;
            }

            System.out.println("[만화책 상세 정보]");
            System.out.println("만화책ID: " + comic.getComicId());
            System.out.println("제목: " + comic.getTitle());
            System.out.println("작가: " + comic.getAuthor());
            System.out.println("권수: " + comic.getVolumeNumber());
            System.out.println("대여료: " + comic.getRentPrice());
            System.out.println("등록일: " + comic.getCreatedAt());

        } catch (NumberFormatException e) {
            System.out.println("만화책ID는 숫자로 입력해주세요.");
        }
    }
	
	// comic-update - 만화책 수정
    private void doUpdateComic() {
        System.out.println("[만화책 수정] 만화책ID를 입력하세요.");

        try {
            System.out.print("만화책ID: ");
            long comicId = Long.parseLong(sc.nextLine().trim());

            Comic comic = comicRepository.findById(comicId);

            if (comic == null) {
                System.out.println("해당 만화책이 존재하지 않습니다.");
                return;
            }

            System.out.println("수정하지 않을 항목은 Enter로 넘겨주세요.");

            System.out.print("새 제목 (" + comic.getTitle() + "): ");
            String titleInput = sc.nextLine().trim();

            System.out.print("새 작가 (" + comic.getAuthor() + "): ");
            String authorInput = sc.nextLine().trim();

            System.out.print("새 시리즈 권수 (" + comic.getVolumeNumber() + "): ");
            String volumeInput = sc.nextLine().trim();

            System.out.print("새 대여 가격 (" + comic.getRentPrice() + "): ");
            String priceInput = sc.nextLine().trim();

            String newTitle = titleInput.isBlank() ? comic.getTitle() : titleInput;
            String newAuthor = authorInput.isBlank() ? comic.getAuthor() : authorInput;
            long newVolumeNumber = volumeInput.isBlank() ? comic.getVolumeNumber() : Integer.parseInt(volumeInput);
            long newRentPrice = priceInput.isBlank() ? comic.getRentPrice() : Integer.parseInt(priceInput);

            if (newTitle.isBlank() || newAuthor.isBlank()) {
                System.out.println("제목과 작가는 공백일 수 없습니다.");
                return;
            }

            if (newVolumeNumber <= 0) {
                System.out.println("시리즈 권수는 1 이상의 숫자여야 합니다.");
                return;
            }

            if (newRentPrice < 0) {
                System.out.println("대여 가격은 0 이상이어야 합니다.");
                return;
            }

            boolean updated = comicRepository.update(comicId, newTitle, newAuthor, newVolumeNumber, newRentPrice);

            if (updated) {
                System.out.println("만화책ID: " + comicId + " 이 수정되었습니다.");
            } else {
                System.out.println("만화책 수정에 실패했습니다.");
            }

        } catch (NumberFormatException e) {
            System.out.println("시리즈 권수와 대여 가격은 숫자로 입력해주세요.");
        }
    }
    
    // comic-delete - 만화책 삭제
    private void doDeleteComic() {
        System.out.println("[만화책 삭제] 만화책ID를 입력하세요.");

        try {
            System.out.print("만화책ID: ");
            long comicId = Long.parseLong(sc.nextLine().trim());

            String result = comicRepository.delete(comicId);

            switch (result) {
                case ComicRepository.DELETE_OK:
                	System.out.println("만화책ID: " + comicId + " 이 삭제되었습니다.");
                    break;

                case ComicRepository.DELETE_NOT_FOUND:
                    System.out.println("해당 만화책이 존재하지 않습니다.");
                    break;

                case ComicRepository.DELETE_RENTAL_HISTORY_EXISTS:
                    System.out.println("대여 이력이 있는 만화책은 삭제할 수 없습니다.");
                    break;

                default:
                    System.out.println("만화책 삭제에 실패했습니다.");
                    break;
            }

        } catch (NumberFormatException e) {
            System.out.println("만화책ID는 숫자로 입력해주세요.");
        }
    }
    
    // member-add - 회원 등록
    private void doAddMember() {
    	System.out.println("[회원 등록] 정보를 입력하세요.");

    	System.out.print("이름: ");
    	String name = sc.nextLine().trim();

    	System.out.print("연락처: ");
    	String phone = sc.nextLine().trim();

    	if (name.isBlank() || phone.isBlank()) {
    		System.out.println("이름과 연락처는 공백일 수 없습니다.");
    		return;
    	}

		long newId = memberRepository.save(name, phone);

    	if (newId == -1) {
    		System.out.println("회원 등록에 실패했습니다.");
    		return;
    	}

    	System.out.println(newId + "번 회원이 등록되었습니다.");
    }    
    
    // member-list - 회원 목록
    private void doListMembers() {
    	List<Member> members = memberRepository.findAll();

    	if (members.isEmpty()) {
    		System.out.println("등록된 회원이 없습니다.");
    		return;
    	}

    	System.out.println("회원ID | 이름 | 연락처 | 가입일");
    	System.out.println("--------------------------------------------------");

    	for (Member member : members) {
    		System.out.printf("%d | %s | %s | %s%n",
    				member.getMemberId(),
    				member.getName(),
    				member.getPhone(),
    				member.getRegDate());
    	}
    }
    
    // rent - 만화책 대여
	private void doRent() {
        System.out.println("[만화책 대여] 정보를 입력하세요.");

        try {
            System.out.print("회원 번호: ");
            long memberId = Long.parseLong(sc.nextLine().trim());

            System.out.print("만화책 번호: ");
            long comicId = Long.parseLong(sc.nextLine().trim());

            String result = rentalRepository.rent(memberId, comicId);

            switch (result) {
                case RentalRepository.RENT_OK:
                    System.out.println("[대여] [회원 번호: " + memberId + "]님이 [만화책 번호: " + comicId + "]를 대여하였습니다.");
                    break;

                case RentalRepository.RENT_NO_MEMBER:
                    System.out.println("존재하지 않는 회원 번호입니다.");
                    break;

                case RentalRepository.RENT_NO_COMIC:
                    System.out.println("존재하지 않는 만화책 번호입니다.");
                    break;

                case RentalRepository.RENT_ALREADY_RENTED:
                    System.out.println("이미 대여중인 만화책입니다.");
                    break;

                default:
                    System.out.println("대여 처리 중 오류가 발생했습니다.");
                    break;
            }

        } catch (NumberFormatException e) {
            System.out.println("회원 번호와 만화책 번호는 숫자만 입력해주세요.");
        }
    }
	
	// return - 만화책 반납
	private void doReturn() {
        System.out.println("[만화책 반납] 정보를 입력하세요.");

        try {
            System.out.print("만화책 번호: ");
            long comicId = Long.parseLong(sc.nextLine().trim());

            String result = rentalRepository.returnComic(comicId);

            switch (result) {
                case RentalRepository.RETURN_OK:
                    System.out.println("[반납] [만화책 번호: " + comicId + "]가 반납되었습니다.");
                    break;

                case RentalRepository.RETURN_NO_HISTORY:
                    System.out.println("대여 이력이 없는 만화책입니다.");
                    break;

                case RentalRepository.RETURN_ALREADY_RETURNED:
                    System.out.println("이미 반납된 만화책입니다.");
                    break;

                default:
                    System.out.println("반납 처리 중 오류가 발생했습니다.");
                    break;
            }

        } catch (NumberFormatException e) {
            System.out.println("만화책 번호는 숫자만 입력해주세요.");
        }
    }
	
	// rental-list - 만화책 대여 목록
	private void doRentalList() {
        List<Rental> rentals = rentalRepository.findAll();
        printRentalList(rentals);
    }
	
	private void printRentalList(List<Rental> rentals) {
	        if (rentals.isEmpty()) {
	            System.out.println("대여 내역이 없습니다.");
	            return;
	        }

	        System.out.println("대여id | 만화id | 회원id | 대여일 | 반납일 | 반납예정일");
	        System.out.println("------------------------------------------------------------");

	        for (Rental rental : rentals) {
	            LocalDate dueDate = rental.getRentDate().plusDays(2);
	            String returnDate = rental.getReturnDate() == null ? "-" : rental.getReturnDate().toString();

	            System.out.printf("%d | %d | %d | %s | %s | %s%n",
	                    rental.getRentId(),
	                    rental.getComicId(),
	                    rental.getMemberId(),
	                    rental.getRentDate(),
	                    returnDate,
	                    dueDate
                );
            }
    }
}
