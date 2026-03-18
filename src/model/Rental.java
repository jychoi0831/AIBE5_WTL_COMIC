package model;

public class Rental {
	
	 private long rentId;          // 대여 ID (PK)
	 private long comicId;         // 대여한 만화책 ID
	 private long memberId;        // 대여한 회원 ID
	 private String rentDate;      // 대여일
	 private String returnDate;    // 실제 반납일 (반납 전이면 null)
	 private String dueDate;       // 반납 예정일
	 
	 public Rental(long rentId, long comicId, long memberId, String rentDate, String returnDate) {
		 this.rentId = rentId;
		 this.comicId = comicId;
		 this.memberId = memberId;
		 this.rentDate = rentDate;
		 this.returnDate = returnDate;
	     this.dueDate = dueDate;
    }
	 
	 public long getRentalId() {
	     return rentId;
	 }

	 public long getMemberId() {
	     return memberId;
	 }

	 public long getComicId() {
	     return comicId;
	 }

	 public String getRentalDate() {
	     return rentDate;
	 }

	 public String getDueDate() {
	     return dueDate;
	 }

	 public String getReturnDate() {
	     return returnDate;
	 }
	 
}
