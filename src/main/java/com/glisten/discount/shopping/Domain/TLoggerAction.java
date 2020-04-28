package com.glisten.discount.shopping.Domain;



import javax.persistence.*;

@Table(name = "DiscountShopping.t_logger_action")
public class TLoggerAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "host_ip")
    private String hostIp;

    @Column(name = "action_time")
    private String actionTime;

    @Column(name = "action_name")
    private String actionName;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getActionTime() {
        return actionTime;
    }

    public void setActionTime(String actionTime) {
        this.actionTime = actionTime;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public String toString() {
        return "TLoggerAction{" +
                "id=" + id +
                ", hostIp='" + hostIp + '\'' +
                ", actionTime='" + actionTime + '\'' +
                ", actionName='" + actionName + '\'' +
                '}';
    }
}
