import java.util.List;

public abstract class Animal
{
    private boolean alive;
    private Field field;
    private Location location;

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

    //  AGE 

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

    //  BREEDING 

    protected abstract int getBreedingAge();

    protected boolean canBreed()
    {
        return getAge() >= getBreedingAge();
    }

    protected abstract int breed();

    protected abstract Animal createChild(boolean randomAge, Field field, Location location);

    //  MOVED METHOD 

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
