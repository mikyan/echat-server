package cn.mikyan.pojo;

import javax.persistence.*;

public class Groups {
    @Id
    private String id;

    private String groupname;

    private String faceimage;

    @Column(name = "faceimage_big")
    private String faceimageBig;

    private String qrcode;

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
     * @return groupname
     */
    public String getGroupname() {
        return groupname;
    }

    /**
     * @param groupname
     */
    public void setGroupname(String groupname) {
        this.groupname = groupname;
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
}