import java.io.*;
public class EmployeeRecords {
    public static void main(String[] args) {
        Employee emp = new Employee(1, "Alex", 50000);
        emp.writeEmployee("EmployeeRecs.txt");
        Employee empRead = new Employee(0, "", 0);
        empRead.readEmployee("EmployeeRecs.txt");
    }
}
class Employee implements Serializable {
    int id;
    String name;
    double salary;
    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    public void writeEmployee(String path) {
        try (ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(path))) {
            write.writeObject(this);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readEmployee(String path) {
        try (ObjectInputStream read = new ObjectInputStream(new FileInputStream(path))) {
            try {
                while (true) {
                    Employee employee = (Employee) read.readObject();
                    System.out.println("id: " + employee.id + "\nname: " + employee.name + "\nsalary: " + employee.salary);
                }
            } catch (EOFException e) {}
        }catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
