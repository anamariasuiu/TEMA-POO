public class HealthPotion implements Potion{
    private int price, valueReg, weight;
    public HealthPotion(int price, int valueReg, int weight) {
        this.price = price;
        this.valueReg = valueReg;
        this.weight = weight;
    }
    public void usingPotion(Character fighter){
        fighter.regenerateLife(getValueRegenerate());
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
