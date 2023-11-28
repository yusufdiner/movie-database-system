public class ShortFilm extends Film{
    public String releaseDate;
    public String writers;
    public String genre;
    public ShortFilm(String filmID, String filmTitle, String language,String director, String runtime, String country,  String cast,String genre,String releaseDate,String writers){
        super(filmID, filmTitle, language, director, runtime, country, cast);
        this.releaseDate=releaseDate;
        this.genre=genre;
        this.writers=writers;
    }
}
