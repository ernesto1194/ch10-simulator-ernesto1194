import java.util.List;

public abstract class Animal
{
    // basic state of the animal
    private boolean alive;
    private Field field;
    private Location location;

    // age of the animal
    private int age;

    public Animal(Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location);
        age = 0;
    }

    public abstract void act(List<Animal> newAnimals);

    protected boolean isAlive()
    {
        return alive;
    }

    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    protected Location getLocation()
    {
        return location;
    }

    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    protected Field getField()
    {
        return field;
    }

    // 
    // AGE (shared behaviour for all animals)
    // 

    protected int getAge()
    {
        return age;
    }

    protected void setAge(int age)
    {
        this.age = age;
    }

    protected abstract int getMaxAge();

    protected void incrementAge()
    {
        age++;
        if(age > getMaxAge()) {
            setDead();
        }
    }

    //
    // BREEDING (shared logic, species rules differ)
    // 

    protected abstract int getBreedingAge();

    protected boolean canBreed()
    {
        return age >= getBreedingAge();
    }

    protected abstract int breed();

    protected abstract Animal createChild(boolean randomAge, Field field, Location location);

    // moved method from subclasses
    protected void giveBirth(List<Animal> newAnimals)
    {
        List<Location> free = getField().getFreeAdjacentLocations(getLocation());
        int births = breed();

        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Animal young = createChild(false, getField(), loc);
            newAnimals.add(young);
        }
    }
}
