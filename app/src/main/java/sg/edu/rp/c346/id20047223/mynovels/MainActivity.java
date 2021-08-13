package sg.edu.rp.c346.id20047223.mynovels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnadd, btnsort, btngenre;
    EditText etGenre;
    ListView lvNovels;
    ArrayList<Novel> ln;
    CustomAdapter an;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnadd = findViewById(R.id.btnAdd);
        btngenre = findViewById(R.id.sortbtn);
        btnsort = findViewById(R.id.sort2btn);
        lvNovels = findViewById(R.id.listViewNovels);
        etGenre = findViewById(R.id.editTextGenre2);

        DBHelper dbh = new DBHelper(MainActivity.this);
        ln = dbh.getAllNovels();
        dbh.close();

        ln = new ArrayList<Novel>();
        an = new CustomAdapter(this,
                R.layout.row, ln);
        lvNovels.setAdapter(an);

        lvNovels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Novel novel = ln.get(position);
                Intent i = new Intent(MainActivity.this,
                        EditActivity.class);
                i.putExtra("novel", novel);
                startActivity(i);
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(MainActivity.this,
                        InsertActivity.class);
                startActivity(i);
            }
        });

        btngenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etGenre.setVisibility(View.VISIBLE);
                DBHelper dbh = new DBHelper(MainActivity.this);
                ln.clear();
                String genre = etGenre.getText().toString();
                ln.addAll(dbh.sortAllNovels(genre));
                

            }
        });

        btnsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.getNovelByStars();
                dbh.close();
                finish();
            }
        });




    }
}