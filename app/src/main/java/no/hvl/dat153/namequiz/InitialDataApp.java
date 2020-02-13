/**
 * Klasse som står for å legge til eksempeldata fra resource-mappen.
 */


package no.hvl.dat153.namequiz;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;


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

        byte[] byte1 = AddActivity.convertToByteArray(bm1);
        byte[] byte2 = AddActivity.convertToByteArray(bm2);
        byte[] byte3 = AddActivity.convertToByteArray(bm3);

        //Legger bildene til i databaselist.
        DatabaseList.addItem(new Person(DatabaseList.ITEMS.size() + 1, "Kjetil", byte1));
        DatabaseList.addItem(new Person(DatabaseList.ITEMS.size() + 1, "Øystein", byte2));
        DatabaseList.addItem(new Person(DatabaseList.ITEMS.size() + 1, "Vilhelm", byte3));
    }
}
