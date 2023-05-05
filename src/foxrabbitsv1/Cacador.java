package foxrabbitsv1;
import java.util.List;
public abstract class Cacador extends Animal {
    public Cacador(boolean z, Localizacao x, Campo y) {
        super(z,x, y);
    }

    @Override
    public void act(Campo currentField, Campo updatedField, List<Animal> newAnimals) {
        Localizacao newLocation = findFood(currentField, getLocation());
        if (newLocation == null) {
            newLocation = updatedField.freeAdjacentLocation(getLocation());
        }
        if (newLocation != null) {
            setLocation(newLocation);
            updatedField.place(this, newLocation);
        } else {
            setDead();
        }
    }

    private Localizacao findFood(Campo field, Localizacao location) {
        List<Localizacao> adjacentLocations = field.adjacentLocations(location);
        for (Localizacao adjacent : adjacentLocations) {
            Object object = field.getObjectAt(adjacent);
            if (object instanceof Coelho || object instanceof Lobo) {
                Animal animal = (Animal) object;
                if (animal.isAlive()) {
                    animal.setDead();
                    return adjacent;
                }
            }
        }
        return null;
    }
}

