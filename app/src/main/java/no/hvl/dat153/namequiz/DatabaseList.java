package no.hvl.dat153.namequiz;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing a List to the
 * Database
 *
 */
public class DatabaseList {

    /**
     * An array of person items.
     */
    public static final List<Person> ITEMS = new ArrayList<Person>();


    public static final Map<String, Person> ITEM_MAP = new HashMap<String, Person>();

    public static void addItem(Person item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }


    /**
     * Person class for storing a person in the database
     */
    public static class Person {
        public final String id;
        public final String name;
        public final Bitmap image;

        public Person(String id, String name, Bitmap image) {
            this.id = id;
            this.name = name;
            this.image = image;
        }

        @Override
        public String toString() {
            return name;
        }
        public Bitmap getImage() {
            return image;
        }
    }
}
