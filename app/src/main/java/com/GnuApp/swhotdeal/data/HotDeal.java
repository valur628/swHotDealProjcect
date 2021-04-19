package com.GnuApp.swhotdeal.data;

// 리사이클러 뷰 사용하기 위한 데이터, get
public class HotDeal {
    private int loadNumber; // 갱신순서
    private String swName; // 소프트웨어 명
    private String devName; // 개발사 명
    private String disPeriod; // 할인 기간
    private String currency; // 통화
    private int cost; // 원가
    private int disPrice; // 할인가
    // private int disRate = disPrice / cost * 100; // 할인률
    private String platAddress; // 플랫폼 주소
    private String platName; // 플랫폼 이름
    private String repPicture; // 대표사진
    private String othPicture; // 기타사진

    public String getSWName() { return swName; }

    public String getDevName() { return devName; }

    public String getDisPeriod() { return disPeriod; }

    public String getCurrency() { return currency; }

    public int getCost() { return cost; }

    public int getDisPrice() { return disPrice; }

    public int getDisRate() { return (disPrice / cost * 100); }

    public String getPlatAddress() { return platAddress; }

    public String getPlatName() { return platName; }

    public String getRepPicture() { return repPicture; }

    public String getOthPicture() { return othPicture; }
}
