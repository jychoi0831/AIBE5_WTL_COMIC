package model;

import java.time.LocalDate;

public class Rental {
	
	private long rentId;             // 대여ID (PK)
	private long comicId;            // 대여한 만화책ID (FK)
	private long memberId;           // 대여한 회원ID (FK)
    private LocalDate rentDate;      // 대여일
    private LocalDate returnDate;    // 반납일, 미반납 시 null
    
    public Rental(long rentId, long comicId, long memberId, LocalDate rentDate, LocalDate returnDate) {
        this.rentId = rentId;
        this.comicId = comicId;
        this.memberId = memberId;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }
    
    // 대여ID 반환
    public long getRentId() {
        return rentId;
    }
    
    // 만화책ID 반환
    public long getComicId() {
        return comicId;
    }
    
    // 회원ID 반환
    public long getMemberId() {
        return memberId;
    }
    
    // 대여일 반환
    public LocalDate getRentDate() {
        return rentDate;
    }
    
    // 반납일 반환, 미반납 시 null
    public LocalDate getReturnDate() {
        return returnDate;
    }
}
