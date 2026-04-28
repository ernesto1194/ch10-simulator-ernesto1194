## Exercise 12.51 – Wolf (New Animal Type)

### Overview
For this extension of the predator-prey simulation, a new animal type called Wolf was added to the system. The wolf acts as a top-level predator and interacts with both foxes and rabbits.

---

### Changes Made

- Created a new class `Wolf` that extends `Animal`
- Implemented custom behaviour for movement, breeding, aging, and eating
- Wolves can eat both foxes and rabbits
- Wolves were added to the simulation population during initialization in `Simulator`
- A new probability constant was added to control wolf spawning
- Wolves were assigned a visual representation in the simulation view

---

### Interaction with Other Animals

- Wolves are **apex predators**
- Wolves hunt:
  - Rabbits
  - Foxes
- Foxes and rabbits do not hunt wolves
- Wolves reduce both fox and rabbit populations over time

---

### Design Assumptions

The following assumptions were made when designing the wolf:

- Wolves are stronger than foxes and rabbits
- Wolves are rarer than foxes and rabbits at spawn time
- Wolves have a higher maximum age than foxes
- Wolves breed less frequently than foxes to prevent overpopulation
- Wolves do not have natural predators in the simulation

---

### Impact on Simulation

- Rabbit population decreases more rapidly due to additional predator pressure
- Fox population is also reduced due to wolf predation
- The ecosystem becomes more dynamic and less stable
- Population cycles become more complex compared to the original model

---

### Summary

The introduction of wolves adds a third trophic level to the simulation, increasing complexity and creating a more realistic predator-prey ecosystem.