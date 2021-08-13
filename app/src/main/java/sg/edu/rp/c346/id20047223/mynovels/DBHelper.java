package sg.edu.rp.c346.id20047223.mynovels;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "novel.db";
    private static final String TABLE_NOVEL = "novel";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOVEL_TITLE = "title";
    private static final String COLUMN_NOVEL_AUTHOR = "novelAuthor";
    private static final String COLUMN_NOVEL_GENRE = "genre";
    private static final String COLUMN_NOVEL_NOTE = "synopsis";
    private static final String COLUMN_RATING = "_rating";
    private static final String COLUMN_NOVEL_STATUS = "status";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_NOVEL + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOVEL_TITLE + " TEXT , " + COLUMN_NOVEL_AUTHOR + " TEXT , " + COLUMN_NOVEL_GENRE +
                " TEXT , " + COLUMN_NOVEL_NOTE + " TEXT , " + COLUMN_RATING + " INTEGER , " + COLUMN_NOVEL_STATUS + " TEXT )";

        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOVEL);
        onCreate(db);

    }

    public long insertNovelName(String novelName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOVEL_TITLE, novelName);
        long result = db.insert(TABLE_NOVEL, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }


        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1

        return result;


    }

    public long insertNovelAuthor(String authorName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOVEL_AUTHOR, authorName);
        long result = db.insert(TABLE_NOVEL, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }


        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1

        return result;


    }
    public long insertNovelGenre(String genreName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOVEL_GENRE, genreName);
        long result = db.insert(TABLE_NOVEL, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }


        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1

        return result;


    }

    public long insertNovelNote(String synopsisNote) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOVEL_NOTE, synopsisNote);
        long result = db.insert(TABLE_NOVEL, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }


        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1

        return result;


    }

    public long insertNovelRating(int novelRating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(String.valueOf(COLUMN_RATING), novelRating);
        long result = db.insert(TABLE_NOVEL, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }


        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1

        return result;


    }

    public long insertNovelStatus(String statusName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOVEL_STATUS, statusName);
        long result = db.insert(TABLE_NOVEL, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }


        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1

        return result;


    }
    public ArrayList<Novel> getAllNovels() {
        ArrayList<Novel> novels = new ArrayList<Novel>();

        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_NOVEL_TITLE +  ", " +  COLUMN_NOVEL_AUTHOR +  ", " + COLUMN_NOVEL_GENRE + ", " +
                COLUMN_NOVEL_NOTE + ", " + COLUMN_RATING + ", " + COLUMN_NOVEL_STATUS
                + " FROM " + TABLE_NOVEL;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String novelTitle = cursor.getString(1);
                String novelAuthor = cursor.getString(2);
                String novelGenre = cursor.getString(3);
                String novelSynopsis = cursor.getString(4);
                int rating = cursor.getInt(5);
                String novelStatus = cursor.getString(6);

                Novel novel = new Novel(id, novelTitle, novelAuthor, novelGenre, novelSynopsis, rating, novelStatus);
                novels.add(novel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return novels;
    }
    public int updateNovel(Novel data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOVEL_TITLE, data.getTitle());
        values.put(COLUMN_NOVEL_AUTHOR, data.getAuthor());
        values.put(COLUMN_NOVEL_GENRE, data.getGenre());
        values.put(COLUMN_NOVEL_NOTE, data.getSynopsis());
        values.put(String.valueOf(COLUMN_RATING), data.getRating());
        values.put(COLUMN_NOVEL_STATUS, data.getStatus());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_NOVEL, values, condition, args);
        if (result < 1){
            Log.d("DBHelper", "Update failed");
        }

        db.close();
        return result;
    }
    public int deleteNovel(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_NOVEL, condition, args);
        if (result < 1){
            Log.d("DBHelper", "Update failed");
        }

        db.close();
        return result;
    }

    public ArrayList<Novel> sortAllNovels(String keyword) {
        ArrayList<Novel> novels = new ArrayList<Novel>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_NOVEL_TITLE, COLUMN_NOVEL_AUTHOR, COLUMN_NOVEL_GENRE, COLUMN_NOVEL_NOTE, COLUMN_RATING, COLUMN_NOVEL_STATUS};
        String condition = COLUMN_NOVEL_GENRE + " Like ?";
        String[] args = { "%" +  keyword + "%"};
        Cursor cursor = db.query(TABLE_NOVEL, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String novelTitle = cursor.getString(1);
                String novelAuthor = cursor.getString(2);
                String novelGenre = cursor.getString(3);
                String novelSynopsis = cursor.getString(4);
                int rating = cursor.getInt(5);
                String novelStatus = cursor.getString(6);

                Novel novel = new Novel(id, novelTitle, novelAuthor, novelGenre, novelSynopsis, rating, novelStatus);
                novels.add(novel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return novels;
    }

    public ArrayList<Novel> getNovelByStars(){
        ArrayList<Novel> novels = new ArrayList<Novel>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NOVEL_TITLE, COLUMN_NOVEL_AUTHOR, COLUMN_NOVEL_GENRE, COLUMN_NOVEL_NOTE, COLUMN_RATING, COLUMN_NOVEL_STATUS};
        String condition = COLUMN_RATING + " > 3";

        Cursor cursor;
        cursor = db.query(TABLE_NOVEL, columns, condition, null, null, null, COLUMN_RATING);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String novelTitle = cursor.getString(1);
                String novelAuthor = cursor.getString(2);
                String novelGenre = cursor.getString(3);
                String novelSynopsis = cursor.getString(4);
                int rating = cursor.getInt(5);
                String novelStatus = cursor.getString(6);

                Novel novel = new Novel(id, novelTitle, novelAuthor, novelGenre, novelSynopsis, rating, novelStatus);
                novels.add(novel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return novels;
    }
}
