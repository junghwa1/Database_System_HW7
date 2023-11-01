import java.sql.*;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.84.3:4567/madang","junghwa", "dua6531");
            Statement statement = connection.createStatement();

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Database 기능");
                System.out.println("1. 삽입 \t2. 삭제 \t3. 검색 \t4. 종료");
                System.out.print("선택: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // 데이터 삽입
                        System.out.print("Book ID: ");
                        int bookid = scanner.nextInt();
                        System.out.print("Book Name: ");
                        String bookname = scanner.next();
                        System.out.print("Publisher: ");
                        String publisher = scanner.next();
                        System.out.print("Price: ");
                        int price = scanner.nextInt();

                        String insertQuery = "INSERT INTO Book (bookid, bookname, publisher, price) VALUES (" +
                                bookid + ", '" + bookname + "', '" + publisher + "', " + price + ")";
                        statement.executeUpdate(insertQuery);
                        System.out.println("책 정보가 삽입되었습니다.");
                        break;

                    case 2:
                        // 데이터 삭제
                        System.out.print("삭제할 Book ID: ");
                        int deleteId = scanner.nextInt();
                        String deleteQuery = "DELETE FROM Book WHERE bookid=" + deleteId;
                        statement.executeUpdate(deleteQuery);
                        System.out.println("입력한 Book Id의 책 정보가 삭제되었습니다.");
                        break;

                    case 3:
                        // 데이터 검색
                        System.out.print("검색할 Book ID: ");
                        int searchId = scanner.nextInt();
                        String selectQuery = "SELECT * FROM Book WHERE bookid=" + searchId;
                        ResultSet resultSet = statement.executeQuery(selectQuery);
                        while (resultSet.next()) {
                            System.out.println("Book ID: " + resultSet.getInt("bookid") +
                                    "| Book Name: " + resultSet.getString("bookname") +
                                    "| Publisher: " + resultSet.getString("publisher") +
                                    "| Price: " + resultSet.getInt("price"));
                        }
                        resultSet.close();
                        break;

                    case 4:
                        // 종료
                        statement.close();
                        connection.close();
                        scanner.close();
                        System.out.println("프로그램을 종료합니다.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("1부터 4사이의 정수를 입력해주세요.");
                        break;
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}