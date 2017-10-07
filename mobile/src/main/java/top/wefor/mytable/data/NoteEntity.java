package top.wefor.mytable.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;

/**
 * Created on 2017/10/6.
 *
 * @author ice
 */

public class NoteEntity implements Parcelable {

    private UUID mId;

    private String mTitle;

    private String mContent;

    // 添加时间
    private Date mAddDate;

    // 最后编辑时间
    private Date mLastEditDate;

    // 来源。（默认null，即手动添加）
    private String mFrom;

    // 是否已完成。（预留字段）
    private boolean mDone;

    // 类型，以颜色区分，含义由用户自己理解。（默认0，即默认颜色）
    private int mType;


    public NoteEntity(){
        this(UUID.randomUUID());
    }

    public NoteEntity(UUID uuid) {
        mId = uuid;
        mAddDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public Date getAddDate() {
        return mAddDate;
    }

    public void setAddDate(Date addDate) {
        mAddDate = addDate;
    }

    public Date getLastEditDate() {
        return mLastEditDate;
    }

    public void setLastEditDate(Date lastEditDate) {
        mLastEditDate = lastEditDate;
    }

    public String getFrom() {
        return mFrom;
    }

    public void setFrom(String from) {
        mFrom = from;
    }

    public boolean isDone() {
        return mDone;
    }

    public void setDone(boolean done) {
        mDone = done;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }


    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.mId);
        dest.writeString(this.mTitle);
        dest.writeString(this.mContent);
        dest.writeLong(this.mAddDate != null ? this.mAddDate.getTime() : -1);
        dest.writeLong(this.mLastEditDate != null ? this.mLastEditDate.getTime() : -1);
        dest.writeString(this.mFrom);
        dest.writeByte(this.mDone ? (byte) 1 : (byte) 0);
        dest.writeInt(this.mType);
    }

    protected NoteEntity(Parcel in) {
        this.mId = (UUID) in.readSerializable();
        this.mTitle = in.readString();
        this.mContent = in.readString();
        long tmpMAddDate = in.readLong();
        this.mAddDate = tmpMAddDate == -1 ? null : new Date(tmpMAddDate);
        long tmpMLastEditDate = in.readLong();
        this.mLastEditDate = tmpMLastEditDate == -1 ? null : new Date(tmpMLastEditDate);
        this.mFrom = in.readString();
        this.mDone = in.readByte() != 0;
        this.mType = in.readInt();
    }

    public static final Creator<NoteEntity> CREATOR = new Creator<NoteEntity>() {
        @Override
        public NoteEntity createFromParcel(Parcel source) {
            return new NoteEntity(source);
        }

        @Override
        public NoteEntity[] newArray(int size) {
            return new NoteEntity[size];
        }
    };
}
