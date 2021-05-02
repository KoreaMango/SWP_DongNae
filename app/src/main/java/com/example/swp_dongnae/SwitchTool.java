package com.example.swp_dongnae;

public class SwitchTool {
    public String switchClub(int i) {

        String item;

        switch (i) {
            case 0:
                item = "공연 분과";
                break;
            case 1:
                item = "그림 분과";
                break;
            case 2:
                item = "국제 분과";
                break;
            case 3:
                item = "무예 분과";
                break;
            case 4:
                item = "문학 분과";
                break;
            case 5:
                item = "봉사 분과";
                break;
            case 6:
                item = "생활,문화 분과";
                break;
            case 7:
                item = "외국어 분과";
                break;
            case 8:
                item = "종교 분과";
                break;
            case 9:
                item = "창업 분과";
                break;
            case 10:
                item = "체육 분과";
                break;
            case 11:
                item = "학술 분과";
                break;
            default:
                item = null;
                break;
        }
        return item;
    }
}
