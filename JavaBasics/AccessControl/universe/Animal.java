package accessControl.universe;

/* A package-private class - Animal */
class Animal implements LivingBeings {
    /* A package-private class can have public overridden methods,
       other classes from a different package could access
       those public methods by using the static-type (super-type) of
       the package-private class
     */

    String theOnlyFriend;

    @Override
    public void non_Static_Print_NoBody() {
        System.out.println("Animals can move!");
    }

    @Override
    public void addFriends(String friendName) {
        theOnlyFriend = friendName;
    }

    public Object yell(){
        return null;
    }

    public static void main(String[] args) {
        /* In same package */
        /* Call the static method of interface LivingBeings */
        LivingBeings.static_Print();


    }
}
