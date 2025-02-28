package DSA;



import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String name;
    private double gpa;

    Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    // Getters to access private fields
    public int getId() { return id; }
    public String getName() { return name; }
    public double getGpa() { return gpa; }
}

public class StudentSerialization {
    public static void main(String[] args) {
        Student student = new Student(101, "Alice", 3.8);
        saveStudent(student);
        loadStudent();
    }

    static void saveStudent(Student student) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            out.writeObject(student);
            System.out.println("Student data saved.");
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    static void loadStudent() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student student = (Student) in.readObject();
            System.out.println("Student loaded: " + student.getId() + ", " + student.getName() + ", " + student.getGpa());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }
    }
}

