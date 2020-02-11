package no.hvl.dat153.namequiz;

import no.hvl.dat153.namequiz.Person;
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
        ITEM_MAP.put(item.getId(), item);
    }
}
