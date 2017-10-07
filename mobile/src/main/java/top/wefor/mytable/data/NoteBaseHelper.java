package top.wefor.mytable.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created on 2017/9/25.
 *
 * @author ice
 */

public class NoteBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "noteBase.db";

    public NoteBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

//    public static final class Cols {
//        public static final String UUID = "uuid";
//        public static final String TITLE = "title";
//        public static final String CONTENT = "content";
//        public static final String ADD_DATE = "add_date";
//        public static final String LAST_EDIT_DATE = "last_edit_date";
//        public static final String FROM = "out_source";
//        public static final String DONE = "done";
//        public static final String TYPE = "type";
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NoteDbSchema.NoteTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                NoteDbSchema.NoteTable.Cols.UUID + ", " +
                NoteDbSchema.NoteTable.Cols.TITLE + ", " +
                NoteDbSchema.NoteTable.Cols.CONTENT + ", " +
                NoteDbSchema.NoteTable.Cols.ADD_DATE + ", " +
                NoteDbSchema.NoteTable.Cols.LAST_EDIT_DATE + ", " +
                NoteDbSchema.NoteTable.Cols.FROM + ", " +
                NoteDbSchema.NoteTable.Cols.DONE + ", " +
                NoteDbSchema.NoteTable.Cols.TYPE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
