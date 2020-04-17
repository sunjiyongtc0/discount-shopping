package com.glisten.discount.shopping.Domain;



import javax.persistence.*;

@Table(name = "DiscountShopping.t_commodity_category")
public class TCommodityCategory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "type_id")
    private Long typeId;
    @Column(name = "category_order")
    private Long categoryOrder;

    public Long getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(Long categoryOrder) {
        this.categoryOrder = categoryOrder;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }


    @Override
    public String toString() {
        return "TCommodityCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", typeId=" + typeId +
                ", categoryOrder=" + categoryOrder +
                '}';
    }
}
