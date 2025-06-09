import java.util.ArrayList;
import java.util.*;

class Book{
    private String book_name;
    private String book_id;
    private String author ;
    private boolean isAvailable ;


    @Override
    public  String toString(){
        return "Book[Book_Title " + book_name + ", Book_ID " + book_id +",Author "+ author+", Availability " + isAvailable +"]";
    }


    public String getBook_name() {
        return book_name;
    }
    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
    public String getBook_id() {
        return book_id;
    }
    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    public Book(String book_name, String book_id, String author, boolean isAvailable) {
        this.book_name = book_name;
        this.book_id = book_id;
        this.author = author;
        this.isAvailable = isAvailable;
    }
}

class Student {
    private String student_name;
    private String student_id;

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
}

class Record {
    private Book book;
    private Student student;
    private Date issueDate;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Record(Book book, Student Student, Date issueDate) {
        this.book = book;
        this.student = Student;
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "Book: " + book.getBook_name() + ", Borrowed by: " + student.getStudent_name() + " (" + student.getStudent_id() + "), On: " + issueDate;
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Record> records = new ArrayList<>();


    public void addBooks(String book_name ,String book_id ,String author){
        Book newBook = new Book(book_name, book_id, author, true);
        books.add(newBook);
        System.out.println("Book Added succesfully ");
    }

    public void dispalyBook(){
        if (books.isEmpty()){
            System.out.println("No Books Available");
        }else {
            System.out.println("Available Books in Libraray");
            for (Book book : books){
                System.out.println(book);
            }
        }
    }

    public boolean searchBook(String book_name){
        for (Book book : books){
            if (book.getBook_name().equalsIgnoreCase(book_name)){
                if (book.isAvailable()){
                    System.out.println("Book is available" + book_name);
                    return true;
                }else {
                    System.out.println("Book" + book_name + "is borrowed");
                }
            }
            else {
                System.out.println("book not found");
                return false;
            }
        }
        return false;
    }

    public void borrowBook(String book_name) {
        for (Book book : books) {
            if (book.getBook_name().equalsIgnoreCase(book_name) && book.isAvailable()) {
                Scanner sc = new Scanner(System.in);
                Student st = new Student();

                System.out.print("Enter Student Name: ");
                st.setStudent_name(sc.nextLine());

                System.out.print("Enter Student ID: ");
                st.setStudent_id(sc.nextLine());

                Date d1 = new Date();
                book.setAvailable(false);

                Record rec = new Record(book, st, d1);
                records.add(rec);

                System.out.println("Book \"" + book_name + "\" is borrowed by " + st.getStudent_name() +
                        " (ID: " + st.getStudent_id() + ") on " + d1 + " for 7 days.");
                return;
            }
        }
        System.out.println("Book not available or not found.");
    }

    public void returnBook(String book_name , String student_id){
        for (Book book : books){
            if (book.getBook_name().equalsIgnoreCase(book_name)){
                if (book.isAvailable()){
                    System.out.println("The Book "+book_name+"is available");
                }

                for (Record rec : records){
                    if (rec.getBook().getBook_name().equalsIgnoreCase(book_name) &&
                            rec.getStudent().getStudent_id().equalsIgnoreCase(student_id)) {

                        book.setAvailable(true);
                        records.remove(rec);
                        System.out.println("Book " + book_name + " successfully returned by Student ID: " + student_id);
                        return;

                    }
                }
                System.out.println("No borrow record found for this student and book.");
                return;
            }
        }
    }



}


public class LibraryManagementSystem {
    public static void main(String[] args) {
        System.out.println("CORE Java Base Library Management System");

        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n----- Library Menu -----");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Search Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1:
                    System.out.print("Enter Book Name: ");
                    String bookName = sc.nextLine();
                    System.out.print("Enter Book ID: ");
                    String bookId = sc.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();
                    library.addBooks(bookName, bookId, author);
                    break;
                case 2:
                    library.dispalyBook();
                    break;

                case 3:
                    System.out.print("Enter Book Name to Search: ");
                    String searchName = sc.nextLine();
                    library.searchBook(searchName);
                    break;

                case 4:
                    System.out.print("Enter Book Name to Borrow: ");
                    String borrowName = sc.nextLine();
                    library.borrowBook(borrowName);
                    break;

                case 5:
                    System.out.print("Enter Book Name to Return: ");
                    String returnName = sc.nextLine();
                    System.out.print("Enter Student ID: ");
                    String studentId = sc.nextLine();
                    library.returnBook(returnName, studentId);
                    break;

                case 6:
                    System.out.println("Exiting the system. Goodbye!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

    }
}