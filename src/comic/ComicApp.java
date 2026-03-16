package comic;

import java.util.ArrayList;
import java.util.Scanner;

public class ComicApp {

    static ArrayList<Comic> comicList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int nextId = 1;

    public static void main(String[] args) {

        while (true) {
        	System.out.println();
        	System.out.println("💜 COMON 만화대여소 Java-CLI 프로그램");
        	System.out.println();

        	System.out.println("   ______   ____   __  ___   ____   _   __");
        	System.out.println("  / ____/  / __ \\ /  |/  /  / __ \\ / | / /");
        	System.out.println(" / /      / / / // /|_/ /  / / / //  |/ / ");
        	System.out.println("/ /___   / /_/ // /  / /  / /_/ // /|  /  ");
        	System.out.println("\\____/   \\____//_/  /_/   \\____//_/ |_/   ");

        	System.out.println();
        	System.out.println("=========================================");
        	System.out.println("              COMON Main Menu");
        	System.out.println("=========================================");
        	System.out.println("1. 만화 등록");
        	System.out.println("2. 만화 목록 조회");
        	System.out.println("3. 만화 상세 조회");
        	System.out.println("4. 만화 수정");
        	System.out.println("5. 만화 삭제");
        	System.out.println("0. 종료");
        	System.out.println("-----------------------------------------");
        	System.out.print("메뉴 선택 > ");

            int menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1:
                    addComic();
                    break;
                case 2:
                    listComics();
                    break;
                case 3:
                    detailComic();
                    break;
                case 4:
                    updateComic();
                    break;
                case 5:
                    deleteComic();
                    break;
                case 0:
                    System.out.println("프로그램 종료");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    public static void addComic() {

        System.out.print("제목: ");
        String title = sc.nextLine();

        System.out.print("작가: ");
        String author = sc.nextLine();

        System.out.print("출판사: ");
        String publisher = sc.nextLine();

        System.out.print("가격: ");
        int price = sc.nextInt();

        System.out.print("재고: ");
        int stock = sc.nextInt();
        sc.nextLine();

        Comic comic = new Comic(nextId++, title, author, publisher, price, stock);
        comicList.add(comic);

        System.out.println("만화 등록 완료!");
    }

    public static void listComics() {

        if (comicList.isEmpty()) {
            System.out.println("등록된 만화가 없습니다.");
            return;
        }

        System.out.println("\n===== 만화 목록 =====");

        for (Comic comic : comicList) {
            System.out.println(comic);
        }
    }

    public static void detailComic() {

        System.out.print("조회할 만화 ID 입력: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Comic comic : comicList) {
            if (comic.getComicId() == id) {
                System.out.println("\n===== 만화 상세 정보 =====");
                System.out.println(comic);
                return;
            }
        }

        System.out.println("해당 ID의 만화가 없습니다.");
    }

    public static void updateComic() {

        System.out.print("수정할 만화 ID 입력: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Comic comic : comicList) {

            if (comic.getComicId() == id) {

                System.out.print("새 제목: ");
                comic.setTitle(sc.nextLine());

                System.out.print("새 작가: ");
                comic.setAuthor(sc.nextLine());

                System.out.print("새 출판사: ");
                comic.setPublisher(sc.nextLine());

                System.out.print("새 가격: ");
                comic.setPrice(sc.nextInt());

                System.out.print("새 재고: ");
                comic.setStock(sc.nextInt());
                sc.nextLine();

                System.out.println("수정 완료!");
                return;
            }
        }

        System.out.println("해당 ID의 만화가 없습니다.");
    }

    public static void deleteComic() {

        System.out.print("삭제할 만화 ID 입력: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < comicList.size(); i++) {

            if (comicList.get(i).getComicId() == id) {

                comicList.remove(i);
                System.out.println("삭제 완료!");
                return;
            }
        }

        System.out.println("해당 ID의 만화가 없습니다.");
    }
}