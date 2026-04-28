import java.util.List;

/**
 * A class representing shared characteristics of animals.
 * 
 * Now includes age handling for all animals.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public abstract class Animal
{
    private boolean alive;
    private Field field;
    private Location location;

    // NEW: shared age field
    private int age;

    public Animal(Field field, Location location)
    {
        alive = true;
        this.field = field;
        setLocation(location);
        age = 0;
    }
    
    abstract public void act(List<Animal> newAnimals);

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

    // NEW METHODS FOR AGE
    protected int getAge()
    {
        return age;
    }

    protected void setAge(int age)
    {
        this.age = age;
    }

    protected void incrementAge()
    {
        age++;
    }
}
