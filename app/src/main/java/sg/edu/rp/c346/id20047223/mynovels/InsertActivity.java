package sg.edu.rp.c346.id20047223.mynovels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class InsertActivity extends AppCompatActivity {

    EditText etName, etAuthor, etGenre, etSynopsis;
    Button btnInsert, btnBack;
    RadioGroup rgRating;
    RadioButton r1, r2, r3, r4, r5;
    RadioGroup rgStatus;
    ArrayList<Novel> ln;
    CustomAdapter an;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        etName = findViewById(R.id.editTextTitle);
        etAuthor = findViewById(R.id.editTextAuthor);
        etGenre = findViewById(R.id.editTextGenre);
        etSynopsis = findViewById(R.id.editTextSynopsis);
        btnInsert = findViewById(R.id.btnInsert);
        btnBack = findViewById(R.id.btnBack);
        rgRating = findViewById(R.id.rgRating);
        rgStatus = findViewById(R.id.rgStatus);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String novelName = etName.getText().toString();
                String novelAuthor = etAuthor.getText().toString();
                String novelGenre = etGenre.getText().toString();
                String novelSynopsis = etSynopsis.getText().toString();
                String novelStatus = "";
                switch (rgStatus.getCheckedRadioButtonId()){
                    case R.id.rbs1:
                        novelStatus = "Not Finished";
                        break;
                    case R.id.rbs2:
                        novelStatus = "Finished";
                        break;
                }
                int stars = 1;
                switch (rgRating.getCheckedRadioButtonId()){
                    case R.id.rb1:
                        stars = 1;
                        break;
                    case R.id.rb2:
                        stars = 2;
                        break;
                    case R.id.rb3:
                        stars = 3;
                        break;
                    case R.id.rb4:
                        stars = 4;
                        break;
                    case R.id.rb5:
                        stars = 5;
                        break;
                }
                DBHelper dbh = new DBHelper(InsertActivity.this);
                long inserted_title = dbh.insertNovelName(novelName);
                long inserted_author = dbh.insertNovelAuthor(novelAuthor);
                long inserted_genre = dbh.insertNovelGenre(novelGenre);
                long inserted_synopsis = dbh.insertNovelNote(novelSynopsis);
                long inserted_rating = dbh.insertNovelRating(stars);
                long inserted_status = dbh.insertNovelStatus(novelStatus);

                if (inserted_title != -1 && inserted_author != -1 && inserted_genre != -1 && inserted_synopsis != -1 && inserted_rating != -1 && inserted_status != -1){
                    ln.clear();
                    ln.addAll(dbh.getAllNovels());
                    an.notifyDataSetChanged();
                    Toast.makeText(InsertActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}