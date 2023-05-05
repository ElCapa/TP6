package foxrabbitsv1;

import java.util.List;
import java.util.Random;

public class Lobo extends Animal {
    private static final double BREEDING_PROBABILITY = 0.08;
    private static final int MAX_LITTER_SIZE = 4;
    private static final int FOX_FOOD_VALUE = 7;
    public Lobo(boolean randomAge, Campo field, Localizacao location) {
        super(randomAge, location, field);
    }

    public void act(List<Animal> newLobo) {
        incrementAge();
        if (isAlive()) {
            giveBirth(newLobo);

            Localizacao newLocation = findFood();
            if (newLocation == null) {

                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            if (newLocation != null) {
                setLocation(newLocation);
            } else {

                setDead();
            }
        }
    }

    @Override
    public void act(Campo currentField, Campo updatedField, List<Animal> newAnimals) {
        
    }

    protected double getBreedingProbability() {
        return BREEDING_PROBABILITY;
    }

    protected int getMaxLitterSize() {
        return MAX_LITTER_SIZE;
    }

    protected int getFoodValue() {
        return FOX_FOOD_VALUE;
    }

    private Localizacao findFood() {
        List<Localizacao> adjacentLocations = getField().adjacentLocations(getLocation());
        for (Localizacao location : adjacentLocations) {
            Object object = getField().getObjectAt(location);
            if (object instanceof Coelho) {
                Coelho rabbit = (Coelho) object;
                if (rabbit.isAlive()) {
                    rabbit.setDead();
                    setFoodLevel(getFoodValue());
                    return location;
                }
            }
        }
        return null;
    }



    protected boolean isPredator() {
        return true;
    }


}

