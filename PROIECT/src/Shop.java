import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shop implements CellElement{
    Random rand = new Random();
    ArrayList<Potion> potions;
    public Shop(){
        potions = new ArrayList<Potion>();
        int nrPotions = rand.nextInt(2,4);
        System.out.println(nrPotions);
        HealthPotion potion1 = new HealthPotion(30,30,30);
        ManaPotion potion2  = new ManaPotion(40,20,30);
        potions.add(potion1);
        potions.add(potion2);
        for(int i = 2;  i < nrPotions; i++) {
            Potion potion;
            int potionKind = rand.nextInt(1);
            if(potionKind == 0) {
                potion = new HealthPotion(25, 20, 30);
                potions.add(potion);
            }
            else if(potionKind == 1) {
                potion = new ManaPotion(10, 15, 25);
                potions.add(potion);
            }
        }
    }
    @Override
    public void toCharacter() {
        System.out.println("S");
    }
    public Potion getPotion(int index) {
        Potion p = null;
        if(potions.get(index) != null) {
            p = potions.get(index);
            potions.remove(index);
        }
        return p;
    }

    public void getNamesOfPotions() {
        for(int i = 0; i < potions.size(); i++)
        System.out.print(potions.get(i).getClass().getName() + "(" + (i+1) + "), ");
        System.out.print("\n");
    }

}
