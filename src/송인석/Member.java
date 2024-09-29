package 송인석;
public class Member {

    private String name; // 이름
    private int age; // 나이
    private int id; // 중복될 수 없는 id

    // 생성자
    public Member() {}
    public Member(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    // Getter and Setter
    public String getName() { return name; }

    public void setName(String name) { this.name = name;}

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    // toString()
    @Override
    public String toString() {
        return "name: " + name + ", age: " + age + ", id: " + id;
    }
}
