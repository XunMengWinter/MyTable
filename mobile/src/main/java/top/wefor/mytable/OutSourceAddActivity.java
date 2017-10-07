package top.wefor.mytable;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import top.wefor.mytable.data.NoteEntity;
import top.wefor.mytable.data.NoteLab;

/**
 * Created on 2017/10/7.
 *
 * @author ice
 * @GitHub https://github.com/XunMengWinter
 */

public class OutSourceAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent data = getIntent();
        if (data == null) {
            finish();
            return;
        }

        // 将来源数据转化成 NoteEntity
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setTitle(data.getStringExtra(NoteCols.TITLE));
        noteEntity.setContent(data.getStringExtra(NoteCols.CONTENT));
        noteEntity.setFrom(data.getStringExtra(NoteCols.FROM));
        noteEntity.setType(data.getIntExtra(NoteCols.TYPE, 0));

        // 将数据保存至数据库
        NoteLab.get(getApplicationContext()).addNoteEntity(noteEntity);

        // 返回一些消息
        Intent intent = new Intent();
        intent.putExtra(NoteCols.RESULT_TYPE, NoteCols.RESULT_TYPE_SUCCESS);
        intent.putExtra(NoteCols.RESULT_MESSAGE, "我已经将你传来的数据收下啦^^");
        setResult(RESULT_OK, intent);

        // 退出当前Activity
        finish();
    }

    public static final class NoteCols {
        //        public static final String UUID = "uuid";
        public static final String TITLE = "title";
        public static final String CONTENT = "content";
        //        public static final String ADD_DATE = "add_date";
//        public static final String LAST_EDIT_DATE = "last_edit_date";
        public static final String FROM = "from";
        //        public static final String DONE = "done";
        public static final String TYPE = "type";

        public static final String RESULT_TYPE = "result_type";
        public static final String RESULT_MESSAGE = "result_message";
        public static final int RESULT_TYPE_SUCCESS = 1;

        public static final int TYPE_NOW = 7;
    }


}
