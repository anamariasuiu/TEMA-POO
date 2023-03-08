import java.util.ArrayList;
import java.util.Random;

public class Rogue extends Character{
    Random rand = new Random();
    public Rogue(int CurrentHealth,int CurrentMana,String name, int Ox, int Oy, int CurrentExperience,int CurrentLevel){
        this.CurrentHealth = CurrentHealth;
        this.CurrentMana = CurrentMana;
        this.name = name;
        this.Ox = Ox;
        this.Oy = Oy;
        this.CurrentExperience = CurrentExperience;
        this.CurrentLevel = CurrentLevel;
        inventory = new Inventory(100000/2);
        fire = false;
        ice = false;
        earth =  true;
        dexterity = 5 * CurrentLevel + 10;
        charisma = 5 * CurrentLevel;
        strength = 5 * CurrentLevel;
        abilities = new ArrayList<Spell>();
        int nrAbilities = rand.nextInt(2,4);
        for(int i = 0;  i < nrAbilities; i++) {
            Spell spell;
            int abilityKind = rand.nextInt(2);
            if (abilityKind == 0)
                spell = new Ice();
            else if (abilityKind == 1)
                spell = new Fire();
            else spell = new Earth();
            abilities.add(spell);
        }
    }
    @Override
    void receiveDamage(int power) {
        if(charisma > 35 && strength > 35){
            int nr = rand.nextInt(1,5);
            if(nr < 4)
                CurrentHealth = CurrentHealth - power/2;
            else CurrentHealth = CurrentHealth - power;
        }
        else  CurrentHealth = CurrentHealth - power;
    }

    @Override
    int getDamage() {
        if(dexterity < 50)
            return 40;
        else{
            int nr = rand.nextInt(1,5);
            if(nr < 4)
                return 80;
            else return 40;
        }
    }

    @Override
    public String toString() {
        return "Rogue{" +
                "name='" + name + '\'' +
                ", CurrentExperience=" + CurrentExperience +
                ", CurrentLevel=" + CurrentLevel +
                '}';
    }
}
