package com.kaibai.shopping.pojo;

import java.io.Serializable;
import java.util.Date;

public class ShoppingTransportTrace implements Serializable {
    private String id;

    private String orderid;

    private Date arrivetime;

    private String arrivedes;

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

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Date getArrivetime() {
        return arrivetime;
    }

    public void setArrivetime(Date arrivetime) {
        this.arrivetime = arrivetime;
    }

    public String getArrivedes() {
        return arrivedes;
    }

    public void setArrivedes(String arrivedes) {
        this.arrivedes = arrivedes == null ? null : arrivedes.trim();
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
        sb.append(", orderid=").append(orderid);
        sb.append(", arrivetime=").append(arrivetime);
        sb.append(", arrivedes=").append(arrivedes);
        sb.append(", maketime=").append(maketime);
        sb.append(", modifytime=").append(modifytime);
        sb.append(", operator=").append(operator);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}