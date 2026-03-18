package model;

public class Comic {
	
	 private int comicId;          // 만화책 ID (PK)
	 private String title;         // 제목
	 private String author;        // 작가
	 private int volumeNumber;     // 시리즈 권수 (1권, 2권 등)
	 private int rentPrice;        // 대여 가격
	 private String createdAt;     // 등록일
	 
	 public Comic(int comicId, String title, String author, int volumeNumber, int rentPrice, String createdAt) {
		 this.comicId = comicId;
	     this.title = title;
	     this.author = author;
	     this.volumeNumber = volumeNumber;
	     this.rentPrice = rentPrice;
	     this.createdAt = createdAt;
	 }
	 
	 public int getComicId() {
	     return comicId;
	 }

	 public String getTitle() {
	     return title;
	 }

	 public String getAuthor() {
	     return author;
	 }

	 public int getVolumeNumber() {
	     return volumeNumber;
	 }

	 public int getRentPrice() {
	     return rentPrice;
	 }

	 public String getCreatedAt() {
	     return createdAt;
	 }
	 
}
