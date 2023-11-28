public class ChildActor extends Performer{
    public String age;
    public ChildActor(String id, String name, String surname, String country, String age){
        super(id, name, surname, country);
        this.age=age;
    }
}
