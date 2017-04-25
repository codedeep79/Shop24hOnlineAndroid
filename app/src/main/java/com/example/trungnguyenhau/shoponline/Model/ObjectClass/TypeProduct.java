package com.example.trungnguyenhau.shoponline.Model.ObjectClass;

import java.util.List;

/**
 * Created by TRUNGNGUYENHAU on 4/21/2017.
 */

public class TypeProduct {
    public int getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public int getMALOAICHA() {
        return MALOAICHA;
    }

    public void setMALOAICHA(int MALOAICHA) {
        this.MALOAICHA = MALOAICHA;
    }

    public String getTENLOAISP() {
        return TENLOAISP;
    }

    public void setTENLOAISP(String TENLOAISP) {
        this.TENLOAISP = TENLOAISP;
    }

    public List<TypeProduct> getListCon() {
        return listCon;
    }

    public void setListCon(List<TypeProduct> listCon) {
        this.listCon = listCon;
    }

    int MALOAISP,MALOAICHA;
    String TENLOAISP;
    List<TypeProduct> listCon;
}
