package top.wefor.mytable.data;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

/**
 * Created on 2017/9/25.
 *
 * @author ice
 */

public class NoteCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

//    public static final class Cols {
//        public static final String UUID = "uuid";
//        public static final String TITLE = "title";
//        public static final String CONTENT = "content";
//        public static final String ADD_DATE = "add_date";
//        public static final String LAST_EDIT_DATE = "last_edit_date";
//        public static final String FROM = "from";
//        public static final String DONE = "done";
//        public static final String TYPE = "type";
//    }

    public NoteEntity getNoteEntity() {
        String uuidString = getString(getColumnIndex(NoteDbSchema.NoteTable.Cols.UUID));
        String title = getString(getColumnIndex(NoteDbSchema.NoteTable.Cols.TITLE));
        String content = getString(getColumnIndex(NoteDbSchema.NoteTable.Cols.CONTENT));
        long addDate = getLong(getColumnIndex(NoteDbSchema.NoteTable.Cols.ADD_DATE));
        long lastEditDate = getLong(getColumnIndex(NoteDbSchema.NoteTable.Cols.LAST_EDIT_DATE));
        String from = getString(getColumnIndex(NoteDbSchema.NoteTable.Cols.FROM));
        int isDone = getInt(getColumnIndex(NoteDbSchema.NoteTable.Cols.FROM));
        int type = getInt(getColumnIndex(NoteDbSchema.NoteTable.Cols.TYPE));

        NoteEntity noteEntity = new NoteEntity(UUID.fromString(uuidString));
        noteEntity.setTitle(title);
        noteEntity.setContent(content);
        noteEntity.setAddDate(new Date(addDate));
        noteEntity.setLastEditDate(new Date(lastEditDate));
        noteEntity.setFrom(from);
        noteEntity.setDone(isDone != 0);
        noteEntity.setType(type);

        return noteEntity;
    }

}
