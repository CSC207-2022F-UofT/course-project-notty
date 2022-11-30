import gateway.DBConnection;

public class Main {
    static DBConnection database;

    public static void main(String[] args) {
        System.out.println("Hello");
        database=new DBConnection();
    }
}
