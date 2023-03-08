import java.util.List;

abstract class Entity {
    List<Spell> abilities;
    int CurrentHealth;
    static final int MaxHealth = 300;
    int CurrentMana;
    static final int MaxMana = 300;
    boolean fire, ice, earth;
    public void regenerateLife(int health) {
        if((CurrentHealth + health) < MaxHealth)
            CurrentHealth += health;
        else CurrentHealth = MaxHealth;
    }
    public void regenerateMana(int mana) {
        if((CurrentMana + mana) < MaxMana)
            CurrentMana += mana;
        else CurrentMana = MaxMana;
    }
    public boolean useAbility(Spell spell, Entity currentEnemy, Entity currentFighter){
        if(currentFighter.CurrentMana > spell.costMana) {
            if((spell instanceof Fire && currentEnemy.fire == false) || (spell instanceof Ice &&
                    currentEnemy.ice == false) || (spell instanceof Earth && currentEnemy.earth == false) ){
                currentEnemy.receiveDamage(spell.getDamage());
                spell.setMana(currentFighter);
                return true;
            }
        }
        return false;
    }
    public void getNamesOfAbilities() {
        for(int i = 0; i < abilities.size(); i++)
            System.out.print(abilities.get(i).getClass().getName() + "(" + (i+1) + "), ");
        System.out.print("\n");
    }
    abstract void receiveDamage(int power);
    abstract int getDamage();
}
