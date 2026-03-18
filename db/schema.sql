CREATE DATABASE IF NOT EXISTS comic_rental;
USE comic_rental;

-- 1) 만화책 테이블 (comics)
CREATE TABLE IF NOT EXISTS comics (
  comic_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  author VARCHAR(255) NOT NULL,
  volume_number INT NOT NULL,
  rent_price INT NOT NULL,
  created_at DATE NOT NULL DEFAULT (CURRENT_DATE)
);

-- 2) 회원 테이블 (members)
CREATE TABLE IF NOT EXISTS members (
  member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  phone VARCHAR(30) NOT NULL UNIQUE,
  reg_date DATE NOT NULL DEFAULT (CURRENT_DATE)
);

-- 3) 대여 기록 테이블 (rentals)
CREATE TABLE IF NOT EXISTS rentals (
  rent_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  comic_id BIGINT NOT NULL,
  member_id BIGINT NOT NULL,
  rent_date DATE NOT NULL DEFAULT (CURRENT_DATE),
  return_date DATE NULL,

  CONSTRAINT fk_rentals_comic
    FOREIGN KEY (comic_id) REFERENCES comics(comic_id),

  CONSTRAINT fk_rentals_member
    FOREIGN KEY (member_id) REFERENCES members(member_id)
);

-- (선택) 테이블 생성 확인용
USE comic_rental;
SHOW TABLES;


SELECT *
FROM comics;

SELECT *
FROM members;

SELECT *
FROM rentals;