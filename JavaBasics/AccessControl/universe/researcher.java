/* This is a default package without being indicated with a specific package name
*  AVOID to use package like this! */
import accessControl.universe.Animal;
import accessControl.universe.AnimalMaker;
import accessControl.universe.LivingBeings;

public class researcher {
    public static void main(String[] args) {
        /* The package-private class Animal CANNOT be instantiated in a class
           from a different package
           I even cannot import the Animal class (SEE above)
         */
        Animal a = new Animal();

        /* See if we could call non_Static_Print_NoBody() in Animal class */
        /* (NOT WORK) The first way is using super/static type LivingBeings */
        LivingBeings lb1 = new Animal();
        /* (WORKS WELL) The second way is using an AnimalMaker */
        LivingBeings lb2 = AnimalMaker.makeAnAnimal();

        /* We can access the default method in interface */
        lb2.default_Non_Static_Print_Has_Body();

        /* We can access the instance method
           that is 'public' and 'overridden' in Animal */
        lb2.non_Static_Print_NoBody();

        /* We CANNOT access the 'Non-overridden' instance method
           by super/static type like LivingBeings */
        lb2.yell();

    }

}
