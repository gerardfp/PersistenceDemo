package db;

@Table(name = "thing")
public class Thing {
    Integer thingid;
    String title;
    Integer personid;

    public Thing() {
    }

    public Thing(String title) {
        this.title = title;
    }

    public Thing(String title, int personid) {
        this.title = title;
        this.personid = personid;
    }

    public int getThingid() {
        return thingid;
    }

    public void setThingid(int thingid) {
        this.thingid = thingid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "thingid=" + thingid +
                ", title='" + title + '\'' +
                ", personid=" + personid +
                '}';
    }
}
