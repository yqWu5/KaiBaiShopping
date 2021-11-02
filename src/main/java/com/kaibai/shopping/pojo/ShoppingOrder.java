package com.kaibai.shopping.pojo;

import java.io.Serializable;
import java.util.Date;

public class ShoppingOrder implements Serializable {
    private String id;

    private Float orderprice;

    private String orderstatus;

    private String orderstatusdes;

    private Date ordercreatetime;

    private String orderdestination;

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

    public Float getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(Float orderprice) {
        this.orderprice = orderprice;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus == null ? null : orderstatus.trim();
    }

    public String getOrderstatusdes() {
        return orderstatusdes;
    }

    public void setOrderstatusdes(String orderstatusdes) {
        this.orderstatusdes = orderstatusdes == null ? null : orderstatusdes.trim();
    }

    public Date getOrdercreatetime() {
        return ordercreatetime;
    }

    public void setOrdercreatetime(Date ordercreatetime) {
        this.ordercreatetime = ordercreatetime;
    }

    public String getOrderdestination() {
        return orderdestination;
    }

    public void setOrderdestination(String orderdestination) {
        this.orderdestination = orderdestination == null ? null : orderdestination.trim();
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
        sb.append(", orderprice=").append(orderprice);
        sb.append(", orderstatus=").append(orderstatus);
        sb.append(", orderstatusdes=").append(orderstatusdes);
        sb.append(", ordercreatetime=").append(ordercreatetime);
        sb.append(", orderdestination=").append(orderdestination);
        sb.append(", maketime=").append(maketime);
        sb.append(", modifytime=").append(modifytime);
        sb.append(", operator=").append(operator);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}