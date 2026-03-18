package model;

import java.time.LocalDate;

public class Rental {
	
	private long rentId;             // 대여 ID (PK)
	private long comicId;            // 대여한 만화책 ID
	private long memberId;           // 대여한 회원 ID
    private LocalDate rentDate;      // 대여일
    private LocalDate returnDate;    // 실제 반납일 (반납 전이면 null)
//    private String dueDate;          // 반납 예정일
	
    
    public Rental(long rentId, long comicId, long memberId, LocalDate rentDate, LocalDate returnDate) {
        this.rentId = rentId;
        this.comicId = comicId;
        this.memberId = memberId;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
//        this.dueDate = dueDate;
    }
    
    public long getRentId() {
        return rentId;
    }

    public long getComicId() {
        return comicId;
    }

    public long getMemberId() {
        return memberId;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}
