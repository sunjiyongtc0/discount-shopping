package com.glisten.discount.shopping.Domain;



import javax.persistence.*;

@Table(name = "DiscountShopping.t_commodity_wares")
public class TCommodityWares {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wares_name")
    private String waresName;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "wares_price")
    private Long waresPrice;

    @Column(name = "wares_remarks")
    private String waresRemarks;

    @Column(name = "wares_img")
    private String waresImg;



    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "category_id")
    private Long categoryId;



    @Column(name = "wares_tagprice")
    private Long waresTagprice;
    @Column(name = "wares_offprice")
    private Long waresOffprice;
    @Column(name = "offi_state")
    private int offiState;
    @Column(name = "wares_exp")
    private String waresExp;

    @Override
    public String toString() {
        return "TCommodityWares{" +
                "id=" + id +
                ", waresName='" + waresName + '\'' +
                ", itemId=" + itemId +
                ", waresPrice=" + waresPrice +
                ", waresRemarks='" + waresRemarks + '\'' +
                ", waresImg='" + waresImg + '\'' +
                ", typeId=" + typeId +
                ", categoryId=" + categoryId +
                ", waresTagprice=" + waresTagprice +
                ", waresOffprice=" + waresOffprice +
                ", offiState=" + offiState +
                ", waresExp='" + waresExp + '\'' +
                ", waresState=" + waresState +
                '}';
    }

    public Long getWaresTagprice() {
        return waresTagprice;
    }

    public void setWaresTagprice(Long waresTagprice) {
        this.waresTagprice = waresTagprice;
    }

    public Long getWaresOffprice() {
        return waresOffprice;
    }

    public void setWaresOffprice(Long waresOffprice) {
        this.waresOffprice = waresOffprice;
    }

    public int getOffiState() {
        return offiState;
    }

    public void setOffiState(int offiState) {
        this.offiState = offiState;
    }

    public String getWaresExp() {
        return waresExp;
    }

    public void setWaresExp(String waresExp) {
        this.waresExp = waresExp;
    }


    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWaresName() {
        return waresName;
    }

    public void setWaresName(String waresName) {
        this.waresName = waresName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getWaresPrice() {
        return waresPrice;
    }

    public void setWaresPrice(Long waresPrice) {
        this.waresPrice = waresPrice;
    }

    public String getWaresRemarks() {
        return waresRemarks;
    }

    public void setWaresRemarks(String waresRemarks) {
        this.waresRemarks = waresRemarks;
    }

    public String getWaresImg() {
        return waresImg;
    }

    public void setWaresImg(String waresImg) {
        this.waresImg = waresImg;
    }

    public int getWaresState() {
        return waresState;
    }

    public void setWaresState(int waresState) {
        this.waresState = waresState;
    }

    @Column(name = "wares_state")
    private int waresState;


}
