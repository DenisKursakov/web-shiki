package by.epam.lab.beans;

public abstract class Entity {
     long id;

     public Entity(long id) {
          this.id = id;
     }

     public long getId() {
          return id;
     }
}
