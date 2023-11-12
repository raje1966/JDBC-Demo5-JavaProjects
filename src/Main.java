import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String username = "root";
    private static final String password = "password";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());;
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO students(name, age, marks) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.print("Enter name: ");
                String name = scanner.next();
                System.out.print("Enter age: ");
                int age = scanner.nextInt();
                System.out.print("Enter marks: ");
                double marks = scanner.nextDouble();
                System.out.print("Enter more data(Y/N): ");
                String choice = scanner.next();
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setDouble(3, marks);


                preparedStatement.addBatch();
                if(choice.toUpperCase().equals("N")) {
                    break;
                }
            }
            int[] arr = preparedStatement.executeBatch();
//                if(rowsAffected>0) {
//                System.out.println("Data Updated successfully");
//                }else {
//                System.out.println("Data not updated");
//            }

//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setDouble(1, 87.5);
//            preparedStatement.setInt(2, 3);
            for(int i = 0; i < arr.length; i++) {
                if(arr[i] ==0) {
                    System.out.println("Query: "+i+ "not executed successfully!!");
                }
            }


        }catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }
}