/**
 * Person.java
 * This is an Abstract Class that serves as the base class for all person types.
 * It demonstrates: Abstract Class, Encapsulation, Constructors
 */

public abstract class Person {
    // Private fields - Encapsulation (data hiding)
    private String id;
    private String name;
    private int age;
    private String email;
    private String phone;
    private String address;

    // Default Constructor - Method Overloading (Constructor Overloading)
    public Person() {
        this.id = "";
        this.name = "";
        this.age = 0;
        this.email = "";
        this.phone = "";
        this.address = "";
    }

    // Parameterized Constructor with 3 parameters - Method Overloading
    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = "";
        this.phone = "";
        this.address = "";
    }

    // Parameterized Constructor with all parameters - Method Overloading
    public Person(String id, String name, int age, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getter and Setter methods - Encapsulation
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Abstract method - must be implemented by subclasses
    // This enforces that all Person types must provide their role
    public abstract String getRole();

    // Abstract method for getting formatted details
    public abstract String getFormattedDetails();

    // Concrete method available to all subclasses
    public boolean isValidEmail() {
        return email != null && email.contains("@") && email.contains(".");
    }

    // Concrete method for phone validation
    public boolean isValidPhone() {
        return phone != null && phone.length() >= 10;
    }

    // Override toString() method - Method Overriding from Object class
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age;
    }

    // Method to convert Person data to file format (CSV-like)
    public String toFileString() {
        return id + "," + name + "," + age + "," + email + "," + phone + "," + address;
    }
}
