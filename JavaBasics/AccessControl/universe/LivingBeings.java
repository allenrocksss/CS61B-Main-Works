package accessControl.universe;

public interface LivingBeings {

    /* 1. Static method in an interface should have a body */
    static void static_Print() {
        System.out.println("static print!");
    }

    /* 2. Non-static method in an interface could have NO Body - BUT it must be overridden */
        /* - No need to write public since NO-body method MUST be overridden! */
    void non_Static_Print_NoBody();

    /* 3. Non-static method could either have NO Body or have been marked
              as 'default' */
    default void default_Non_Static_Print_Has_Body() {
        System.out.println("default could have body!");
    }

    /* 4. Non-static method addFriends */
    void addFriends(String s);
}
