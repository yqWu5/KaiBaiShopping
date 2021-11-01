package com.kaibai.shopping.pojo;

import java.io.Serializable;

public class ShoppingCode implements Serializable {
    private String id;

    private String codetype;

    private String codename;

    private String codevlaue;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCodetype() {
        return codetype;
    }

    public void setCodetype(String codetype) {
        this.codetype = codetype == null ? null : codetype.trim();
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename == null ? null : codename.trim();
    }

    public String getCodevlaue() {
        return codevlaue;
    }

    public void setCodevlaue(String codevlaue) {
        this.codevlaue = codevlaue == null ? null : codevlaue.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", codetype=").append(codetype);
        sb.append(", codename=").append(codename);
        sb.append(", codevlaue=").append(codevlaue);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}