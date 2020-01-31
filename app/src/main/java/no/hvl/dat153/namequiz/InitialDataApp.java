/**
 * Klasse som står for å legge til eksempeldata fra resource-mappen.
 */


package no.hvl.dat153.namequiz;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

import no.hvl.dat153.namequiz.DatabaseList.Person;

public class InitialDataApp extends Application {
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

        //Legger bitmappene til i en liset
        ArrayList<Bitmap> bmList = new ArrayList<>();
        bmList.add(bm1);
        bmList.add(bm2);
        bmList.add(bm3);

        //Legger bildene til i databaselist.
        DatabaseList.addItem(new Person(String.valueOf(DatabaseList.ITEMS.size() + 1), "Kjetil", bmList.get(0)));
        DatabaseList.addItem(new Person(String.valueOf(DatabaseList.ITEMS.size() + 1), "Øystein", bmList.get(1)));
        DatabaseList.addItem(new Person(String.valueOf(DatabaseList.ITEMS.size() + 1), "Vilhelm", bmList.get(2)));
    }
}
