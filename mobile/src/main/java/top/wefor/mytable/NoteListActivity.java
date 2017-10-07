package top.wefor.mytable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import top.wefor.mytable.data.NoteEntity;
import top.wefor.mytable.data.NoteLab;

/**
 * Created on 2017/10/6.
 *
 * @author ice
 */

public class NoteListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private NoteAdapter mNoteAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<NoteEntity> noteList = NoteLab.get(getApplicationContext()).getNoteList();

        mNoteAdapter = new NoteAdapter(noteList);

        mRecyclerView.setAdapter(mNoteAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class NoteAdapter extends RecyclerView.Adapter<NoteHolder> {

        private List<NoteEntity> mNoteList;

        public NoteAdapter(List<NoteEntity> noteList) {
            mNoteList = noteList;
        }

        @Override
        public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(NoteListActivity.this);
            View view = layoutInflater.inflate(R.layout.item_note_list, parent, false);
            return new NoteHolder(view);
        }

        @Override
        public void onBindViewHolder(NoteHolder holder, int position) {
            NoteEntity noteEntity = mNoteList.get(position);
            holder.bindNote(noteEntity);
        }

        @Override
        public int getItemCount() {
            return mNoteList.size();
        }
    }

    public class NoteHolder extends RecyclerView.ViewHolder {

        TextView mTitleTv, mContentTv, mFromTv, mDateTv;

        public NoteHolder(View itemView) {
            super(itemView);
            mTitleTv = (TextView) itemView.findViewById(R.id.title_tv);
            mContentTv = (TextView) itemView.findViewById(R.id.content_tv);
            mFromTv = (TextView) itemView.findViewById(R.id.from_tv);
            mDateTv = (TextView) itemView.findViewById(R.id.date_tv);
        }

        public void bindNote(NoteEntity noteEntity) {
            mTitleTv.setText(noteEntity.getTitle());
            mContentTv.setText(noteEntity.getContent());
            mFromTv.setText(noteEntity.getFrom());
            SimpleDateFormat sd = new SimpleDateFormat(DATE_FORMAT, Locale.CHINA);
            String dateStr = sd.format(noteEntity.getAddDate());
            mDateTv.setText(dateStr);
        }
    }

    public String pass(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            return "";
        }
        return charSequence.toString();
    }

    private static final String DATE_FORMAT = "MM月dd HH:mm";

}
