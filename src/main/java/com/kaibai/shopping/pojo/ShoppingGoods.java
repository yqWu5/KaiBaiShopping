package com.kaibai.shopping.pojo;

import java.io.Serializable;
import java.util.Date;

public class ShoppingGoods implements Serializable {
    private String id;

    private String goodsname;

    private Float goodsprice;

    private String goodstypeid;

    private String goodstype;

    private Integer goodsamount;

    private Boolean goodsstatus;

    private Date maketime;

    private Date modifytime;

    private String operator;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public Float getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(Float goodsprice) {
        this.goodsprice = goodsprice;
    }

    public String getGoodstypeid() {
        return goodstypeid;
    }

    public void setGoodstypeid(String goodstypeid) {
        this.goodstypeid = goodstypeid == null ? null : goodstypeid.trim();
    }

    public String getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(String goodstype) {
        this.goodstype = goodstype == null ? null : goodstype.trim();
    }

    public Integer getGoodsamount() {
        return goodsamount;
    }

    public void setGoodsamount(Integer goodsamount) {
        this.goodsamount = goodsamount;
    }

    public Boolean getGoodsstatus() {
        return goodsstatus;
    }

    public void setGoodsstatus(Boolean goodsstatus) {
        this.goodsstatus = goodsstatus;
    }

    public Date getMaketime() {
        return maketime;
    }

    public void setMaketime(Date maketime) {
        this.maketime = maketime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", goodsname=").append(goodsname);
        sb.append(", goodsprice=").append(goodsprice);
        sb.append(", goodstypeid=").append(goodstypeid);
        sb.append(", goodstype=").append(goodstype);
        sb.append(", goodsamount=").append(goodsamount);
        sb.append(", goodsstatus=").append(goodsstatus);
        sb.append(", maketime=").append(maketime);
        sb.append(", modifytime=").append(modifytime);
        sb.append(", operator=").append(operator);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}