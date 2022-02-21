package accessControl.universe;

public class AnimalMaker
    /* The static method will create an Animal, but it returns
       the super-type of Animal, which is the LivingBeings
     */
    public static LivingBeings makeAnAnimal() {
        return new Animal();
    }
}
