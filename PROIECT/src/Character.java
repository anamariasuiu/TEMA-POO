abstract class Character extends Entity{
    String name;
    int Ox, Oy;
    Inventory inventory;
    int CurrentExperience, CurrentLevel;
    int strength, charisma, dexterity;
    public boolean buyPotion(Potion potion) {
       if(inventory.calculateWeight(potion) > 0 && inventory.NumberOfCoins >= potion.getPrice()) {
           inventory.NumberOfCoins = inventory.NumberOfCoins - potion.getPrice();
           return true;
       }
       return false;
    }

}
