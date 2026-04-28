import java.util.List;
import java.util.Iterator;
import java.util.Random;

public class Wolf extends Animal
{
    private static final int BREEDING_AGE = 20;
    private static final int MAX_AGE = 200;
    private static final double BREEDING_PROBABILITY = 0.05;
    private static final int MAX_LITTER_SIZE = 3;

    private static final int FOOD_VALUE = 25;
    private static final Random rand = Randomizer.getRandom();

    private int foodLevel;

    public Wolf(boolean randomAge, Field field, Location location)
    {
        super(field, location);

        if(randomAge) {
            setAge(rand.nextInt(MAX_AGE));
            foodLevel = rand.nextInt(FOOD_VALUE);
        }
        else {
            setAge(0);
            foodLevel = FOOD_VALUE;
        }
    }

    public void act(List<Animal> newWolves)
    {
        incrementAge();
        incrementHunger();

        if(isAlive()) {
            giveBirth(newWolves);

            Location newLocation = findFood();

            if(newLocation == null) {
                newLocation = getField().freeAdjacentLocation(getLocation());
            }

            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                setDead();
            }
        }
    }

    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }

    private Location findFood()
    {
        List<Location> adjacent = getField().adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();

        while(it.hasNext()) {
            Location where = it.next();
            Object animal = getField().getObjectAt(where);

            if(animal instanceof Fox fox && fox.isAlive()) {
                fox.setDead();
                foodLevel = FOOD_VALUE;
                return where;
            }

            if(animal instanceof Rabbit rabbit && rabbit.isAlive()) {
                rabbit.setDead();
                foodLevel = FOOD_VALUE;
                return where;
            }
        }
        return null;
    }

    @Override
    protected int getMaxAge()
    {
        return MAX_AGE;
    }

    @Override
    protected int getBreedingAge()
    {
        return BREEDING_AGE;
    }

    @Override
    protected int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    @Override
    protected Animal createChild(boolean randomAge, Field field, Location location)
    {
        return new Wolf(randomAge, field, location);
    }
}