package no.hvl.dat153.namequiz;

import android.graphics.Bitmap;

//@Entity(tableName = "person")
public class Person {

  //  @PrimaryKey(autoGenerate = true)
  //  @ColumnInfo(name = "imageId")
    private String id;

  //  @ColumnInfo(name = "name")
    private String name;
    private Bitmap image;

    public Person(String id, String name, Bitmap image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setBitmap(Bitmap id) {
        this.image = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
