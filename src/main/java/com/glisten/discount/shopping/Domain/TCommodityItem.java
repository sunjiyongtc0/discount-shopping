package com.glisten.discount.shopping.Domain;



import javax.persistence.*;

@Table(name = "DiscountShopping.t_commodity_item")
public class TCommodityItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name")
    private String itemName;



    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "item_order")
    private Long itemOrder;
    @Override
    public String toString() {
        return "TCommodityItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", categoryId=" + categoryId +
                ", itemOrder=" + itemOrder +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Long itemOrder) {
        this.itemOrder = itemOrder;
    }


}
