package com.qianyi.shine.ui.career_planning.entity;

/**
 * Created by Administrator on 2018/4/2.
 */

public class SuitableForMeEntity {
    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getProfessionPrice() {
        return professionPrice;
    }

    public void setProfessionPrice(String professionPrice) {
        this.professionPrice = professionPrice;
    }

    public String getProfessionDirection() {
        return professionDirection;
    }

    public void setProfessionDirection(String professionDirection) {
        this.professionDirection = professionDirection;
    }

    public SuitableForMeEntity(String professionName, String professionPrice, String professionDirection) {

        this.professionName = professionName;
        this.professionPrice = professionPrice;
        this.professionDirection = professionDirection;
    }

    private String professionName;
    private String professionPrice;
    private String professionDirection;
}
