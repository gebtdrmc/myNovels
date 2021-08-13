package sg.edu.rp.c346.id20047223.mynovels;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    EditText etName, etAuthor, etGenre, etSynopsis;
    Button btnEdit, btnDelete, btnCancel;
    RadioGroup rgRating;
    RadioButton r1, r2, r3, r4, r5;
    RadioGroup rgStatus;
    RadioButton rb1, rb2;
    RatingBar rbRating;
    Novel data;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit2);

        etName = findViewById(R.id.editTextTitle);
        etAuthor = findViewById(R.id.editTextAuthor);
        etGenre = findViewById(R.id.editTextGenre);
        etSynopsis = findViewById(R.id.editTextSynopsis);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        rgRating = findViewById(R.id.rgRating);
        rgStatus = findViewById(R.id.rgStatus);
        rbRating = findViewById(R.id.rbStars);
        r1 = findViewById(R.id.rb1);
        r2 = findViewById(R.id.rb2);
        r3 = findViewById(R.id.rb3);
        r4 = findViewById(R.id.rb4);
        r5 = findViewById(R.id.rb5);
        rb1 = findViewById(R.id.rbs1);
        rb2 = findViewById(R.id.rbs2);

        Intent i = getIntent();
        data = (Novel) i.getSerializableExtra("novel");

        etName.setText(data.getTitle());
        etAuthor.setText(data.getAuthor());
        etGenre.setText(data.getGenre());
        etSynopsis.setText(data.getSynopsis());
        switch (data.getRating()) {
            case 1:
                r1.setChecked(true);
                break;
            case 2:
                r2.setChecked(true);
                break;
            case 3:
                r3.setChecked(true);
                break;
            case 4:
                r4.setChecked(true);
                break;
            case 5:
                r5.setChecked(true);
                break;
        }

        if (data.getStatus().equalsIgnoreCase("Not Finished")){
            index = 1;
        } else {
            index = 2;
        }

        switch (index){
            case 1:
                rb1.setChecked(true);
                break;
            case 2:
                rb2.setChecked(true);
                break;
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setTitle(etName.getText().toString());
                data.setAuthor(etAuthor.getText().toString());
                data.setGenre(etGenre.getText().toString().trim());
                data.setSynopsis(etSynopsis.toString());
                int rbID = rbRating.getNumStars();
                RadioButton rb = (RadioButton) findViewById(rbID);
                data.setRating(Integer.parseInt(rb.getText().toString()));

                dbh.updateNovel(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete this island.");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Cancel", null);

                myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(EditActivity.this);
                        dbh.deleteNovel(data.getId());
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard changes.");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Do not discard", null);

                myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            }
        });
    }
}