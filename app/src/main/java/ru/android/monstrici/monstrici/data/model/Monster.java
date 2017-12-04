package ru.android.monstrici.monstrici.data.model;

/**
 * Created by elisium
 *
 * @Date 28/11/2017
 * @Author Andrei Gusev
 */

public class Monster implements Cloneable {
    private String mId;
    private String mName;
    private String mBody;
    private String mEye;
    private String mMouth;

    public Monster() {
    }

    public Monster(String id) {
        mId = id;
    }

    public Monster(String id, String name, String body, String eye, String mouth) {
        mId = id;
        mName = name;
        mBody = body;
        mEye = eye;
        mMouth = mouth;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public String getEye() {
        return mEye;
    }

    public void setEye(String eye) {
        mEye = eye;
    }

    public String getMouth() {
        return mMouth;
    }

    public void setMouth(String mouth) {
        mMouth = mouth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Monster monster = (Monster) o;

        if (mId != null ? !mId.equals(monster.mId) : monster.mId != null) return false;
        if (mName != null ? !mName.equals(monster.mName) : monster.mName != null) return false;
        if (mBody != null ? !mBody.equals(monster.mBody) : monster.mBody != null) return false;
        if (mEye != null ? !mEye.equals(monster.mEye) : monster.mEye != null) return false;
        return mMouth != null ? mMouth.equals(monster.mMouth) : monster.mMouth == null;
    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mBody != null ? mBody.hashCode() : 0);
        result = 31 * result + (mEye != null ? mEye.hashCode() : 0);
        result = 31 * result + (mMouth != null ? mMouth.hashCode() : 0);
        return result;
    }

    @Override
    public Monster clone() {
        try {
            return (Monster) super.clone();
        } catch (final CloneNotSupportedException ex) {
            throw new AssertionError();
        }
    }
}
