package db;

@Table(name = "person")
public class Person {
    Integer personid;
    String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personid=" + personid +
                ", name='" + name + '\'' +
                '}';
    }
}
