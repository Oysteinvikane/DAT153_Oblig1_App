package no.hvl.dat153.namequiz;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PersonDao {

    @Insert
    public void insertPerson(Person person);

    @Query("SELECT * FROM person WHERE name = :name")
    List<Person> findPerson(String name);

    @Query("SELECT * FROM person WHERE name = :name")
    List<Person> findPersonId(String name);


    @Query("SELECT * FROM person")
    public List<Person> loadAllPersons();

    @Delete
    void deletePerson(Person person);

    @Query("SELECT * FROM person")
    List<Person> getAllPersons();
}