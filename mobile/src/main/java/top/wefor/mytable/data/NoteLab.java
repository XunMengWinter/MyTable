package top.wefor.mytable.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created on 2017/9/24.
 *
 * @author ice
 */

public class NoteLab {

    private static NoteLab sNoteLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static NoteLab get(Context context) {
        if (sNoteLab == null) {
            sNoteLab = new NoteLab(context);
        }
        return sNoteLab;
    }

    private NoteLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new NoteBaseHelper(mContext).getWritableDatabase();
    }

    public List<NoteEntity> getNoteList() {

        List<NoteEntity> noteEntitys = new ArrayList<>();
        NoteCursorWrapper cursorWrapper = queryNoteEntitys(null, null);

        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                noteEntitys.add(cursorWrapper.getNoteEntity());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return noteEntitys;
    }

    public NoteEntity getNoteEntity(UUID id) {
        NoteCursorWrapper cursor = queryNoteEntitys(
                NoteDbSchema.NoteTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getNoteEntity();
        } finally {
            cursor.close();
        }
    }

    public void addNoteEntity(NoteEntity noteEntity) {
        ContentValues values = getContentValues(noteEntity);

        mDatabase.insert(NoteDbSchema.NoteTable.NAME, null, values);
    }

    public void updateNoteEntity(NoteEntity noteEntity) {
        String uuidString = noteEntity.getId().toString();
        ContentValues values = getContentValues(noteEntity);

        mDatabase.update(NoteDbSchema.NoteTable.NAME, values,
                NoteDbSchema.NoteTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    /*通过索引删除NoteEntity*/
    public void deleteNoteEntity(int index) {
        mDatabase.delete(NoteDbSchema.NoteTable.NAME,
                NoteDbSchema.NoteTable.Cols.UUID + " = ?",
                new String[]{getNoteList().get(index).getId().toString()});
        getNoteList().remove(index);
    }

    public void deleteNoteEntity(NoteEntity noteEntity) {
        mDatabase.delete(NoteDbSchema.NoteTable.NAME,
                NoteDbSchema.NoteTable.Cols.UUID + " = ?",
                new String[]{noteEntity.getId().toString()});
        getNoteList().remove(noteEntity);
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


    /*将NoteEntity转化程ContentValues以便于写入数据库*/
    private static ContentValues getContentValues(NoteEntity noteEntity) {
        ContentValues values = new ContentValues();
        if (noteEntity.getLastEditDate() == null) {
            noteEntity.setLastEditDate(noteEntity.getAddDate());
        }
        values.put(NoteDbSchema.NoteTable.Cols.UUID, noteEntity.getId().toString());
        values.put(NoteDbSchema.NoteTable.Cols.TITLE, noteEntity.getTitle());
        values.put(NoteDbSchema.NoteTable.Cols.CONTENT, noteEntity.getContent());
        values.put(NoteDbSchema.NoteTable.Cols.ADD_DATE, noteEntity.getAddDate().toString());
        values.put(NoteDbSchema.NoteTable.Cols.LAST_EDIT_DATE, noteEntity.getLastEditDate().toString());
        values.put(NoteDbSchema.NoteTable.Cols.FROM, noteEntity.getFrom());
        values.put(NoteDbSchema.NoteTable.Cols.DONE, noteEntity.isDone() ? 1 : 0);
        values.put(NoteDbSchema.NoteTable.Cols.TYPE, noteEntity.getType());

        return values;
    }


    private NoteCursorWrapper queryNoteEntitys(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                NoteDbSchema.NoteTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, //groupBy
                null, //having
                "_id desc" //orderBy
        );
        return new NoteCursorWrapper(cursor);
    }

    public File getPhotoFile(NoteEntity noteEntity) {
        File externalFileDir = mContext
                .getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (externalFileDir == null) {
            return null;
        }

        return new File(externalFileDir, noteEntity.getPhotoFilename());
    }

}
