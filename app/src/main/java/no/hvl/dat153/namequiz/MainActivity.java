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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseButton = findViewById(R.id.Database);

        databaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveToDatabaseActivity();

            }
        });

        quizButton = findViewById(R.id.quiz);

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

        private void moveToDatabaseActivity() {
            Intent intent = new Intent(MainActivity.this, DatabaseListActivity.class);
            startActivity(intent);
        }

        private void moveToAddActivity() {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        }

    private void moveToQuizActivity() {
        if (DatabaseList.ITEMS.size() > 0) {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        } else {
            moveToAddActivity();
        }
    }



}
