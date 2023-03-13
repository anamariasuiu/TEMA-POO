import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Account{
    Information info;
    Collection<String> characters;
    int numberOfGames;
    public Account(String email, String parola, String name, String country, int numberOfGames,
                   ArrayList<String> characters, ArrayList<String> favouriteGames){
        info = new Information(email, parola, name, country, favouriteGames);
        this.characters = characters;
        this.numberOfGames = numberOfGames;
    }

    @Override
    public String toString() {
        return "Account{" + info +
                ", characters=" + characters +
                ", numberOfGames=" + numberOfGames +
                '}';
    }

    class Information {
        Credentials credentials;
        ArrayList<String> favouriteGames;
        String name;
        String country;

        public Information(String email, String parola, String name, String country, ArrayList<String> favouriteGames) {
            credentials = new Credentials(email, parola);
            this.name = name;
            this.country = country;
            this.favouriteGames = favouriteGames;
            Collections.sort(this.favouriteGames);
        }

        @Override
        public String toString() {
            return  credentials +
                   ", favouriteGames=" + favouriteGames +
                    ", name='" + name + '\'' +
                    ", country='" + country + '\'';
        }
    }
}

