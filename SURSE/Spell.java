abstract class Spell {
    int damage, costMana;
    public int getDamage(){
        return damage;
    }

    public int getCostMana() {
        return costMana;
    }
    public void setMana(Entity entity) {
        entity.CurrentMana -= getCostMana();
    }

}
