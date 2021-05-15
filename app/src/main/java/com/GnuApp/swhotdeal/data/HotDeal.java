package com.GnuApp.swhotdeal.data;

import com.google.firebase.firestore.PropertyName;

// 리사이클러 뷰 사용하기 위한 데이터, get
public class HotDeal {
    private int loadNumber; // 갱신순서
    @PropertyName("DB_SWName")
    private String swName; // 소프트웨어 명
    @PropertyName("DB_DevName")
    private String devName; // 개발사 명, 사용안함
    @PropertyName("DB_DisPeriod")
    private int disPeriod; // 할인 기간
    @PropertyName("DB_Currency")
    private String currency; // 통화
    @PropertyName("DB_Cost")
    private float cost; // 원가
    @PropertyName("DB_DisPrice")
    private float disPrice; // 할인가
    @PropertyName("DB_DisRate")
    private float disRate; // 할인율
    @PropertyName("DB_PlatAddress")
    private String platAddress; // 플랫폼 주소, 판매처 링크
    @PropertyName("DB_PlatName")
    private String platName; // 플랫폼 이름
    @PropertyName("DB_RepPicture")
    private String repPicture; // 대표사진
    @PropertyName("DB_OthPicture")
    private String othPicture; // 기타사진

    public HotDeal() {
        setDivide100();
    }
    // 파이어베이스에서 받아온 데이터 객체로 바꿀때 사용.

    public HotDeal(String swName, int disPeriod, int cost, int disPrice, int disRate, String platAddress, String platName, String repPicture, String othPicture) {
        this.setSWName(swName);
        this.setDisPeriod(disPeriod);
        this.setCost(cost);
        this.setDisPrice(disPrice);
        this.setDisRate(disRate);
        this.setPlatAddress(platAddress);
        this.setPlatName(platName);
        this.setRepPicture(repPicture);
        this.setOthPicture(othPicture);
    }

//    public HotDeal(int DB_Cost, String DB_Currency, String DB_DevName, int DB_DisPeriod, int DB_DisPrice, int DB_DisRate, int DB_LoadNumber, String DB_OthPicture, String DB_PlatAddress, String DB_PlatName, String DB_RepPicture, String DB_SWName){
//        this.setCost((DB_Cost / 100));
//        this.setCurrency(DB_Currency);
//        this.setDisPeriod(DB_DisPeriod);
//        this.setDisPrice((DB_DisPrice / 100));
//        this.setDisRate(DB_DisRate);
//        this.setOthPicture(DB_OthPicture);
//        this.setPlatAddress(DB_PlatAddress);
//        this.setPlatName(DB_PlatName);
//        this.setRepPicture(DB_RepPicture);
//        this.setSWName(DB_SWName);
//    }

    public void setDivide100() {
        setCost(this.cost);
        setDisPrice(this.disPrice);
        setDisRate(this.disRate);
    }

    @PropertyName("DB_SWName")
    public String getSWName() { return swName; }
    public void setSWName(String swName) { this.swName = swName; }

    @PropertyName("DB_DevName")
    public String getDevName() { return devName; }
    public void setDevName(String devName) { this.devName = devName; }
//    21. 4. 27. 회의 devname 제외

    @PropertyName("DB_DisPeriod")
    public int getDisPeriod() { return disPeriod; }
    public void setDisPeriod(int disPeriod) { this.disPeriod = disPeriod; }

    @PropertyName("DB_Currency")
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    @PropertyName("DB_Cost")
    public String getCost() { return String.valueOf(cost); }
    public void setCost(float cost) { this.cost = cost / 100; }

    @PropertyName("DB_DisPrice")
    public String getDisPrice() { return String.valueOf(disPrice); }
    public void setDisPrice(float disPrice) { this.disPrice = disPrice / 100; }

    @PropertyName("DB_DisRate")
    public String getDisRate() { return String.valueOf(disRate); }
    public void setDisRate(float disRate) { this.disRate = disRate / 100; }
    // 할인율 서버에서 받아옴

    @PropertyName("DB_PlatAddress")
    public String getPlatAddress() { return platAddress; }
    public void setPlatAddress(String platAddress) { this.platAddress = platAddress; }

    @PropertyName("DB_PlatName")
    public String getPlatName() { return platName; }
    public void setPlatName(String platName) { this.platName = platName; }

    @PropertyName("DB_RepPicture")
    public String getRepPicture() { return repPicture; }
    public void setRepPicture(String repPicture) { this.repPicture = repPicture; }

    @PropertyName("DB_OthPicture")
    public String getOthPicture() { return othPicture; }
    public void setOthPicture(String othPicture) { this.othPicture = othPicture; }
}
