package no.hvl.dat153.namequiz;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)

public class DAOTest {

    private PersonDao personDao;
    private RoomDBQuiz db;
    private Context ctx;

    @Before
    public void setUp() {
        ctx = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(ctx, RoomDBQuiz.class).build();
        personDao = db.personDAO();
    }


    @Test
    public void TestAdd() {

        Resources res = ctx.getResources();

        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.pic1);
        byte[] bytearr = convertToByteArray(bitmap);

        Person p = new Person("test", bytearr);
        personDao.insertPerson(p);

        ArrayList<Person> list = (ArrayList<Person>) personDao.findPerson("test");

        assert (list.contains("test"));
    }
    @Test
    public void TestAddRemove() {

        Resources res = ctx.getResources();

        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.pic1);
        byte[] bytearr = convertToByteArray(bitmap);

        Person p = new Person("test", bytearr);
        personDao.insertPerson(p);

        personDao.deletePerson(p);
        ArrayList<Person> list = (ArrayList<Person>) personDao.findPerson("test");

        assert (!list.contains("test"));
    }

    public byte[] convertToByteArray(Bitmap bitmap) {

        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /* Ignored for PNGs */, blob);
        byte[] bitmapdata = blob.toByteArray();

        return bitmapdata;
    }
}
