package foxrabbitsv1;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

public  abstract class Animal {
    private int age;
    private boolean alive;
    private Localizacao location;
    private Campo field;
    private static final int MAX_AGE = 20;
    private static final int MAX_LITTER_SIZE = 5;
    private static final Random rand = new Random();

    public Animal(boolean randomAge, Localizacao location, Campo field) {
        alive = true;
        this.field = field;
        setLocation(location);
        if (randomAge) {
            age = (int) (Math.random() * MAX_AGE);
        } else {
            age = 0;
        }
    }

    protected boolean isAlive() {
        return alive;
    }

    protected Localizacao getLocation() {
        return location;
    }

    protected Campo getField() {
        return field;
    }

    protected void setLocation(Localizacao newLocation) {
        if (location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    protected void setDead() {
        alive = false;
        if (location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    protected void setFoodLevel(int foodLevel) {
        // não faz nada para animais que não comem plantas
    }

    public abstract void act(List<Animal> newAnimals);


    protected void incrementAge() {
        age++;
        if (age > MAX_AGE) {
            setDead();
        }
    }
    protected void giveBirth(List<Animal> newLobos) {
        int births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        for (int b = 0; b < births; b++) {
            Localizacao loc = getField().freeAdjacentLocation(getLocation());
            if (loc != null) {
                Lobo young = new Lobo(false, getField(), loc);
                newLobos.add(young);
            }
        }
    }

    public abstract void act(Campo currentField, Campo updatedField, List<Animal> newAnimals);
}