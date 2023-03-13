import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Entity implements CellElement{
    Random rand = new Random();
    int damage = 40;

    public Enemy(){
        CurrentHealth = rand.nextInt(350,420);
        CurrentMana = rand.nextInt(350,420);
        fire = rand.nextBoolean();
        ice = rand.nextBoolean();
        earth = rand.nextBoolean();
        abilities = new ArrayList<Spell>();
        int nrAbilities = rand.nextInt(2,4);
        for(int i = 0;  i < nrAbilities; i++) {
            Spell spell;
            int abilityKind = rand.nextInt(0,2);
            if(abilityKind == 0)
                spell = new Ice();
            else if(abilityKind == 1)
                spell = new Fire();
            else spell = new Earth();
            abilities.add(spell);
        }
    }
    public void receiveDamage(int power){
        int nr = rand.nextInt(1);
        if(nr == 0)
            CurrentHealth = CurrentHealth - power;
        else System.out.println("Din pacate, inamicul a evitat atacul!");
    }
    public int getDamage(){
        int nr = rand.nextInt(1);
        if(nr == 0)
            return damage;
        else return 2 * damage;
    }
    @Override
    public void toCharacter() {
        System.out.println("E");
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "abilities=" + abilities +
                ", rand=" + rand +
                ", damage=" + damage +
                ", abilities=" + abilities +
                ", CurrentHealth=" + CurrentHealth +
                ", CurrentMana=" + CurrentMana +
                ", fire=" + fire +
                ", ice=" + ice +
                ", earth=" + earth +
                '}';
    }
}
