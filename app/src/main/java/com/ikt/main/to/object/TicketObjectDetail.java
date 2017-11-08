package com.ikt.main.to.object;

/**
 * Created by Arifin on 4/10/16.
 */
public class TicketObjectDetail {

    // LAMA
//            "data": [{
//                "CARRIER": "PT. DUNIA EXPRESS TRANSINDO",
//                        "DRIVER": "dfg",
//                        "TRUCK": "B9003WV",
//                        "OUTGOING_VOYAGE_NR": null, del
//                        "OUTGOING_VESSEL": null, del
//                        "TIME_BEGIN": "30-MAR-2016 13:00",
//                        "TIME_END": "30-MAR-2016 17:00",
//                        "LOADAREA_IN": "CD005",
//                        "LOADAREA_OUT": null,
//                        "AMOUNT_CAR_PICKUP": "0",
//                        "AMOUNT_CAR_VESSEL": "9"
//            }],
    // BARU
//            "data":{
//                "CARRIER":"PT. DUNIA EXPRESS TRANSINDO",
//                        "DRIVER":"Pardede",
//                        "LICENSEPLATE":"B 9015 WV", x
//                        "TRUCK":"B9015WV",
//                        "TIME_BEGIN":"27-OCT-2017 08:00",
//                        "TIME_END":"27-OCT-2017 16:00",
//                        "LOADAREA_IN":null,
//                        "LOADAREA_OUT":"Loding Area E",
//                        "AMOUNT_CAR_PICKUP":"91",
//                        "AMOUNT_CAR_VESSEL":"0",
//                        "INFO":"Truk sudah Gate In di Terminal IKT pada 01-NOV-2017 14:36", x
//                        "DELETABLE":false, x
//                        "NEED_ASSIGN":false x
//                        "PHONE":"123123123"

    private String carrier;
    private String driverName;
    private String platNo;
    private String outGoingVoyage;
    private String outGoingVessel;
    private String begin;
    private String end;
    private String areaIn;
    private String areaOut;
    private String amountCarPickup;
    private String amountCarVessel;
    private String licenseplate;
    private String info;
    private boolean deletable;
    private boolean need_assign;
    private String phone;
    //belum ada api nya
    private boolean editable;

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPlatNo() {
        return platNo;
    }

    public void setPlatNo(String platNo) {
        this.platNo = platNo;
    }

    public String getOutGoingVoyage() {
        return outGoingVoyage;
    }

    public void setOutGoingVoyage(String outGoingVoyage) {
        this.outGoingVoyage = outGoingVoyage;
    }

    public String getOutGoingVessel() {
        return outGoingVessel;
    }

    public void setOutGoingVessel(String outGoingVessel) {
        this.outGoingVessel = outGoingVessel;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getAreaIn() {
        return areaIn;
    }

    public void setAreaIn(String areaIn) {
        this.areaIn = areaIn;
    }

    public String getAreaOut() {
        return areaOut;
    }

    public void setAreaOut(String areaOut) {
        this.areaOut = areaOut;
    }

    public String getAmountCarPickup() {
        return amountCarPickup;
    }

    public void setAmountCarPickup(String amountCarPickup) {
        this.amountCarPickup = amountCarPickup;
    }

    public String getAmountCarVessel() {
        return amountCarVessel;
    }

    public void setAmountCarVessel(String amountCarVessel) {
        this.amountCarVessel = amountCarVessel;
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public boolean isNeed_assign() {
        return need_assign;
    }

    public void setNeed_assign(boolean need_assign) {
        this.need_assign = need_assign;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
