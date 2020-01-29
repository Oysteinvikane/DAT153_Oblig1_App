package no.hvl.dat153.namequiz;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Random;

import no.hvl.dat153.namequiz.DatabaseList.Person;

public class QuizActivity extends AppCompatActivity {

    private List<Person> items;
    private ImageView imageView;
    private String riktigSvar;

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        imageView = findViewById(R.id.imageViewQuiz);


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
        int randNum = random.nextInt(DatabaseList.ITEMS.size());

        Person person = DatabaseList.ITEMS.get(randNum);

        DatabaseList.ITEMS.remove(person);

        riktigSvar = person.toString();

        imageView.setImageBitmap(person.getImage());


    }

    public void checkAnswer() {

        EditText editText = findViewById(R.id.textInputEditText);
        String svar = editText.getText().toString();

        String melding;

        if (riktigSvar.toLowerCase().equals(svar.toLowerCase())) {
            melding = "Riktig svar";
            score++;
            TextView textScore = findViewById(R.id.score);
            textScore.setText(score);
        } else {
            melding = "Feil svar...";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(melding);
        builder.setMessage("Riktig svar er: " + riktigSvar);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (DatabaseList.ITEMS.size() < 1) {

                } else {
                    showNextQuiz();
                }
            }
        });

        builder.setCancelable(false);
        builder.show();

    }
}
