/*
Goal: Create an ArraySet class that supports methods like:
      Part ONE:
      1. contains(item) - see if the item is in the array
      2. add(item) - add item to the array
      3. size() - get to know the size of the array
      4. Iterate each item in the array
         a. While-loop version
         b. For-loop version

      Part TWO:
      5. toString() - convert each item into String?
      6. equals() - see if ... what's this?
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArraySet<T> implements Iterable<T> {

    private T[] items;
    private int size;

//    public boolean hasNext() {
//        return false;
//    }
//    public T next() {
//        return null;
//    }
    /** Each single time someone instantiates the ArraySet, I need to confirm that the items and
       size are brand new (initialized) */
    public ArraySet() {
        items = (T[]) new Object[100]; //100 null at the beginning
        size = 0;
    }

    /* 1. contains(item) - see if the item is in the array
          it returns true if the passed item is found in the array */
    public boolean contains(T item) {
        /* For loop #1 */
        for (int i = 0; i < size; i += 1) {
            /* .equals() or .compareTo()? */
            if (items[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    /* 2. add(item) - add item to the array */
    public void add(T x) {
        if (x == null) {
            return;
        }
        /* Suppose we try to avoid passing duplicated value to our array */
        if (contains(x)) {
            return;
        }
        items[size] = x;
        /* Remember to update the size ! */
        size += 1;
    }

    /* 3. size() - get to know the size of the array */
    public int size() {
        return size;
    }

    /* 4. iterator() - create a new Iterator object that iterates each item in the array T[] items */
    @Override
    public Iterator<T> iterator() {
        /* ArraySetIterator is the new Iterator object since it implements Iterator<T> */
        return new ArraySetIterator();
    }

    /* 5. Iterate each item in the array
          a. While-loop version
             - We are NOT trying to write an instance method that supports iterating
               through the whole array
               e.g. public void iterateMethod() {
                        for (int i = 0; i < size; i += 1) {

                        }
                    }
             - Instead, we attempt to take advantage of OOP - create objects that helps
               us to implements the iteration functions
               We need an iteration object that has methods to access the T[] items in
               outer class, then clearly knows if
          b. For-loop version          */
    /* The object that has methods to access T[] items */
    public class ArraySetIterator implements Iterator<T> {
        /* The current position in the T[] items (the array) */
        private int currentPosition;
        /* Common format: Every instantiation create a brand new currentPosition thing */
        public ArraySetIterator() {
            currentPosition = 0;
        }
        /* As I mentioned above, I want to create an object that has methods that check and return items
           from the array */
           /*Iterator: as an object, Iterator
           1. Set the current position at the beginning of the array - currentPosition = 0,
              so we can access items[0]
           2. Checks the array to see if there is another item
           3. If there is another item, we move on to the next item and return that item
         */
        @Override
        public boolean hasNext() {
            if (currentPosition < size) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            T item = items[currentPosition];
            currentPosition += 1;
            return item;
        }
    } //Close inner class

    /*
    Part TWO:
      5. toString() - convert each item into String?
      6. equals() - see if ... what's this?
     */
    /* Since every class in java extends Object, so every class inherits toString() from
    *  Object. I want to DIY my own toString() method, so that it could support make my
    *  T[] items array be printed as a string thing
    *  Steps
    *       1. Locate & access the core array in my ArraySet - T[] item in this case
    *       2. Loop through every element/item in the core array - Iterator + enhanced for loop
    *       3. Covert each item into String
    *       4. Organize the result as a printable String thing
    *  */

//    @Override
//    public String toString() {
//        /* Local variable returnString that will be updated to be returned */
//        String returnString = "{";
//        /* Iterator + enhanced for loop - access T[] item in this instance! */
//        for (T item : this) {
//            /* Update the returnString with each item converted from Integer-type object */
//            returnString += item; //Automatically convert T item into String when I try to combine them.
//            returnString += ", "; //   Continue above, no need to - returnString += ", ";
//        }
//        returnString += "}";
//        return returnString;
//    }
//    The approach below is linear, wasting time! Because concatenating one character to a string takes
//    a lot of TIME! Concatenating is like String a; a = a + "yoo";
//    @Override
//    public String toString() {
//        String returnString = "{";
//        //{1, 2, 3} size: 3
//        for (int i = 0; i < size - 1; i += 1) {
//            returnString += items[i].toString();
//            returnString += ", "; //<--- This is why we have extra comma
//        }
//        /* We make the last item independent because we want to void see the extra comma
//           Before: {1, 2, 3, } After: {1, 2, 3}
//         */
//        returnString += items[size - 1];
//
//        returnString += "}";
//        return returnString;
//    }
//    /* A better way to avoid concatenating characters to a string */
//    @Override
//    public String toString() {
//        /* Create a string builder that takes the first character "{" */
//        StringBuilder sb = new StringBuilder("{");
//        for (int i = 0; i < size - 1; i += 1) {
//            sb.append(items[i]);
//            sb.append(", ");
//        }
//        sb.append(items[size - 1]);
//        sb.append("}");
//        return sb.toString();
//    }
    /* The best way - toString() with String.join() */
    @Override
    public String toString() {
        /* Create an ArrayList that holds all the item in the T[] from ArraySet */
        List<String> listOfItem = new ArrayList<>();
        /* Loop through T[] from ArraySet, convert each T item into String */
        for (T item : this) {
            listOfItem.add(item.toString());
        }
        return "{" + String.join(", ", listOfItem) + "}";
    }

    /* Very Cool of() method */
    public static <Glerp> ArraySet<Glerp> of(Glerp... stuff) {
        /* Instantiate an ArraySet in the static method so that we could access the T[] item*/
        ArraySet<Glerp> items = new ArraySet<>();
        for (Glerp item : stuff) {
            items.add(item);
        }
        return items;
    } // Then we could do ArraySet<String> stringArraySet = ArraySet.of("1", "2", "3");

    /* Override equals() so it could compare two ArraySet to see if they are same or not
       Also, build "THE STANDARD" of what can be considered as "THE SAME"
     */
//    @Override
//    public boolean equals(Object other) {
//        ArraySet<T> o = (ArraySet<T>) other;
//
//        /* See if the passed object (other) is null */
//        if (other == null) {
//            return false;
//        }
//        /* See if the passed object (other) is an ArraySet */
//        if (other.getClass() != this.getClass()) {
//            return false;
//        }
//        /* See if two ArraySet have the same size */
//        if (o.size != this.size) {
//            return false;
//        }
//        /* Indicate the standard - I do not care about the order of items in the ArraySet
//        *  two ArraySet are considered as the same IF they have the same items (even NOT in order) */
//        for (T item : this) {
//            if (!o.contains(item)) {
//                return false;
//            }
//            return true;
//        }
//    }
    /*
    Override the equals(Object obj)
    It could take an arraySet and see if two arraySet have the same items
    We don't care about the order of the items
     */
    @Override
    public boolean equals(Object other) {
        /* In case 'this' and 'other' point to the same object */
        if (this == other) {
            return true;
        }
        /* In case 'other' is null */
        if (other == null) {
            return false;
        }
        /* In case 'other' is NOT an ArraySet */
        if (other.getClass() != this.getClass()) {
            return false;
        }
        /* Cast 'other' into ArraySet, so we could do the following things */
        ArraySet<T> otherArraySet = (ArraySet<T>) other;
        /* In case 'other' has a different size */
        if (otherArraySet.size() != this.size) {
            return false;
        }
        /* Loop through each item in 'this' ArraySet and see if the 'other' ArraySet has the same item */
        for (T item : this) {
            if (!otherArraySet.contains(item)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        /* Static block cannot directly access the non-static inner class.
           Thus, we need to instantiate the outer class, and indicate
           the relationship between outer-inner objects by using dot .
           Type: outer-type.inner-type = new outer-instance.new inner-instance()
         */
        ArraySet<Integer> arraySet1 = new ArraySet<>();
        arraySet1.add(1);
        arraySet1.add(2);
        arraySet1.add(3);

        ArraySet<String> arraySet2 = new ArraySet<>();
        arraySet2.add("1");
        arraySet2.add("2");
        arraySet2.add("3");

        boolean equalsOrNot = arraySet1.equals(arraySet2);
        System.out.println(equalsOrNot);
        System.out.println(arraySet1.toString());


//        String s = arraySet1.toString();
//        System.out.println(s);

//        ArraySet<Integer>.ArraySetIterator asi1 = arraySet1.new ArraySetIterator();
//        /* Although iterator() returns an inner-instance, its signature tells that it returns
//           an interface-instance "public Iterator<T> iterator() {}"
//           Thus, we need to cast interface-instance down to the inner-instance
//           , which is (ArraySet<Integer>.ArraySetIterator)
//         */
//        ArraySet<Integer>.ArraySetIterator asi2 = (ArraySet<Integer>.ArraySetIterator) arraySet1.iterator();
//        /* The FUNNY thing is, if outer-class ArraySet does not mark a generics <>,
//           we actually could simply write things as followings:
//           ArraySetIterator asi2 = (ArraySet<Integer>.ArraySetIterator) arraySet1.iterator();
////SEE above, No outer-type needed
//         */

//        /* The goal of building such an instance method "iterator()"
//             - Access & create an inner-instance ArraySetIterator directly from main()
//           The goal of building such an inner class "ArraySetIterator{} "
//             - OOP thoughts, we have an instance that could iterate our array
//         */
//        Iterator<Integer> asi = arraySet1.iterator();
//        /* Using while loop to iterate & print each item in the array T[] items. */
//        while (asi.hasNext()) {
//            /* Since asi is <Integer> */
//            Integer eachItem = asi.next();
//            System.out.println(eachItem);
//        }
//
//        for (int eachItem : arraySet1) {
//            System.out.println(eachItem);
//        }

    }

} //Close outer class



















