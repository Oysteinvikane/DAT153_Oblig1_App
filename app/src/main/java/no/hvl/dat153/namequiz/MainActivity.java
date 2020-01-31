/**
 * Klassen som kjøres når man åpner appen.
 * Inneholder metoder som aktiveres når knapper i appen trykkes.
 */


package no.hvl.dat153.namequiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button databaseButton;

    private Button addButton;

    private Button quizButton;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseButton = findViewById(R.id.quiz_list);

        databaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveToDatabaseActivity();

            }
        });

        quizButton = findViewById(R.id.quizstart);

        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveToQuizActivity();

            }
        });

        addButton = findViewById(R.id.add);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveToAddActivity();

            }
        });
    }
        //Metode som sender deg til DatabaseActivity
        private void moveToDatabaseActivity() {
            Intent intent = new Intent(MainActivity.this, DatabaseListActivity.class);
            startActivity(intent);
        }
        //Metode som sender deg til AddActivity
        private void moveToAddActivity() {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        }
        //Metode som sender deg til QuizActivity
     private void moveToQuizActivity() {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);

    }



}
