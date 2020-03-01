package no.hvl.dat153.namequiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link DatabaseListActivity}.
 */
public class DatabaseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);



        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        AppBarLayout appBarLayout1 = findViewById(R.id.app_bar);
        appBarLayout1.setExpanded(false, false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);


        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            final Bundle arguments = new Bundle();
            arguments.putString(DatabaseDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(DatabaseDetailFragment.ARG_ITEM_ID));
            DatabaseDetailFragment fragment = new DatabaseDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.database_detail_container, fragment)
                    .commit();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(DatabaseList.ITEM_MAP.get(arguments.getString(DatabaseDetailFragment.ARG_ITEM_ID)));
                    moveToDatabaseActivity();
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, DatabaseListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void moveToDatabaseActivity() {
        Intent intent = new Intent(this, DatabaseListActivity.class);
        startActivity(intent);
    }
    public void deleteItem(Person item) {
        final PersonDao personDao = InitialDataApp.roomDBQuiz.personDAO();
        DatabaseList.ITEMS.remove(item);
        personDao.deletePerson(item);
        Intent i = new Intent(this, DatabaseListActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("EXIT", true);
        startActivity(i);
        this.finish();

    }
}
