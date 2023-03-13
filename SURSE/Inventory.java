import java.util.ArrayList;
import java.util.List;

public class Inventory {
    List<Potion> potions;
    int MaxWeight;
    int NumberOfCoins = 500;
    public Inventory(int MaxWeight) {
        this.MaxWeight = MaxWeight;
        potions = new ArrayList<Potion>();
    }
    public void addPotion(Potion potion) {
        potions.add(potion);
    }
    public void removePotion(Potion potion) {
        potions.remove(potion);
    }
    public int calculateWeight(Potion p){
        int WeightOfPotions = 0;
        for(int i = 0; i < potions.size(); i++)
            WeightOfPotions += potions.get(i).getWeightOfPotion();
        return MaxWeight - NumberOfCoins - WeightOfPotions - p.getWeightOfPotion();
    }

    public void getNamesOfPotions() {
        for(int i = 0; i < potions.size(); i++)
            System.out.print(potions.get(i).getClass().getName() + " (" + (i+1) + ") ");
        System.out.print("\n");
    }
}
