import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Game{
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);
    List<Account> accounts = new ArrayList<Account>();
    Map<Cell.TypeCell, List<String>> stories;
    public Game() {

    }
    public Character run(Grid table, Game game){
        File input1 = new File("accounts.json");
        File input2 = new File("stories.json");
        try {
            JsonElement storiesElement = JsonParser.parseReader(new FileReader((input2)));
            JsonObject fileObject = storiesElement.getAsJsonObject();
            JsonArray jsonArrayOfStories = fileObject.get("stories").getAsJsonArray();
            HashMap<Cell.TypeCell,List<String>> storiesMap = new HashMap<>();
            List<String> shopStories = new ArrayList<>();
            List<String> emptyStories = new ArrayList<>();
            List<String> enemyStories = new ArrayList<>();
            List<String> finishStories = new ArrayList<>();
            for(JsonElement stories : jsonArrayOfStories) {
                JsonObject storiesObject = stories.getAsJsonObject();
                String type = storiesObject.get("type").getAsString();
                String story = storiesObject.get("value").getAsString();
                if(type.equals("EMPTY"))
                    emptyStories.add(story);
                else if(type.equals("ENEMY"))
                    enemyStories.add(story);
                else if(type.equals("SHOP"))
                    shopStories.add(story);
                else if(type.equals("FINISH"))
                    finishStories.add(story);
            }
            storiesMap.put(Cell.TypeCell.EMPTY, emptyStories);
            storiesMap.put(Cell.TypeCell.ENEMY, enemyStories);
            storiesMap.put(Cell.TypeCell.SHOP, shopStories);
            storiesMap.put(Cell.TypeCell.FINISH, finishStories);

            JsonElement fileElement = JsonParser.parseReader(new FileReader(input1));
            JsonObject fileObject2 = fileElement.getAsJsonObject();
            JsonArray jsonArrayOfAccounts = fileObject2.get("accounts").getAsJsonArray();
            HashMap<Integer,List<String>> accounts = new HashMap<>();
            HashMap<Integer, ArrayList<String>> fav_games = new HashMap<>();
            int nr = 0;
            HashMap<Integer,ArrayList<ArrayList<String>>> characters = new HashMap<>();
            List<String> accountsList = new ArrayList<>();
            for(JsonElement account : jsonArrayOfAccounts){
                JsonObject accountObject = account.getAsJsonObject();
                JsonObject cred = accountObject.get("credentials").getAsJsonObject();
                String email = cred.get("email").getAsString();
                String password = cred.get("password").getAsString();
                String name = accountObject.get("name").getAsString();
                accountsList.add(name);
                String country = accountObject.get("country").getAsString();
                String numberOfGames = accountObject.get("maps_completed").getAsString();
                List<String> infoCharacter = new ArrayList<>();
                infoCharacter.add(email);
                infoCharacter.add(password);
                infoCharacter.add(name);
                infoCharacter.add(country);
                infoCharacter.add(numberOfGames);
                accounts.put(nr, infoCharacter);
                JsonArray jsonArrayOfGames = accountObject.get("favorite_games").getAsJsonArray();
                ArrayList<String> favourite_games = new ArrayList<>();
                for(JsonElement games : jsonArrayOfGames) {
                    String gameName = games.getAsString();
                    favourite_games.add(gameName);
                }
                fav_games.put(nr, favourite_games);
                JsonArray jsonArrayOfCharacters = accountObject.get("characters").getAsJsonArray();
                ArrayList<ArrayList<String>> listsOfCharacters = new ArrayList<ArrayList<String>>();
                for(JsonElement charactersElement : jsonArrayOfCharacters){
                    JsonObject charactersObject = charactersElement.getAsJsonObject();
                    String nameOfCharacter = charactersObject.get("name").getAsString();
                    String profession = charactersObject.get("profession").getAsString();
                    String level = charactersObject.get("level").getAsString();
                    String experience = charactersObject.get("experience").getAsString();
                    ArrayList<String> list = new ArrayList<>();
                    list.add(nameOfCharacter);
                    list.add(profession);
                    list.add(level);
                    list.add(experience);
                    listsOfCharacters.add(list);
                    characters.put(nr, listsOfCharacters);
                }
                nr++;
            }
            System.out.println("Alege un cont din cele " + nr + ", tastand un nr de la 1 la 8!");
            System.out.println(accountsList);
            int nrAccount = scanner.nextInt();
            if (nrAccount < 1 || nrAccount > 8)
                throw new InvalidCommandException();

            ArrayList<String> character = new ArrayList<String>();
            character.add(characters.get(nrAccount - 1).get(0).get(0));
            character.add(characters.get(nrAccount - 1).get(1).get(0));
            character.add(characters.get(nrAccount - 1).get(2).get(0));

            System.out.println("Alege un personaj din cele 3, tastand un nr de la 1 la 3 !");
            System.out.println(character);
            int nrCharacter = scanner.nextInt();
            if(nrCharacter < 1 || nrCharacter > 3)
                throw new InvalidCommandException();

            Account acc = new Account(accounts.get(nrAccount - 1).get(0), accounts.get(nrAccount - 1).get(1),
                    accounts.get(nrAccount - 1).get(2), accounts.get(nrAccount - 1).get(3),
                    Integer.parseInt(accounts.get(nrAccount - 1).get(4)), character, fav_games.get(nrAccount - 1));
            System.out.println("Contul tau este:");
            System.out.println(acc);
            game.accounts.add(acc);
            stories = storiesMap;
            Character fighter = new Character() {
                @Override
                void receiveDamage(int power) {
                    CurrentHealth = CurrentHealth - power;
                }

                @Override
                int getDamage() {
                    return 50;
                }
            };
            if(nrCharacter == 1)
                fighter = new Warrior(400,400,characters.get(nrAccount - 1).get(0).get(0),
                        table.CurrentCell.Ox, table.CurrentCell.Oy,
                        Integer.parseInt(characters.get(nrAccount - 1).get(0).get(2)),
                        Integer.parseInt(characters.get(nrAccount - 1).get(0).get(3)));
            else if(nrCharacter == 2)
                fighter = new Mage(400,400,characters.get(nrAccount - 1).get(1).get(0),
                        table.CurrentCell.Ox, table.CurrentCell.Oy,
                        Integer.parseInt(characters.get(nrAccount - 1).get(1).get(2)),
                        Integer.parseInt(characters.get(nrAccount - 1).get(1).get(3)));
            else if(nrCharacter == 3)
                fighter = new Rogue(400,400,characters.get(nrAccount - 1).get(2).get(0),
                        table.CurrentCell.Ox, table.CurrentCell.Oy,
                        Integer.parseInt(characters.get(nrAccount - 1).get(2).get(2)),
                        Integer.parseInt(characters.get(nrAccount - 1).get(2).get(3)));
            System.out.println("Ai ales personajul:");
            return fighter;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(InvalidCommandException ie){

        }
        return null;
    }
    public void optionsGame(Grid table, Character fighter, Game game){
        try {
            while (fighter.CurrentHealth > 0) {
                table.showMap();
                if (table.map[table.CurrentCell.Ox][table.CurrentCell.Oy].type == Cell.TypeCell.SHOP &&
                        table.map[table.CurrentCell.Ox][table.CurrentCell.Oy].StateOfCell == false) {
                    Shop shop = new Shop();
                    System.out.println("Alege din lista de " + shop.potions.size() + " potiuni : ");
                    shop.getNamesOfPotions();
                    int nrPotion1 = scanner.nextInt();
                    if(nrPotion1 < 1 || nrPotion1 > shop.potions.size())
                        throw new InvalidCommandException();
                    else if (fighter.buyPotion(shop.potions.get(nrPotion1 - 1)) == true) {
                        fighter.inventory.addPotion(shop.getPotion(nrPotion1 - 1));
                        System.out.println("Ai cumparat o potiune!");
                    }
                    System.out.println("Alege din lista de " + shop.potions.size() + " potiuni : ");
                    shop.getNamesOfPotions();
                    int nrPotion2 = scanner.nextInt();
                    if(nrPotion2 < 1 || nrPotion2 > shop.potions.size())
                        throw new InvalidCommandException();
                    else if (fighter.buyPotion(shop.potions.get(nrPotion2 - 1)) == true) {
                            fighter.inventory.addPotion(shop.getPotion(nrPotion2 - 1));
                        System.out.println("Ai cumparat o potiune!");
                    }
                    System.out.println("\n" + "Lista ta de potiuni este : ");
                    fighter.inventory.getNamesOfPotions();
                    System.out.println("\nNumarul de monede ramas este : " + fighter.inventory.NumberOfCoins);
                } else if (table.map[table.CurrentCell.Ox][table.CurrentCell.Oy].type == Cell.TypeCell.ENEMY &&
                        table.map[table.CurrentCell.Ox][table.CurrentCell.Oy].StateOfCell == false) {
                    Enemy enemy = new Enemy();
                    int nrTurn = 0;
                    System.out.println("Viata ta este: " + fighter.CurrentHealth);
                    System.out.println("Mana ta este: " + fighter.CurrentMana);
                    System.out.println("Viata inamicului este : " + enemy.CurrentHealth);
                    System.out.println("Mana inamicului este : " + enemy.CurrentMana);
                    while (fighter.CurrentHealth > 0 && enemy.CurrentHealth > 0) {
                        if (nrTurn % 2 == 0) {
                            System.out.println("Este randul tau!");
                            System.out.println("Alege urmatoarea miscare! Ataca inamicul(1), foloseste abilitate(2), " +
                                    "foloseste potiune(3)");
                            int move = scanner.nextInt();
                            if(move < 1 || move > 3)
                                throw new InvalidCommandException();
                            if (move == 1) {
                                enemy.receiveDamage(fighter.getDamage());
                            } else if (move == 2) {
                                if (fighter.abilities.size() > 0) {
                                    System.out.println("Alege una din cele " + fighter.abilities.size() + " abilitati");
                                    fighter.getNamesOfAbilities();
                                    int nrAbility = scanner.nextInt();
                                    if(nrAbility < 1 || nrAbility > fighter.abilities.size())
                                        throw new InvalidCommandException();
                                    Spell spell = fighter.abilities.get(nrAbility - 1);
                                    if (fighter.useAbility(spell, enemy, fighter) == true)
                                        fighter.abilities.remove(nrAbility - 1);
                                    else {
                                        System.out.println("Inamicul s-a aparat cu protectia de "
                                                + spell.getClass().getName());
                                    }
                                } else
                                    System.out.println("Nu mai ai abilitati!");

                            } else if (move == 3) {
                                if (fighter.inventory.potions.size() > 0) {
                                    System.out.println("Alege o potiune!");
                                    fighter.inventory.getNamesOfPotions();
                                    int nrPotion = scanner.nextInt();
                                    if(nrPotion < 1 || nrPotion > fighter.inventory.potions.size())
                                        throw new InvalidCommandException();
                                    else {
                                        fighter.inventory.potions.get(nrPotion - 1).usingPotion(fighter);
                                        fighter.inventory.removePotion(fighter.inventory.potions.get(nrPotion - 1));
                                    }
                                } else System.out.println("Nu exista potiuni in lista!");
                            }
                        } else if (nrTurn % 2 == 1) {
                            System.out.println("Este randul inamicului!");
                            int moveEnemy = random.nextInt(0, 2);
                            if (moveEnemy == 0) {
                                System.out.println("Inamicul ataca!");
                                fighter.receiveDamage(enemy.getDamage());
                            } else if ((moveEnemy == 1) || (moveEnemy == 2)) {
                                System.out.println("Inamicul foloseste abilitate!");
                                if (enemy.abilities.size() > 0) {
                                    enemy.getNamesOfAbilities();
                                    int nrAbility = random.nextInt(1, 3);
                                    Spell spell = enemy.abilities.get(nrAbility - 1);
                                    if (enemy.useAbility(spell, fighter, enemy) == true) {
                                        System.out.println("Te-a atacat cu " + spell.getClass().getName());
                                        enemy.abilities.remove(nrAbility - 1);
                                    } else System.out.println("Ai scapat! Te-ai aparat cu protectia de "
                                            + spell.getClass().getName());
                                }
                            }
                        }
                        nrTurn++;
                        System.out.println("Viata ta este: " + fighter.CurrentHealth);
                        System.out.println("Mana ta este: " + fighter.CurrentMana);
                        System.out.println("Viata inamicului este : " + enemy.CurrentHealth);
                        System.out.println("Mana inamicului este : " + enemy.CurrentMana);
                    }
                    if (enemy.CurrentHealth <= 0) {
                        int chanceOfGettingCoins = random.nextInt(1, 5);
                        if (chanceOfGettingCoins < 5) {
                            fighter.inventory.NumberOfCoins += 50;
                        }
                        fighter.CurrentExperience += 20;
                    }
                    if(fighter.CurrentHealth <= 0) {
                        fighter.CurrentExperience += 5;
                    }
                    if(fighter.CurrentExperience > 50){
                        fighter.CurrentLevel++;
                        if(fighter instanceof Warrior) {
                            fighter.strength += 10;
                            fighter.charisma += 5;
                            fighter.dexterity += 5;
                        }
                        else if(fighter instanceof Rogue){
                            fighter.strength += 5;
                            fighter.charisma += 5;
                            fighter.dexterity += 10;
                        }
                        else if(fighter instanceof Mage){
                            fighter.strength += 5;
                            fighter.charisma += 10;
                            fighter.dexterity += 5;
                        }
                        fighter.CurrentExperience = 0;
                    }
                } else if (table.map[table.CurrentCell.Ox][table.CurrentCell.Oy].type == Cell.TypeCell.EMPTY) {
                    if (table.map[table.CurrentCell.Ox][table.CurrentCell.Oy].StateOfCell == false) {
                        int chanceCoins = random.nextInt(1, 5);
                        if (chanceCoins == 5) {
                            fighter.inventory.NumberOfCoins += 50;
                        }
                    }
                } else if (table.map[table.CurrentCell.Ox][table.CurrentCell.Oy].type == Cell.TypeCell.FINISH) {
                    System.out.println("Felicitari! Ai ajuns la finalul jocului!");
                    break;
                }
                table.map[table.CurrentCell.Ox][table.CurrentCell.Oy].StateOfCell = true;
                if (fighter.CurrentHealth <= 0) {
                    System.out.println("Jocul s-a incheiat! Ai pierdut! :(");
                } else {
                    System.out.println("Viata ta este: " + fighter.CurrentHealth);
                    System.out.println("Mana ta este: " + fighter.CurrentMana);
                    System.out.println("Nivelul tau este: " + fighter.CurrentLevel);
                    System.out.println("Experienta ta este: " + fighter.CurrentExperience);
                    System.out.println("Numarul de monede ramas este : " + fighter.inventory.NumberOfCoins);
                    System.out.println("Continui jocul? Daca da, apasa tasta P");
                    String newInput = scanner.next();
                    if(!newInput.equals("P"))
                        throw new InvalidCommandException();
                    else if (newInput.equals("P")) {
                        System.out.println("In ce directie? Pentru sud tasteaza S, pentru nord tasteaza N, pentru" +
                                "est tasteaza E, pentru vest tasteaza V");
                        String direction = scanner.next();
                        if (direction.equals("S")) {
                            table.goSouth();
                        } else if (direction.equals("N")) {
                            table.goNorth();
                        } else if (direction.equals("V")) {
                            table.goWest();
                        } else if (direction.equals("E")) {
                            table.goEast();
                        } else throw new InvalidCommandException();
                        System.out.println(game.showStory(table));
                    }
                }
            }
        }catch(InvalidCommandException e){

        }
    }
    public String showStory(Grid table){
        Random random = new Random();
        int nrStory;
        String story = new String();
        if(table.map[table.CurrentCell.Ox][table.CurrentCell.Oy].StateOfCell == false){
            if(table.map[table.CurrentCell.Ox][table.CurrentCell.Oy].type.equals(Cell.TypeCell.EMPTY)) {
                nrStory = random.nextInt(stories.get(Cell.TypeCell.EMPTY).size() - 1);
                story  = stories.get(Cell.TypeCell.EMPTY).get(nrStory);
            }
            else if(table.map[table.CurrentCell.Ox][table.CurrentCell.Oy].type.equals(Cell.TypeCell.ENEMY)) {
                nrStory = random.nextInt(stories.get(Cell.TypeCell.ENEMY).size() - 1);
                story  = stories.get(Cell.TypeCell.ENEMY).get(nrStory);
            }
            else if(table.map[table.CurrentCell.Ox][table.CurrentCell.Oy].type.equals(Cell.TypeCell.SHOP)) {
                nrStory = random.nextInt(stories.get(Cell.TypeCell.SHOP).size() - 1);
                story  = stories.get(Cell.TypeCell.SHOP).get(nrStory);
            }
            else if(table.map[table.CurrentCell.Ox][table.CurrentCell.Oy].type.equals(Cell.TypeCell.FINISH)) {
                nrStory = random.nextInt(stories.get(Cell.TypeCell.FINISH).size() - 1);
                story  = stories.get(Cell.TypeCell.FINISH).get(nrStory);
            }
        }
        return story;

    }
}
