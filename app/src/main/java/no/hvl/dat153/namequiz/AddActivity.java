package no.hvl.dat153.namequiz;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;

import no.hvl.dat153.namequiz.DatabaseList.Person;


public class AddActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button addButton = (Button) findViewById(R.id.velgBilde);
        addButton = findViewById(R.id.velgBilde);

        Button leggTilButton = (Button) findViewById(R.id.leggTilPerson);
        leggTilButton = findViewById(R.id.leggTilPerson);

        addButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {

                                             Intent i = new Intent(
                                                     Intent.ACTION_PICK,
                                                     android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                                             startActivityForResult(i, RESULT_LOAD_IMAGE);

                                         }
                                     }
        );
        leggTilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                EditText editText = findViewById(R.id.content);
                String result = editText.getText().toString();
                Person p = new Person(String.valueOf(6), result, bitmap);
                DatabaseList.addItem(p);
                moveToMainActivity();
            }
        });


    }

    private void moveToMainActivity() {
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * opens photo gallery on phone
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                imageView.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }
        }
    }
    }
