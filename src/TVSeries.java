public class TVSeries extends Film{
    public String startDate;
    public String endDate;
    public String season;
    public String episodes;
    public String genre;
    public String writers;
    public TVSeries(String filmID, String filmTitle, String language,String director, String runtime, String country,  String cast,String genre,String writers,String startDate,String endDate,String season,String episodes){
        super(filmID, filmTitle, language, director, runtime, country, cast);
        this.startDate=startDate;
        this.endDate=endDate;
        this.season=season;
        this.episodes=episodes;
        this.genre=genre;
        this.writers=writers;
    }
}
