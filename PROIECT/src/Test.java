import java.util.*;

public class Test {
    public Test(){

    }
    public void testGame() throws InvalidCommandException {
        System.out.println("Alege modul de joc! Pentru terminal tasteaza terminal, iar pentru interfata grafica" +
                " tasteaza interfata");

        Scanner scanner = new Scanner(System.in);
        String modeGame = scanner.next();
        if (modeGame.equals("interfata"))
            System.out.println("Nu am implementat interfata :(");
        else if (modeGame.equals("terminal")) {
            System.out.println("Incepe jocul in terminal");
            Game game = new Game();
            Grid table = Grid.generateMap();
            try {
                Character fighter = game.run(table, game);
                System.out.println(fighter);
                game.optionsGame(table, fighter, game);
            } catch(NullPointerException e) {
                System.out.println("Nu s-au introdus corect datele contului");
            }

        }
        else throw new InvalidCommandException();
    }
    public static void main(String[] args){
        Test test = new Test();
        try {
            test.testGame();
        }catch(InvalidCommandException e){

        }
    }
}
