/**
 * Quiz aktivitet.
 * Klassen som står bak selve quizen i appen.
*/

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
    private String correctAnswer;
    Button answerButton;

    private int score = 0;
    private ArrayList<Person> quizList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        imageView = findViewById(R.id.imageViewQuiz);

        for (int i = 0; i < DatabaseList.ITEMS.size(); i++) {
            quizList.add(DatabaseList.ITEMS.get(i));
        }

        showNextQuiz();
    }

    // Metode som sender deg til neste spørsmål.
    public void showNextQuiz() {

        Random random = new Random();
        int randNum = random.nextInt(quizList.size());

        Person person = quizList.get(randNum);

        quizList.remove(person);

        correctAnswer = person.toString();

        imageView.setImageBitmap(person.getImage());

    }
    // Metode for å sjekke om svaret er riktig.
    public void checkAnswer(View view) {

        //Oppretter variablene
        EditText editText = findViewById(R.id.textInputEditText);
        String svar = editText.getText().toString();

        String title;
        String message;

        //Sjekker svar og utfører oppaver som skal gjøres om riktig/feil svar.
        if (correctAnswer.toLowerCase().equals(svar.toLowerCase())) {
            title = "Correct answer";
            score++;
            TextView textScore = findViewById(R.id.score);
            textScore.setText(String.valueOf(score));
            message = "Good job!";
        } else {
            title = "Wrong answer...";
            message = "Correct answer is: " + correctAnswer;

        }
        //Skriver ut varsel på skjermen med resultat.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (quizList.size() < 1) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(QuizActivity.this);
                    builder1.setTitle("Quiz finished");
                    builder1.setMessage("Your score: " + score);
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

    //Metode som sender deg til MainActivity.
    private void moveToMainActivity() {
        Intent intent = new Intent(QuizActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
