package app.service;

import app.model.PromotionsModel;
import app.model.UserModel;

//stores logged user data
public class Session {
    public static UserModel User = null;
    private static PromotionsModel promotion = null;
    public static void EndSession(){
        User = null;
    }

    public static boolean isUser(){
        if( User != null) return true;
        return false;
    }

    public static void setPromotion(PromotionsModel promotion) {
        Session.promotion = promotion;
    }

    public static PromotionsModel getPromotion() {
        return promotion;
    }

    public static boolean isPromotionInitialized() {
        return promotion != null;
    }

    public static void setPromotionNull() {
        promotion = null;
    }

}
