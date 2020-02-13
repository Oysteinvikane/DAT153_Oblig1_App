/**
 * Klassen som kjøres når man åpner appen.
 * Inneholder metoder som aktiveres når knapper i appen trykkes.
 */


package no.hvl.dat153.namequiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private EditText usernameEditText;
    private TextView usernameTextView;
    private TextInputLayout inputLayout;
    private SharedPreferences.Editor editor;
    private String user;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        usernameTextView = findViewById(R.id.appName);
        inputLayout = findViewById(R.id.nameInputLayout);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();



        updateView();


    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }
        //Metode som sender deg til DatabaseActivity
        public void moveToDatabaseActivity(View view) {
            Intent intent = new Intent(MainActivity.this, DatabaseListActivity.class);
            startActivity(intent);
        }
        //Metode som sender deg til AddActivity
        public void moveToAddActivity(View view) {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        }
        //Metode som sender deg til QuizActivity
     public void moveToQuizActivity(View view) {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);

    }
    public void updateView() {
        String username = sharedPreferences.getString("username", null);
        if (username != null) {
            usernameEditText.setVisibility(View.INVISIBLE);
            usernameTextView.setText("Welcome " + username.trim());
            inputLayout.setVisibility(View.INVISIBLE);
        } else {
            inputLayout.setVisibility(View.VISIBLE);
            usernameEditText.setVisibility(View.VISIBLE);
        }
    }

    public void updateName(View view) {


        String username = usernameEditText.getText().toString().trim();
        usernameEditText.setText("");
        if (!username.equals("")) {
            editor.putString("username", username);
        } else {
            editor.remove("username");
        }
        editor.commit();
        updateView();


    }

}
