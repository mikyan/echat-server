package cn.mikyan.pojo;

import javax.persistence.*;

public class Users {
    @Id
    private String id;

    private String username;

    private String password;

    private String faceimage;

    @Column(name = "faceimage_big")
    private String faceimageBig;

    @Column(name = "nick_name")
    private String nickName;

    private String qrcode;

    private String cid;

    private String appid;

    private String appkey;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return faceimage
     */
    public String getFaceimage() {
        return faceimage;
    }

    /**
     * @param faceimage
     */
    public void setFaceimage(String faceimage) {
        this.faceimage = faceimage;
    }

    /**
     * @return faceimage_big
     */
    public String getFaceimageBig() {
        return faceimageBig;
    }

    /**
     * @param faceimageBig
     */
    public void setFaceimageBig(String faceimageBig) {
        this.faceimageBig = faceimageBig;
    }

    /**
     * @return nick_name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return qrcode
     */
    public String getQrcode() {
        return qrcode;
    }

    /**
     * @param qrcode
     */
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    /**
     * @return cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * @param cid
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * @return appid
     */
    public String getAppid() {
        return appid;
    }

    /**
     * @param appid
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * @return appkey
     */
    public String getAppkey() {
        return appkey;
    }

    /**
     * @param appkey
     */
    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
}