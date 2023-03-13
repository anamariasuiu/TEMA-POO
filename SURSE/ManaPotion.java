public class ManaPotion implements Potion{
    private int price, valueReg, weight;
    public ManaPotion(int price, int valueReg, int weight) {
        this.price = price;
        this.valueReg = valueReg;
        this.weight = weight;
    }
    public void usingPotion(Character fighter){
        fighter.regenerateMana(getValueRegenerate());
    }
    public int getPrice(){

        return price;
    }
    public int getValueRegenerate(){

        return valueReg;
    }
    public int getWeightOfPotion(){
        return weight;
    }
}

