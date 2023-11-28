
public class User extends Person {
    public int filmRating ;
    public User( String id, String name,String surname,String country){
        super(id, name, surname, country);
    }
    public User(String id, int filmRating){
        super(id);
        this.filmRating=filmRating;
    }

}
