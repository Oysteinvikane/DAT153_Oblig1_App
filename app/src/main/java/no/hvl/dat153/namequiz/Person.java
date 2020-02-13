package no.hvl.dat153.namequiz;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class Person {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "imageId")
    public int id;



    @ColumnInfo(name = "name")
    private String name;
    private byte[] image;

    public Person(int id, String name, byte[] image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }
}
