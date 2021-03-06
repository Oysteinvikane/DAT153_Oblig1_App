package no.hvl.dat153.namequiz;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.Room;

import java.util.ArrayList;


public class InitialDataApp extends Application {

    public static RoomDBQuiz roomDBQuiz;


    @Override
    public void onCreate() {

        super.onCreate();

        //Lager Bitmap av bildene

        Bitmap image1 = BitmapFactory.decodeResource(getResources(), R.drawable.pic1);
        Bitmap image2 = BitmapFactory.decodeResource(getResources(), R.drawable.pic2);
        Bitmap image3 = BitmapFactory.decodeResource(getResources(), R.drawable.pic3);

        //Scaler bildene til riktig størrelse.
        Bitmap bm1 = Bitmap.createScaledBitmap(image1, 1000, 1333, true);
        Bitmap bm2 = Bitmap.createScaledBitmap(image2, 1000, 1333, true);
        Bitmap bm3 = Bitmap.createScaledBitmap(image3, 1000, 1333, true);

        byte[] byte1 = AddActivity.convertToByteArray(bm1);
        byte[] byte2 = AddActivity.convertToByteArray(bm2);
        byte[] byte3 = AddActivity.convertToByteArray(bm3);


        //Legger bildene til i databaselist.
        DatabaseList.addItem(new Person("Kjetil", byte1));
        DatabaseList.addItem(new Person("Øystein", byte2));
        DatabaseList.addItem(new Person("Vilhelm", byte3));

        roomDBQuiz = Room.databaseBuilder(getApplicationContext(), RoomDBQuiz.class, "persondb").allowMainThreadQueries().build();

        final PersonDao personDao = roomDBQuiz.personDAO();
        final ArrayList<Person> persons = (ArrayList<Person>) personDao.loadAllPersons();

        for (Person p : persons) {
            DatabaseList.addItem(p);
        }

    }
}