/**
 * Quiz aktivitet.
 * Klassen som står bak selve quizen i appen.
 */

package no.hvl.dat153.namequiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;


public class QuizActivity extends AppCompatActivity {

    public static int randNum;

    private ImageView imageView;
    private String correctAnswer;
    private SharedPreferences sharedPreferences;
    private boolean showScore;
    private TextView textScore;
    private TextView numberScore;
    private boolean scoreEnabled;
    private int next;

    private int score = 0;
    private ArrayList<Person> quizList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        imageView = findViewById(R.id.imageViewQuiz);
        textScore = findViewById(R.id.textScore);
        numberScore = findViewById(R.id.score);

        next = 0;


        for (int i = 0; i < DatabaseList.ITEMS.size(); i++) {
            quizList.add(DatabaseList.ITEMS.get(i));
        }
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        showScore = sharedPreferences.getBoolean("showScore", true);
        scoreEnabled = sharedPreferences.getBoolean("scoreEnabled", true);
        if (!showScore || !scoreEnabled) {
            textScore.setVisibility(View.INVISIBLE);
            numberScore.setVisibility(View.INVISIBLE);
        }

        showNextQuiz();
    }

    // Metode som sender deg til neste spørsmål.
    public void showNextQuiz() {

        Random random = new Random();
        randNum = random.nextInt(quizList.size());

        Person person = quizList.get(randNum);


        //Person person = quizList.get(next);
        quizList.remove(person);

        correctAnswer = person.getName();

        Bitmap bitmap = convertToBitmap(person.getImage());
        imageView.setImageBitmap(bitmap);

    }

    // Metode for å sjekke om svaret er riktig.
    public void checkAnswer(View view) {

        //Oppretter variablene
        EditText editText = findViewById(R.id.textInputEditText);
        String svar = editText.getText().toString();
        editText.setText("");

        String title;
        String message;

        //Sjekker svar og utfører oppaver som skal gjøres om riktig/feil svar.
        if (correctAnswer.toLowerCase().equals(svar.toLowerCase())) {
            title = "Correct answer";
            score++;
            numberScore.setText(String.valueOf(score));
            message = "Good job!";
        } else {
            title = "Wrong answer...";
            message = "Correct answer is: " + correctAnswer;

        }
        if (quizList.size() < 1) {
            title = "Quiz finished";
            if (scoreEnabled)
                message = "Your score: " + score;
            else
                message = "Quiz taken without score";
        }
        //Skriver ut varsel på skjermen med resultat.

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (quizList.size() < 1) {
                            moveToMainActivity();
                        }
                else {
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

    public Bitmap convertToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}
