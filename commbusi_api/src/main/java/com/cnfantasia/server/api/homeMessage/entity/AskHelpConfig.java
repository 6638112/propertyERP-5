package com.cnfantasia.server.api.homeMessage.entity;

/**
 * @ClassName: AskHelpConfig
 * @Date: 2017-04-10 13:14
 * @Auther: kangduo
 * @Description: ()
 */
public class AskHelpConfig {
    private boolean searchHelpOpen;
    private String placeholdOut;
    private String placeholdIn;

    public boolean isSearchHelpOpen() {
        return searchHelpOpen;
    }

    public void setSearchHelpOpen(boolean searchHelpOpen) {
        this.searchHelpOpen = searchHelpOpen;
    }

    public String getPlaceholdOut() {
        return placeholdOut;
    }

    public void setPlaceholdOut(String placeholdOut) {
        this.placeholdOut = placeholdOut;
    }

    public String getPlaceholdIn() {
        return placeholdIn;
    }

    public void setPlaceholdIn(String placeholdIn) {
        this.placeholdIn = placeholdIn;
    }
}
