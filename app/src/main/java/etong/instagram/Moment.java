package etong.instagram;

/**
 * Created by Administrator on 2014/9/17.
 */
public class Moment {

    private String userName;

    private String avatar;

    private String picture;

    private String str;

    private int favorNumber;

    private int commentNumber;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getFavorNumber() {
        return favorNumber;
    }

    public void setFavorNumber(int favorNumber) {
        this.favorNumber = favorNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }
}
