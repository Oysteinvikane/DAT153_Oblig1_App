package no.hvl.dat153.namequiz;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DatabaseList {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Person> ITEMS = new ArrayList<Person>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Person> ITEM_MAP = new HashMap<String, Person>();

    public static void addItem(Person item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static final int COUNT = 5;

    static {
        // Add some sample items.
        for (int i = 1; i <= 0; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static List<Person> getItems() {
        return ITEMS;
    }



    private static Person createDummyItem(int position) {
        return new Person(String.valueOf(position), "Navn nr: " + position, null);
    }

    /**
     * Person class for storing a person in the database
     */
    public static class Person {
        public final String id;
        public final String navn;
        public final Bitmap image;

        public Person(String id, String navn, Bitmap image) {
            this.id = id;
            this.navn = navn;
            this.image = image;
        }

        @Override
        public String toString() {
            return navn;
        }
        public Bitmap getImage() {
            return image;
        }
    }
}
