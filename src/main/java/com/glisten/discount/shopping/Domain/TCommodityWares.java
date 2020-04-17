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

    @Override
    public String toString() {
        return "TCommodityWares{" +
                "id=" + id +
                ", waresName='" + waresName + '\'' +
                ", itemId=" + itemId +
                ", waresPrice=" + waresPrice +
                ", waresRemarks='" + waresRemarks + '\'' +
                ", waresImg='" + waresImg + '\'' +
                ", waresState=" + waresState +
                '}';
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
