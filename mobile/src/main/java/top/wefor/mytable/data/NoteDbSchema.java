package top.wefor.mytable.data;

/**
 * Created on 2017/10/6.
 *
 * @author ice
 */

public class NoteDbSchema {

//    private String mContent;

//    private Date mAddDate;
//
//    private Date mLastEditDate;
//
//    private boolean mOutSource;
//
//    private boolean mDone;
//
//    private int mType;

    public static final class NoteTable {
        public static final String NAME = "notes";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String CONTENT = "content";
            public static final String ADD_DATE = "add_date";
            public static final String LAST_EDIT_DATE = "last_edit_date";
            public static final String FROM = "out_from";
            public static final String DONE = "done";
            public static final String TYPE = "type";
        }
    }

}
