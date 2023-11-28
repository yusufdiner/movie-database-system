import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader peopleFile = new FileReader(args[0]);
        BufferedReader bufferedReader1 = new BufferedReader(peopleFile);
        String peopleFileLine = bufferedReader1.readLine();
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Actor> actors = new ArrayList<>();
        ArrayList<ChildActor> childActors = new ArrayList<>();
        ArrayList<StuntPerformer> stuntPerformers = new ArrayList<>();
        ArrayList<Director> directors = new ArrayList<>();
        ArrayList<Writer> writers = new ArrayList<>();
        HashMap<String, ArrayList<Film>> userRated = new HashMap<>();
        LinkedHashMap<String, ArrayList<User>> filmRated = new LinkedHashMap<>();
        LinkedHashMap<String, String> ratings = new LinkedHashMap<>();

        //listing people by their job from people.txt file
        while (peopleFileLine != null) {
            String[] splited = peopleFileLine.split("\\s+");
            String[] List1 = new String[7];

            for (int i = 0; i < splited.length; i++) {


                List1[i] = splited[i];
            }


            if (List1[0].equals("User:")) {
                users.add(new User(List1[1], List1[2], List1[3], List1[4]));
            } else if (List1[0].equals("Actor:")) {
                actors.add(new Actor(List1[1], List1[2], List1[3], List1[4], List1[5]));
            } else if (List1[0].equals("ChildActor:")) {
                childActors.add(new ChildActor(List1[1], List1[2], List1[3], List1[4], List1[5]));
            } else if (List1[0].equals("StuntPerformer:")) {
                stuntPerformers.add(new StuntPerformer(List1[1], List1[2], List1[3], List1[4], List1[5], List1[6]));
            } else if (List1[0].equals("Director:")) {
                directors.add(new Director(List1[1], List1[2], List1[3], List1[4], List1[5]));
            } else if (List1[0].equals("Writer:")) {
                writers.add(new Writer(List1[1], List1[2], List1[3], List1[4], List1[5]));
            }


            peopleFileLine = bufferedReader1.readLine();

        }
        peopleFile.close();
        FileReader filmFile = new FileReader(args[1]);
        BufferedReader bufferedReader2 = new BufferedReader(filmFile);
        String filmFileLine = bufferedReader2.readLine();
        ArrayList<Film> basicFilms = new ArrayList<>();
        ArrayList<FeatureFilm> featureFilms = new ArrayList<>();
        ArrayList<ShortFilm> shortFilms = new ArrayList<>();
        ArrayList<Documentary> documentaries = new ArrayList<>();
        ArrayList<TVSeries> tvSeries = new ArrayList<>();

        //listing films by their type from film.txt file
        while (filmFileLine != null) {
            String[] splited = filmFileLine.split("\\s+");
            String[] List1 = new String[14];

            for (int i = 0; i < splited.length; i++) {

                List1[i] = splited[i];
            }
            basicFilms.add(new Film(List1[1], List1[2], List1[3], List1[4], List1[5], List1[6], List1[7]));
            if (List1[0].equals("FeatureFilm:")) {
                featureFilms.add(new FeatureFilm(List1[1], List1[2], List1[3], List1[4], List1[5], List1[6], List1[7], List1[8], List1[9], List1[10], List1[11]));
            } else if (List1[0].equals("ShortFilm:")) {
                if (Integer.parseInt(List1[5]) <= 40) {
                    shortFilms.add(new ShortFilm(List1[1], List1[2], List1[3], List1[4], List1[5], List1[6], List1[7], List1[8], List1[9], List1[10]));
                } else System.out.println(" A short film cannot be longer than 40 minutes!");

            } else if (List1[0].equals("Documentary:")) {
                documentaries.add(new Documentary(List1[1], List1[2], List1[3], List1[4], List1[5], List1[6], List1[7], List1[8]));
            } else if (List1[0].equals("TVSeries:")) {
                tvSeries.add(new TVSeries(List1[1], List1[2], List1[3], List1[4], List1[5], List1[6], List1[7], List1[8], List1[9], List1[10], List1[11], List1[12], List1[13]));
            }
            filmFileLine = bufferedReader2.readLine();
        }
        filmFile.close();
        FileReader commandFile = new FileReader(args[2]);
        BufferedReader bufferedReader3 = new BufferedReader(commandFile);
        FileWriter outputfile = new FileWriter(args[3]);
        BufferedWriter output = new BufferedWriter(outputfile);
        String commandFileLine = bufferedReader3.readLine();
        String line = "\n-----------------------------------------------------------------------------------------------------\n";
        //commands from commands.txt file
        while (commandFileLine != null) {
            String[] splited = commandFileLine.split("\\s+");
            String[] List1 = new String[14];

            for (int i = 0; i < splited.length; i++) {

                List1[i] = splited[i];
            }
            // "RATE" command
            if (List1[0].equals("RATE")) {
                output.write(commandFileLine + "\n");
                int rate = Integer.parseInt(List1[3]);
                int controller = 0;

                if (!userRated.containsKey(List1[1])) {//userid - film object hashmap
                    if (Integer.parseInt(List1[3]) > 0 && Integer.parseInt(List1[3]) < 11) {
                        for (int x = 0; x < users.size(); x++) {
                            for (int y = 0; y < basicFilms.size(); y++) {
                                if (List1[1].equals(users.get(x).id) && List1[2].equals(basicFilms.get(y).filmID)) {

                                    ArrayList<Film> userRating2 = new ArrayList<>();
                                    userRating2.add(new Film(List1[2], rate));
                                    userRated.put(List1[1], userRating2);
                                    output.write("\nFilm rated successfully\n");
                                    controller++;
                                    for (int j = 0; j < featureFilms.size(); j++) {
                                        if (featureFilms.get(j).filmID.equals(List1[2])) {
                                            output.write("Film type:\tFeatureFilm\n");
                                            output.write("Film title:\t" + featureFilms.get(j).filmTitle + "\n");
                                            break;
                                        }
                                    }
                                    for (int j = 0; j < shortFilms.size(); j++) {
                                        if (shortFilms.get(j).filmID.equals(List1[2])) {
                                            output.write("Film type:\tShortFilm\n");
                                            output.write("Film title:\t" + shortFilms.get(j).filmTitle + "\n");
                                            break;
                                        }
                                    }
                                    for (int j = 0; j < documentaries.size(); j++) {
                                        if (documentaries.get(j).filmID.equals(List1[2])) {
                                            output.write("Film type:\tDocumentary\n");
                                            output.write("Film title:\t" + documentaries.get(j).filmTitle + "\n");
                                            break;
                                        }
                                    }
                                    for (int j = 0; j < tvSeries.size(); j++) {
                                        if (tvSeries.get(j).filmID.equals(List1[2])) {
                                            output.write("Film type:\tTVSeries\n");
                                            output.write("Film title:\t" + tvSeries.get(j).filmTitle + "\n");
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    } else System.out.println("The rate is not between 1 and 10");
                } else if (userRated.containsKey(List1[1])) {
                    for (int y = 0; y < userRated.get(List1[1]).size(); y++) {
                        for (int x = 0; x < basicFilms.size(); x++) {
                            if (!userRated.get(List1[1]).get(y).filmID.equals(List1[2])&&basicFilms.get(x).filmID.equals(List1[2])) {
                                ArrayList<Film> userRating2;
                                userRating2 = userRated.get(List1[1]);
                                userRating2.add(new Film(List1[2], rate));
                                userRated.put(List1[1], userRating2);
                                output.write("\nFilm rated successfully\n");
                                controller++;
                                for (int j = 0; j < featureFilms.size(); j++) {
                                    if (featureFilms.get(j).filmID.equals(List1[2])) {
                                        output.write("Film type:\tFeatureFilm\n");
                                        output.write("Film title:\t" + featureFilms.get(j).filmTitle + "\n");
                                        break;
                                    }
                                }
                                for (int j = 0; j < shortFilms.size(); j++) {
                                    if (shortFilms.get(j).filmID.equals(List1[2])) {
                                        output.write("Film type:\tShortFilm\n");
                                        output.write("Film title:\t" + shortFilms.get(j).filmTitle + "\n");
                                        break;
                                    }
                                }
                                for (int j = 0; j < documentaries.size(); j++) {
                                    if (documentaries.get(j).filmID.equals(List1[2])) {
                                        output.write("Film type:\tDocumentary\n");
                                        output.write("Film title:\t" + documentaries.get(j).filmTitle + "\n");
                                        break;
                                    }
                                }
                                for (int j = 0; j < tvSeries.size(); j++) {
                                    if (tvSeries.get(j).filmID.equals(List1[2])) {
                                        output.write("Film type:\tTVSeries\n");
                                        output.write("Film title:\t" + tvSeries.get(j).filmTitle + "\n");
                                        break;
                                    }
                                }
                                break;
                            } else if (userRated.get(List1[1]).get(y).filmID.equals(List1[2])) {
                                output.write("\nThis film was earlier rated\n");
                                controller++;
                                break;
                            }
                        }

                    }

                }
                if (controller < 1) {
                    output.write("\nCommand Failed\n");
                    output.write("User ID:\t" + List1[1] + "\n");
                    output.write("Film ID:\t" + List1[2] + "\n");
                }
                if (!filmRated.containsKey(List1[2])) {// fılmid - object hashmap
                    for (int x = 0; x < users.size(); x++) {
                        for (int y = 0; y < basicFilms.size(); y++) {
                            if (users.get(x).id.equals(List1[1]) && basicFilms.get(y).filmID.equals(List1[2])) {
                                ArrayList<User> filmRating1 = new ArrayList<>();
                                filmRating1.add(new User(List1[1], rate));
                                filmRated.put(List1[2], filmRating1);
                                break;
                            }
                        }
                    }
                } else if (filmRated.containsKey(List1[2])) {
                    int counter = 0;
                    for (int x = 0; x < users.size(); x++) {
                        for (int y = 0; y < basicFilms.size(); y++) {
                            if (List1[1].equals(users.get(x).id) && List1[2].equals(basicFilms.get(y).filmID)) {
                                counter++;
                            }
                        }
                    }
                    for (int w = 0; w < filmRated.get(List1[2]).size(); w++) {
                        if (!filmRated.get(List1[2]).get(w).id.equals(List1[1]) && counter != 0) {
                            ArrayList<User> filmRating2;
                            filmRating2 = filmRated.get(List1[2]);
                            filmRating2.add(new User(List1[1], rate));
                            filmRated.put(List1[2], filmRating2);
                            break;

                        }
                    }
                }
                for (int q = 0; q < basicFilms.size(); q++) {
                    double summary = 0;
                    double rating = 0;
                    try {//calculate rate
                        for (int w = 0; w < filmRated.get(basicFilms.get(q).filmID).size(); w++) {
                            summary += filmRated.get(basicFilms.get(q).filmID).get(w).filmRating;
                        }
                        int userNumber = filmRated.get(basicFilms.get(q).filmID).size();
                        rating = summary / userNumber;

                    } catch (NullPointerException e) {
                        ratings.put(basicFilms.get(q).filmID, "0");
                        continue;
                    }
                    double roundOff = (double) Math.round(rating * 10) / 10;
                    String rO = String.valueOf(roundOff);
                    String lastChar = rO.substring(rO.length() - 1);

                    if (lastChar.equals("0")) {//rates change double to integer
                        String intRate = String.valueOf(roundOff).substring(0, rO.indexOf("."));
                        ratings.put(basicFilms.get(q).filmID, intRate);
                    } else if (!lastChar.equals("0")) {
                        ratings.put(basicFilms.get(q).filmID, rO);
                    }
                }
                output.write(line);
            } else if (List1[0].equals("REMOVE")) {// "REMOVE RATE" command
                output.write(commandFileLine + "\n\n");
                try {
                    int counter = 0;
                    for (int x = 0; x < userRated.get(List1[2]).size(); x++) {
                        if (userRated.get(List1[2]).get(x).filmID.equals(List1[3])) {
                            for (int k = 0; k < basicFilms.size(); k++) {
                                if (userRated.get(List1[2]).get(x).filmID.equals(basicFilms.get(k).filmID)) {
                                    output.write("Your film rating was removed successfully\n");
                                    output.write("Film title:\t" + basicFilms.get(k).filmTitle + "\t\n");
                                }
                            }
                            userRated.get(List1[2]).remove(userRated.get(List1[2]).get(x));//remove rate from userrated hashmap
                            counter++;
                            for (int y = 0; y < filmRated.get(List1[3]).size(); y++) {
                                if (filmRated.get(List1[3]).get(y).id.equals(List1[2])) {
                                    double sum = Double.parseDouble(ratings.get(List1[3])) * filmRated.get(List1[3]).size();
                                    if (filmRated.get(List1[3]).size() == 1) {
                                        ratings.put(List1[3], "0");
                                    } else
                                        ratings.put(List1[3], String.valueOf(sum / (filmRated.get(List1[3]).size() - 1)));

                                    filmRated.get(List1[3]).remove(filmRated.get(List1[3]).get(y));

                                }
                            }
                        }
                    }
                    if (counter < 1) {
                        output.write("Command Failed\n" + "User ID:\t" + List1[2] + "\nFilm ID:\t" + List1[3] + "\n");
                    }
                } catch (NullPointerException e) {
                    output.write("Command Failed\n" + "User ID:\t" + List1[2] + "\nFilm ID:\t" + List1[3] + "\n");

                }
                output.write(line);
            } else if (List1[0].equals("LIST") && List1[1].equals("FILMS") && List1[3].equals("RATE")) {// "LIST FILMS BY RATE" command
                output.write(commandFileLine + "\n\n");
                LinkedHashMap<String, String> sortedRatings = new LinkedHashMap<>();
                String[] keysList1 = ratings.keySet().toArray(new String[0]);
                String[] valueList = ratings.values().toArray(new String[0]);
                ArrayList<String> valueArray = new ArrayList<>(Arrays.asList(valueList));
                valueArray.sort(Collections.reverseOrder());
                for (int k = 0; k < valueArray.size(); k++) {//sorted rating hashmap
                    for (int l = 0; l < keysList1.length; l++) {
                        if (valueArray.get(k).equals(ratings.get(keysList1[l]))) {
                            sortedRatings.put(keysList1[l], valueArray.get(k));
                        }
                    }
                }
                String[] keysList = sortedRatings.keySet().toArray(new String[0]);
                int featureFilmsCounter = 0;
                output.write("FeatureFilm:\n");
                for (int x = 0; x < keysList.length; x++) {//rated featurefilms
                    for (int y = 0; y < featureFilms.size(); y++) {
                        if (keysList[x].equals(featureFilms.get(y).filmID)) {
                            output.write(featureFilms.get(y).filmTitle + "\t(" + featureFilms.get(y).releaseDate.substring(featureFilms.get(y).releaseDate.length() - 4) + ")\tRatings:\t");
                            if (filmRated.containsKey(keysList[x])) {
                                output.write(ratings.get(keysList[x]).replace(".", ",") + "/10\tfrom\t" + filmRated.get(keysList[x]).size() + "\tusers\n");
                                featureFilmsCounter++;
                            } else if (!filmRated.containsKey(keysList[x])) {
                                output.write(ratings.get(keysList[x]) + "/10\tfrom\t0\tusers\n");
                                featureFilmsCounter++;
                            }
                        }
                    }
                }
                if (featureFilmsCounter == 0) {
                    output.write("\nNo Result\n");
                }
                int shortFilmsCounter = 0;
                output.write("\nShortFilm:\n");
                for (int x = 0; x < keysList.length; x++) {//rated shortfilms
                    for (int y = 0; y < shortFilms.size(); y++) {
                        if (keysList[x].equals(shortFilms.get(y).filmID)) {
                            output.write(shortFilms.get(y).filmTitle + "\t(" + shortFilms.get(y).releaseDate.substring(shortFilms.get(y).releaseDate.length() - 4) + ")\tRatings:\t");
                            if (filmRated.containsKey(keysList[x])) {
                                output.write(ratings.get(keysList[x]).replace(".", ",") + "/10\tfrom\t" + filmRated.get(keysList[x]).size() + "\tusers\n");
                                shortFilmsCounter++;
                            } else if (!filmRated.containsKey(keysList[x])) {
                                output.write(ratings.get(keysList[x]) + "/10\tfrom\t0\tusers\n");
                                shortFilmsCounter++;
                            }
                        }
                    }
                }
                if (shortFilmsCounter == 0) {
                    output.write("\nNo Result\n");
                }
                int documentaryCounter = 0;
                output.write("\nDocumentary:\n");
                for (int x = 0; x < keysList.length; x++) {//rated documentaries
                    for (int y = 0; y < documentaries.size(); y++) {
                        if (keysList[x].equals(documentaries.get(y).filmID)) {
                            output.write(documentaries.get(y).filmTitle + "\t(" + documentaries.get(y).releaseDate.substring(documentaries.get(y).releaseDate.length() - 4) + ")\tRatings:\t");
                            if (filmRated.containsKey(keysList[x])) {
                                output.write(ratings.get(keysList[x]).replace(".", ",") + "/10\tfrom\t" + filmRated.get(keysList[x]).size() + "\tusers\n");
                                documentaryCounter++;
                            } else if (!filmRated.containsKey(keysList[x])) {
                                output.write(ratings.get(keysList[x]) + "/10\tfrom\t0\tusers\n");
                                documentaryCounter++;
                            }
                        }
                    }
                }
                if (documentaryCounter == 0) {
                    output.write("\nNo Result\n");
                }
                int tvSeriesCounter = 0;
                output.write("\nTVSeries:\n");//rated tv series
                for (int x = 0; x < keysList.length; x++) {
                    for (int y = 0; y < tvSeries.size(); y++) {
                        if (keysList[x].equals(tvSeries.get(y).filmID)) {
                            output.write(tvSeries.get(y).filmTitle + "\t(" + tvSeries.get(y).startDate.substring(featureFilms.get(y).releaseDate.length() - 4) + "-" + tvSeries.get(y).endDate.substring(tvSeries.get(y).endDate.length() - 4) + ")\tRatings:\t");
                            if (filmRated.containsKey(keysList[x])) {
                                output.write(ratings.get(keysList[x]).replace(".", ",") + "/10\tfrom\t" + filmRated.get(keysList[x]).size() + "\tusers\n");
                                tvSeriesCounter++;
                            } else if (!filmRated.containsKey(keysList[x])) {
                                output.write(ratings.get(keysList[x]) + "/10\tfrom\t0\tusers\n");
                                tvSeriesCounter++;
                            }
                        }
                    }
                }
                if (tvSeriesCounter == 0) {
                    output.write("\nNo Result\n");
                }
                output.write(line);
            } else if (List1[0].equals("EDIT")) {// "EDIT RATE" command
                output.write(commandFileLine + "\n\n");
                try {
                    int counter = 0;
                    for (int x = 0; x < userRated.get(List1[2]).size(); x++) {
                        if (userRated.get(List1[2]).get(x).filmID.equals(List1[3])) {
                            for (int k = 0; k < basicFilms.size(); k++) {
                                if (userRated.get(List1[2]).get(x).filmID.equals(basicFilms.get(k).filmID)) {//update rate userrated hashmap
                                    output.write("New ratings done successfully\n");
                                    output.write("Film title:\t" + basicFilms.get(k).filmTitle + ":\t" + userRated.get(List1[2]).get(x).rating + "\n");
                                    output.write("Your rating:\t" + List1[4] + "\n");
                                }
                            }
                            userRated.get(List1[2]).get(x).rating = Integer.parseInt(List1[4]);
                            counter++;
                            for (int y = 0; y < filmRated.get(List1[3]).size(); y++) {//update rate filmrated hashmap
                                if (filmRated.get(List1[3]).get(y).id.equals(List1[2])) {
                                    double sum = Double.parseDouble(ratings.get(List1[3])) * filmRated.get(List1[3]).size();
                                    sum += Integer.parseInt(List1[4]);
                                    ratings.put(List1[3], String.valueOf(sum / (filmRated.get(List1[3]).size())));
                                    filmRated.get(List1[3]).get(y).filmRating = Integer.parseInt(List1[4]);
                                }
                            }
                        }
                    }
                    if (counter < 1) {
                        output.write("Command Failed\n" + "User ID:\t" + List1[2] + "\nFilm ID:\t" + List1[3] + "\n");
                    }
                } catch (NullPointerException e) {
                    output.write("Command Failed\n" + "User ID:\t" + List1[2] + "\nFilm ID:\t" + List1[3] + "\n");

                }
                output.write(line);
            } else if (List1[0].equals("LIST") && List1[1].equals("USER")) {// "LIST USER RATES" command
                output.write(commandFileLine + "\n\n");
                try {
                    for (int j = 0; j < userRated.get(List1[2]).size(); j++) {//check users
                        for (int k = 0; k < basicFilms.size(); k++) {
                            if (userRated.get(List1[2]).get(j).filmID.equals(basicFilms.get(k).filmID)) {
                                output.write(basicFilms.get(k).filmTitle + ":\t" + userRated.get(List1[2]).get(j).rating + "\n");
                            }
                        }
                    }
                } catch (NullPointerException e) {
                    output.write("Command Failed\n" + "User ID:\t" + List1[2] + "\n");
                }
                output.write(line);
            } else if (List1[0].equals("ADD")) {// "ADD FEATUREFILMS" command
                output.write(commandFileLine + "\n");
                int counter = 0;
                for (int x = 0; x < featureFilms.size(); x++) {
                    if (featureFilms.get(x).filmID.equals(List1[2])) {
                        counter++;
                    }
                }
                int counter2 = 0;
                String[] directorsArray = List1[5].split(",");
                String[] castArray = List1[8].split(",");
                String[] writersArray = List1[11].split(",");
                ArrayList<String> writersList = new ArrayList<>();
                ArrayList<String> directorsList = new ArrayList<>();
                ArrayList<String> castList = new ArrayList<>();

                for (int x = 0; x < featureFilms.size(); x++) {

                    for (Director director : directors) {
                        directorsList.add(director.id);
                    }
                    for (Writer writer : writers) {
                        writersList.add(writer.id);
                    }
                    for (Actor actor : actors) {
                        castList.add(actor.id);
                    }
                    for (ChildActor childActor : childActors) {
                        castList.add(childActor.id);
                    }
                }
                for (int x = 0; x < writersArray.length; x++) {
                    int counter3 = 0;
                    for (int y = 0; y < writersList.size(); y++) {
                        if (writersArray[x].equals(writersList.get(y))) {
                            counter3++;
                        }
                    }
                    if (counter3 == 0) {
                        counter2++;
                    }
                }
                for (int x = 0; x < directorsArray.length; x++) {
                    int counter3 = 0;
                    for (int y = 0; y < directorsList.size(); y++) {
                        if (directorsArray[x].equals(directorsList.get(y))) {
                            counter3++;
                        }
                    }
                    if (counter3 == 0) {
                        counter2++;
                    }
                }
                for (int x = 0; x < castArray.length; x++) {
                    int counter3 = 0;
                    for (int y = 0; y < castList.size(); y++) {
                        if (castArray[x].equals(castList.get(y))) {
                            counter3++;
                        }
                    }
                    if (counter3 == 0) {
                        counter2++;
                    }
                }

                if (counter == 0 && counter2 == 0) {//added featurefilm
                    output.write("\nFeatureFilm added successfully\n");
                    featureFilms.add(new FeatureFilm(List1[2], List1[3], List1[4], List1[5], List1[6], List1[7], List1[8], List1[9], List1[10], List1[11], List1[12]));
                    basicFilms.add(new Film(List1[2], List1[3], List1[4], List1[5], List1[6], List1[7], List1[8]));
                    output.write("Film ID:\t" + List1[2]);
                    output.write("\nFilm title:\t" + List1[3] + "\n");

                } else
                    output.write("\nCommand Failed\n" + "Film ID:\t" + List1[2] + "\nFilm title:\t" + List1[3] + "\n");

                output.write(line);
            } else if (List1[0].equals("VIEWFILM")) {// "VIEWFILM" command
                int counter = 0;

                output.write(List1[0] + "\t" + List1[1] + "\n");

                for (int x = 0; x < featureFilms.size(); x++) {// viewfilm for an featurefilm
                    if (featureFilms.get(x).filmID.equals(List1[1])) {
                        counter++;
                        output.write("\n" + featureFilms.get(x).filmTitle + "(" + featureFilms.get(x).releaseDate.substring(featureFilms.get(x).releaseDate.length() - 4) + ")");
                        output.write(featureFilms.get(x).genre);
                        String[] splited1 = featureFilms.get(x).director.split(",");
                        String[] splited2 = featureFilms.get(x).cast.split(",");
                        String[] splited3 = featureFilms.get(x).writers.split(",");
                        String writersList = "";
                        String directorsList = "";
                        String castList = "";
                        for (int y = 0; y < splited1.length; y++) {// directors id to name
                            for (int z = 0; z < directors.size(); z++) {
                                if (directors.get(z).id.equals(splited1[y])) {
                                    directorsList += directors.get(z).name + " " + directors.get(z).surname;
                                    if (y != splited1.length - 1) {
                                        directorsList += ",";
                                    }
                                }
                            }
                        }
                        for (int y = 0; y < splited2.length; y++) {// actors id to name
                            for (int z = 0; z < actors.size(); z++) {
                                if (actors.get(z).id.equals(splited2[y])) {
                                    castList += actors.get(z).name + " " + actors.get(z).surname;
                                    if (y != splited2.length - 1) {
                                        castList += ",";
                                    }
                                }
                            }
                            for (int z = 0; z < childActors.size(); z++) {// childactors id to name
                                if (childActors.get(z).id.equals(splited2[y])) {
                                    castList += childActors.get(z).name + " " + childActors.get(z).surname;
                                    if (y != splited2.length - 1) {
                                        castList += ",";
                                    }
                                }
                            }

                        }
                        for (int y = 0; y < splited3.length; y++) {//writers id to name
                            for (int z = 0; z < writers.size(); z++) {
                                if (writers.get(z).id.equals(splited3[y])) {
                                    writersList += writers.get(z).name + " " + writers.get(z).surname;
                                    if (y != splited3.length - 1) {
                                        writersList += ",";
                                    }
                                }
                            }
                        }
                        output.write("\nWriters:\t" + writersList);
                        output.write("\nDirectors:\t" + directorsList);
                        output.write("\nStars:\t" + castList);
                        try {
                            if (filmRated.get(featureFilms.get(x).filmID).size() != 0) {
                                String rate = ratings.get(featureFilms.get(x).filmID).replace(".", ",");
                                output.write("\nRatings:\t" + rate + "/10\tfrom\t" + filmRated.get(featureFilms.get(x).filmID).size() + "\tusers\n");
                            } else output.write("Awaiting for votes\n");
                        }catch (NullPointerException e){
                            output.write("\nAwaiting for votes\n");
                        }
                    }
                }
                for (int x = 0; x < shortFilms.size(); x++) {
                    if (shortFilms.get(x).filmID.equals(List1[1])) {// viewfilm for an shortfilm
                        counter++;
                        output.write("\n" + shortFilms.get(x).filmTitle + "(" + shortFilms.get(x).releaseDate.substring(shortFilms.get(x).releaseDate.length() - 4) + ")");
                        output.write(shortFilms.get(x).genre);
                        String[] splited1 = shortFilms.get(x).director.split(",");
                        String[] splited2 = shortFilms.get(x).cast.split(",");
                        String[] splited3 = shortFilms.get(x).writers.split(",");
                        String writersList = "";
                        String directorsList = "";
                        String castList = "";
                        for (int y = 0; y < splited1.length; y++) {// directors id to name
                            for (int z = 0; z < directors.size(); z++) {
                                if (directors.get(z).id.equals(splited1[y])) {
                                    directorsList += directors.get(z).name + " " + directors.get(z).surname;
                                    if (y != splited1.length - 1) {
                                        directorsList += ",";
                                    }
                                }
                            }
                        }
                        for (int y = 0; y < splited2.length; y++) {// actors id to name
                            for (int z = 0; z < actors.size(); z++) {
                                if (actors.get(z).id.equals(splited2[y])) {
                                    castList += actors.get(z).name + " " + actors.get(z).surname;
                                    if (y != splited2.length - 1) {
                                        castList += ",";
                                    }
                                }
                            }
                            for (int z = 0; z < childActors.size(); z++) {// childactors id to name
                                if (childActors.get(z).id.equals(splited2[y])) {
                                    castList += childActors.get(z).name + " " + childActors.get(z).surname;
                                    if (y != splited2.length - 1) {
                                        castList += ",";
                                    }
                                }
                            }

                        }
                        for (int y = 0; y < splited3.length; y++) {//writers id to name
                            for (int z = 0; z < writers.size(); z++) {
                                if (writers.get(z).id.equals(splited3[y])) {
                                    writersList += writers.get(z).name + " " + writers.get(z).surname;
                                    if (y != splited3.length - 1) {
                                        writersList += ",";
                                    }
                                }
                            }
                        }
                        output.write("\nWriters:\t" + writersList);
                        output.write("\nDirectors:\t" + directorsList);
                        output.write("\nStars:\t" + castList);
                        if (filmRated.get(shortFilms.get(x).filmID).size() != 0) {
                            String rate = ratings.get(shortFilms.get(x).filmID);
                            rate.replace(".", ",");
                            output.write("\nRatings:\t" + rate + "/10\tfrom\t" + filmRated.get(shortFilms.get(x).filmID).size() + "\tusers\n");
                        } else output.write("Awaiting for votes\n");
                    }

                }
                for (int x = 0; x < documentaries.size(); x++) {
                    if (documentaries.get(x).filmID.equals(List1[1])) {// viewfilm for an documentary
                        counter++;
                        String[] splited1 = documentaries.get(x).director.split(",");
                        String[] splited2 = documentaries.get(x).cast.split(",");
                        output.write("\n" + documentaries.get(x).filmTitle + "(" + documentaries.get(x).releaseDate.substring(documentaries.get(x).releaseDate.length() - 4) + ")");
                        output.write(featureFilms.get(x).genre);
                        String directorsList = "";
                        String castList = "";
                        for (int y = 0; y < splited1.length; y++) {// directors id to name
                            for (int z = 0; z < directors.size(); z++) {
                                if (directors.get(z).id.equals(splited1[y])) {
                                    directorsList += directors.get(z).name + " " + directors.get(z).surname;
                                    if (y != splited1.length - 1) {
                                        directorsList += ",";
                                    }
                                }
                            }
                        }
                        for (int y = 0; y < splited2.length; y++) {
                            for (int z = 0; z < actors.size(); z++) {
                                if (actors.get(z).id.equals(splited2[y])) {// actors id to name
                                    castList += actors.get(z).name + " " + actors.get(z).surname;
                                    if (y != splited2.length - 1) {
                                        castList += ",";
                                    }
                                }
                            }
                            for (int z = 0; z < childActors.size(); z++) {// childactors id to name
                                if (childActors.get(z).id.equals(splited2[y])) {
                                    castList += childActors.get(z).name + " " + childActors.get(z).surname;
                                    if (y != splited2.length - 1) {
                                        castList += ",";
                                    }
                                }
                            }
                        }
                        output.write("\nDirectors:\t" + directorsList);
                        output.write("\nStars:\t" + castList);
                        if (filmRated.get(documentaries.get(x).filmID).size() != 0) {
                            String rate = ratings.get(documentaries.get(x).filmID).replace(".", ",");
                            output.write("\nRatings:\t" + rate + "/10\tfrom\t" + filmRated.get(documentaries.get(x).filmID).size() + "\tusers\n");
                        } else output.write("Awaiting for votes\n");
                    }
                }
                for (int x = 0; x < tvSeries.size(); x++) {
                    if (tvSeries.get(x).filmID.equals(List1[1])) {// viewfilm for an tvseries
                        counter++;
                        output.write("\n" + tvSeries.get(x).filmTitle + "(" + tvSeries.get(x).startDate.substring(featureFilms.get(x).releaseDate.length() - 4) + "-" + tvSeries.get(x).endDate.substring(tvSeries.get(x).endDate.length() - 4) + ")");
                        output.write("\n" + tvSeries.get(x).season + " seasons\tand\t" + tvSeries.get(x).episodes + "episodes\n");
                        output.write(tvSeries.get(x).genre);
                        String[] splited1 = tvSeries.get(x).director.split(",");
                        String[] splited2 = tvSeries.get(x).cast.split(",");
                        String[] splited3 = tvSeries.get(x).writers.split(",");
                        String writersList = "";
                        String directorsList = "";
                        String castList = "";
                        for (int y = 0; y < splited1.length; y++) {// directors id to name
                            for (int z = 0; z < directors.size(); z++) {
                                if (directors.get(z).id.equals(splited1[y])) {
                                    directorsList += directors.get(z).name + " " + directors.get(z).surname;
                                    if (y != splited1.length - 1) {
                                        directorsList += ",";
                                    }
                                }
                            }
                        }
                        for (int y = 0; y < splited2.length; y++) {// actors id to name
                            for (int z = 0; z < actors.size(); z++) {
                                if (actors.get(z).id.equals(splited2[y])) {
                                    castList += actors.get(z).name + " " + actors.get(z).surname;
                                    if (y != splited2.length - 1) {
                                        castList += ",";
                                    }
                                }
                            }
                            for (int z = 0; z < childActors.size(); z++) {// childactors id to name
                                if (childActors.get(z).id.equals(splited2[y])) {
                                    castList += childActors.get(z).name + " " + childActors.get(z).surname;
                                    if (y != splited2.length - 1) {
                                        castList += ",";
                                    }
                                }
                            }
                        }
                        for (int y = 0; y < splited3.length; y++) {//writers id to name
                            for (int z = 0; z < writers.size(); z++) {
                                if (writers.get(z).id.equals(splited3[y])) {
                                    writersList += writers.get(z).name + " " + writers.get(z).surname;
                                    if (y != splited3.length - 1) {
                                        writersList += ",";
                                    }
                                }
                            }
                        }
                        output.write("\nWriters:\t" + writersList);
                        output.write("\nDirectors:\t" + directorsList);
                        output.write("\nStars:\t" + castList);
                        if (filmRated.get(tvSeries.get(x).filmID).size() != 0) {
                            String rate = ratings.get(tvSeries.get(x).filmID).replace(".", ",");
                            ;
                            output.write("\nRatings:\t" + rate + "/10\tfrom\t" + filmRated.get(tvSeries.get(x).filmID).size() + "\tusers\n");
                        } else output.write("\nAwaiting for votes\n");
                    }
                }
                if (counter == 0) {
                    output.write("\nCommand Failed\nFilm ID:\t" + List1[1] + "\n");
                }

                output.write(line);
            } else if (List1[0].equals("LIST") && List1[1].equals("FILM") && List1[2].equals("SERIES")) {// "LIST TVSERİES" command
                output.write(commandFileLine + "\n");
                if (!tvSeries.isEmpty()) {
                    for (int k = 0; k < tvSeries.size(); k++) {//call all tv series
                        output.write("\n" + tvSeries.get(k).filmTitle + "(" + tvSeries.get(k).startDate.substring(featureFilms.get(k).releaseDate.length() - 4) + "-" + tvSeries.get(k).endDate.substring(tvSeries.get(k).endDate.length() - 4) + ")\n");
                        output.write(tvSeries.get(k).season + " seasons and\t" + tvSeries.get(k).episodes + "\tepisodes\n");
                    }
                } else output.write("No result");
                output.write(line);
            } else if (List1[0].equals("LIST") && List1[1].equals("FILMS") && List1[3].equals("COUNTRY")) {// "LIST FILMS BY COUNTRY" command
                output.write(commandFileLine + "\n");
                int counter = 0;

                for (int q = 0; q < basicFilms.size(); q++) {
                    if (basicFilms.get(q).country.equals(List1[4])) {//check all films' country
                        output.write("\nFilm Title:\t" + basicFilms.get(q).filmTitle + "\n");
                        output.write(basicFilms.get(q).runtime + "\tmin\n");
                        output.write("Language:\t" + basicFilms.get(q).language + "\n");
                        counter++;
                    }

                }
                if (counter == 0) {
                    output.write("\nNo result\n");
                }
                output.write(line);
            } else if (List1[0].equals("LIST") && List1[2].equals("BEFORE")) {// "LIST FEATUREFILMS BEFORE YEAR" command
                output.write(commandFileLine + "\n");
                if (List1[1].equals("FEATUREFILMS")) {
                    int counter = 0;
                    for (int j = 0; j < featureFilms.size(); j++) {//check featurefilms by year
                        int year = Integer.parseInt(featureFilms.get(j).releaseDate.substring(featureFilms.get(j).releaseDate.length() - 4));
                        if (year < Integer.parseInt(List1[3])) {
                            output.write("\nFilm Title:\t" + featureFilms.get(j).filmTitle + "(" + year + ")\n");
                            output.write(featureFilms.get(j).runtime + "\tmin\n");
                            output.write("Language:\t" + featureFilms.get(j).language + "\n");
                            counter++;
                        }
                    }
                    if (counter == 0) {
                        output.write("\nNo result\n");
                    }
                }
                output.write(line);
            } else if (List1[0].equals("LIST") && List1[1].equals("FEATUREFILMS") && List1[2].equals("AFTER")) {//"LIST FEATUREFILMS AFTER YEAR" command
                output.write(commandFileLine + "\n");
                int counter = 0;
                for (int j = 0; j < featureFilms.size(); j++) {//check featurefilms by year
                    int year = Integer.parseInt(featureFilms.get(j).releaseDate.substring(featureFilms.get(j).releaseDate.length() - 4));
                    if (year >= Integer.parseInt(List1[3])) {
                        output.write("\nFilm Title:\t" + featureFilms.get(j).filmTitle + "(" + year + ")\n");
                        output.write(featureFilms.get(j).runtime + "\tmin\n");
                        output.write("Language:\t" + featureFilms.get(j).language + "\n");
                        counter++;
                    }
                }
                if (counter == 0) {
                    output.write("\nNo Result\n");
                }
                output.write(line);
            } else if (List1[0].equals("LIST") && List1[1].equals("ARTISTS")) {//"LIST ARTISTS BY COUNTRY" command
                output.write(commandFileLine);
                int directorCounter = 0;
                output.write("\n\nDirectors:\n");
                for (int j = 0; j < directors.size(); j++) {//check diretors from that country
                    if (directors.get(j).country.equals(List1[3])) {
                        output.write(directors.get(j).name + "\t" + directors.get(j).surname + "\t" + directors.get(j).agent + "\n");
                        directorCounter++;
                    }
                }
                if (directorCounter == 0) {
                    output.write("No result\n");
                }
                output.write("\nWriters:\n");
                int writerCounter = 0;
                for (int j = 0; j < writers.size(); j++) {//check writers from that country
                    if (writers.get(j).country.equals(List1[3])) {
                        output.write(writers.get(j).name + "\t" + writers.get(j).surname + "\t" + writers.get(j).writingStyle + "\n");
                        writerCounter++;
                    }
                }
                if (writerCounter == 0) {
                    output.write("No result\n");
                }
                output.write("\nActors:\n");
                int actorCounter = 0;
                for (int j = 0; j < actors.size(); j++) {//check actors from that country
                    if (actors.get(j).country.equals(List1[3])) {
                        output.write(actors.get(j).name + "\t" + actors.get(j).surname + "\t" + actors.get(j).height + "\tcm\n");
                        actorCounter++;
                    }
                }
                if (actorCounter == 0) {
                    output.write("No result\n");
                }
                output.write("\nChildActors:\n");
                int childActorCounter = 0;
                for (int j = 0; j < childActors.size(); j++) {//check childActors from that country
                    if (childActors.get(j).country.equals(List1[3])) {
                        output.write(childActors.get(j).name + "\t" + childActors.get(j).surname + "\t" + childActors.get(j).age + "\n");
                        childActorCounter++;
                    }
                }
                if (childActorCounter == 0) {
                    output.write("No result\n");
                }
                output.write("\nStuntPerformers:\n");
                int stuntPerformersCounter = 0;
                for (StuntPerformer stuntPerformer : stuntPerformers) {//check stuntPerformers from that country
                    if (stuntPerformer.country.equals(List1[3])) {
                        output.write(stuntPerformer.name + "\t" + stuntPerformer.surname + "\t" + stuntPerformer.height + "\tcm\n");
                        stuntPerformersCounter++;
                    }
                }
                if (stuntPerformersCounter == 0) {
                    output.write("No result\n");
                }
                output.write(line);
            }


            commandFileLine = bufferedReader3.readLine();
        }
        /*for (int q=0;q<basicFilms.size();q++){
            System.out.println(basicFilms.get(q).filmID);
        }*/

        commandFile.close();
        output.close();

    }
}
