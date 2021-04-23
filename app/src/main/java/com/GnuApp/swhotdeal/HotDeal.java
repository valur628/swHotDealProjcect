package com.GnuApp.swhotdeal;

// 리사이클러 뷰 사용하기 위한 데이터, get
public class HotDeal {
    private int loadNumber; // 갱신순서
    private String swName; // 소프트웨어 명
    private String devName; // 개발사 명
    private String disPeriod; // 할인 기간
    private String currency; // 통화
    private int cost; // 원가
    private int disPrice; // 할인가
    // private int disRate = disPrice / cost * 100; // 할인율
    private String platAddress; // 플랫폼 주소
    private String platName; // 플랫폼 이름
    private String repPicture; // 대표사진
    private String othPicture; // 기타사진

    public HotDeal() {}

    public String getSWName() { return swName; }
    public void setSWName(String swName) { this.swName = swName; }

    public String getDevName() { return devName; }
    public void setDevName(String devName) { this.devName = devName; }

    public String getDisPeriod() { return disPeriod; }
    public void setDisPeriod(String disPeriod) { this.disPeriod = disPeriod; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }

    public int getDisPrice() { return disPrice; }
    public void setDisPrice(int disPrice) { this.disPrice = disPrice; }

    public int getDisRate() { return (disPrice / cost * 100); } // 할인율 반환

    public String getPlatAddress() { return platAddress; }
    public void setPlatAddress(String platAddress) { this.platAddress = platAddress; }

    public String getPlatName() { return platName; }
    public void setPlatName(String platName) { this.platName = platName; }

    public String getRepPicture() { return repPicture; }
    public void setRepPicture(String repPicture) { this.repPicture = repPicture; }

    public String getOthPicture() { return othPicture; }
    public void setOthPicture(String othPicture) { this.othPicture = othPicture; }
}
