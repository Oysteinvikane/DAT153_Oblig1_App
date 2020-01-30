package no.hvl.dat153.namequiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import no.hvl.dat153.namequiz.DatabaseList.Person;

public class QuizActivity extends AppCompatActivity {

    private List<Person> items;
    private ImageView imageView;
    private String riktigSvar;

    private static int score = 0;
    private ArrayList<Person> quizListe = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        imageView = findViewById(R.id.imageViewQuiz);




        for (int i = 0; i < DatabaseList.ITEMS.size(); i++) {
            quizListe.add(DatabaseList.ITEMS.get(i));
        }


        Button svarButton = (Button) findViewById(R.id.svarButton);

        svarButton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {

                                             checkAnswer();

                                         }
                                     }
        );

        showNextQuiz();
    }


    public void showNextQuiz() {

        Random random = new Random();
        int randNum = random.nextInt(quizListe.size());

        Person person = quizListe.get(randNum);

        quizListe.remove(person);

        riktigSvar = person.toString();

        imageView.setImageBitmap(person.getImage());

    }

    public void checkAnswer() {

        EditText editText = findViewById(R.id.textInputEditText);
        String svar = editText.getText().toString();

        String title;
        String melding;

        if (riktigSvar.toLowerCase().equals(svar.toLowerCase())) {
            title = "Riktig svar";
            score++;
            TextView textScore = findViewById(R.id.score);
            textScore.setText(String.valueOf(score));
            melding = "Bra jobbet!";
        } else {
            title = "Feil svar...";
            melding = "Riktig svar er: " + riktigSvar;

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(melding);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (quizListe.size() < 1) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(QuizActivity.this);
                    builder1.setTitle("Quizen er ferdig");
                    builder1.setMessage("Din score ble: " + score);
                    builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            moveToMainActivity();
                        }
                    });
                    builder1.setCancelable(false);
                    builder1.show();

                } else {
                    showNextQuiz();
                }
            }
        });


        builder.setCancelable(false);
        builder.show();

    }

    private void moveToMainActivity() {
        Intent intent = new Intent(QuizActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
