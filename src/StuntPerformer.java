public class StuntPerformer extends Performer{
    public String height;
    public String realActorID;
    public StuntPerformer(String id, String name, String surname, String country, String height,String realActorID){
        super(id, name, surname, country);
        this.height=height;
        this.realActorID=realActorID;
    }

}
