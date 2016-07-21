package app.go.search.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FilmDetail implements Parcelable {
    public static final Creator<FilmDetail> CREATOR = new Creator<FilmDetail>() {
        @Override
        public FilmDetail createFromParcel(Parcel in) {
            return new FilmDetail(in);
        }

        @Override
        public FilmDetail[] newArray(int size) {
            return new FilmDetail[size];
        }
    };
    private String Released;
    private String Type;
    private String imdbVotes;
    private String Runtime;
    private String Response;
    private String Poster;
    private String imdbID;
    private String Country;
    private String Title;
    private String imdbRating;
    private String Year;
    private String Rated;
    private String Actors;
    private String Plot;
    private String Metascore;
    private String Writer;
    private String Genre;
    private String Language;
    private String Awards;
    private String Director;

    public FilmDetail(Parcel in) {
        Released = in.readString();
        Type = in.readString();
        imdbVotes = in.readString();
        Runtime = in.readString();
        Response = in.readString();
        Poster = in.readString();
        imdbID = in.readString();
        Country = in.readString();
        Title = in.readString();
        imdbRating = in.readString();
        Year = in.readString();
        Rated = in.readString();
        Actors = in.readString();
        Plot = in.readString();
        Metascore = in.readString();
        Writer = in.readString();
        Genre = in.readString();
        Language = in.readString();
        Awards = in.readString();
        Director = in.readString();
    }

    public FilmDetail() {

    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String Released) {
        this.Released = Released;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String Runtime) {
        this.Runtime = Runtime;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String Response) {
        this.Response = Response;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String Poster) {
        this.Poster = Poster;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getRated() {
        return Rated;
    }

    public void setRated(String Rated) {
        this.Rated = Rated;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String Actors) {
        this.Actors = Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String Plot) {
        this.Plot = Plot;
    }

    public String getMetascore() {
        return Metascore;
    }

    public void setMetascore(String Metascore) {
        this.Metascore = Metascore;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String Writer) {
        this.Writer = Writer;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String Awards) {
        this.Awards = Awards;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    @Override
    public String toString() {
        return "ClassPojo [Released = " + Released + ", Type = " + Type + ", imdbVotes = " + imdbVotes + ", Runtime = " + Runtime + ", Response = " + Response + ", Poster = " + Poster + ", imdbID = " + imdbID + ", Country = " + Country + ", Title = " + Title + ", imdbRating = " + imdbRating + ", Year = " + Year + ", Rated = " + Rated + ", Actors = " + Actors + ", Plot = " + Plot + ", Metascore = " + Metascore + ", Writer = " + Writer + ", Genre = " + Genre + ", Language = " + Language + ", Awards = " + Awards + ", Director = " + Director + "]";
    }

    public String testJunitResult(String title,String year) {

        return title + "-test-" + year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Released);
        dest.writeString(Type);
        dest.writeString(imdbVotes);
        dest.writeString(Runtime);
        dest.writeString(Response);
        dest.writeString(Poster);
        dest.writeString(imdbID);
        dest.writeString(Country);
        dest.writeString(Title);
        dest.writeString(imdbRating);
        dest.writeString(Year);
        dest.writeString(Rated);
        dest.writeString(Actors);
        dest.writeString(Plot);
        dest.writeString(Metascore);
        dest.writeString(Writer);
        dest.writeString(Genre);
        dest.writeString(Language);
        dest.writeString(Awards);
        dest.writeString(Director);
    }
}