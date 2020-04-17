package com.glisten.discount.shopping.Domain;



import javax.persistence.*;

@Table(name = "DiscountShopping.t_commodity_type")
public class TCommodityType {

    @Override
    public String toString() {
        return "TCommodityType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", typeOrder=" + typeOrder +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "type_order")
    private Long typeOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getTypeOrder() {
        return typeOrder;
    }

    public void setTypeOrder(Long typeOrder) {
        this.typeOrder = typeOrder;
    }



}
