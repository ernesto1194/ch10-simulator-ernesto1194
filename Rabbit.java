import java.util.List;
import java.util.Random;

public class Rabbit extends Animal
{
    private static final int BREEDING_AGE = 5;
    private static final int MAX_AGE = 40;
    private static final double BREEDING_PROBABILITY = 0.12;
    private static final int MAX_LITTER_SIZE = 4;
    private static final Random rand = Randomizer.getRandom();

    public Rabbit(boolean randomAge, Field field, Location location)
    {
        super(field, location);

        if(randomAge) {
            setAge(rand.nextInt(MAX_AGE));
        }
        else {
            setAge(0);
        }
    }

    public void act(List<Animal> newRabbits)
    {
        incrementAge();

        if(isAlive()) {
            giveBirth(newRabbits);

            Location newLocation = getField().freeAdjacentLocation(getLocation());

            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                setDead();
            }
        }
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
        return new Rabbit(randomAge, field, location);
    }
}
