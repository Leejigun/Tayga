package com.dopy.dopy.tayga.model.game;

/**
 * Created by Dopy on 2017-08-13.
 */

public class GameItem {
    public static final String HEARTHSTONE = "하스스톤";
    public static final String BATTLE_GROUND = "배틀 그라운드";
    public static final String OVERWATCH = "오버워치";
    public static final String LOL = "리그 오브 레전드";
    public static final String HOS = "히어로즈 오브 더 스톰";
    public static final String DOTA2 = "도타2";
    public static final String MINECRAFT = "마인 크래프트";
    public static final String WOW = "월드 오브 워크레프트";






    String tag;

    public GameItem(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
