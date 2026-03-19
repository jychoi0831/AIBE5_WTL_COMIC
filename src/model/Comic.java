package model;

import java.time.LocalDate;

public class Comic {
	
	private long comicId;           // 만화책ID (PK)
	private String title;           // 제목
	private String author;          // 작가
	private long volumeNumber;      // 시리즈 권수 (1권, 2권 ...)
	private long rentPrice;         // 대여 가격
	private LocalDate createdAt;    // 등록일
	
	public Comic(long comicId, String title, String author, long volumeNumber, long rentPrice, LocalDate createdAt) {
		 this.comicId = comicId;
	     this.title = title;
	     this.author = author;
	     this.volumeNumber = volumeNumber;
	     this.rentPrice = rentPrice;
	     this.createdAt = createdAt;
	}
	
	// 만화책ID 반환
	public long getComicId() {
        return comicId;
    }
	
	// 만화책ID 저장 및 수정
    public void setComicId(long comicId) {
        this.comicId = comicId;
    }
    
    // 제목 반환
    public String getTitle() {
        return title;
    }
    
    // 제목 저장 및 수정
    public void setTitle(String title) {
        this.title = title;
    }
    
    // 작가 반환
    public String getAuthor() {
        return author;
    }
    
    // 작가 저장 및 수정
    public void setAuthor(String author) {
        this.author = author;
    }
    
    // 시리즈 권수 반환
    public long getVolumeNumber() {
        return volumeNumber;
    }
    
    // 시리즈 권수 저장 및 수정
    public void setVolumeNumber(long volumeNumber) {
        this.volumeNumber = volumeNumber;
    }
    
    // 대여 가격 반환
    public long getRentPrice() {
        return rentPrice;
    }
    
    // 대여 가격 저장 및 수정
    public void setRentPrice(long rentPrice) {
        this.rentPrice = rentPrice;
    }
    
    // 등록일 반환
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    
    // 등록일 저장 및 수정
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
