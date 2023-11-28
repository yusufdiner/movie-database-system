public class Actor extends Performer {
    public String height;
    public Actor(String id, String name, String surname, String country, String height){
        super(id, name, surname, country);
        this.height=height;

    }
}
