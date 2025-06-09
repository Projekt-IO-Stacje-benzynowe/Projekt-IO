package app.service;

import app.model.OutletModel;
import app.model.PromotionModel;
import app.model.UserModel;
import app.service.branch_panel.delivery_section.Delivery;

//stores logged user data
public class Session {
    public static UserModel user = null;
    private static PromotionModel promotion = null;
    private static OutletModel outlet = null;
    private static Delivery delivery = null;
    
    public static void endSession(){
        user = null;
        promotion = null;
        outlet = null;
        delivery = null;
    }

    public static boolean isUserInitialized(){
        return user != null;
    }

    public static void setPromotion(PromotionModel promotion) {
        Session.promotion = promotion;
    }

    public static PromotionModel getPromotion() {
        return promotion;
    }

    public static boolean isPromotionInitialized() {
        return promotion != null;
    }

    public static void setPromotionNull() {
        promotion = null;
    }

    public static void setOutlet(OutletModel outlet) {
        Session.outlet = outlet;
    }

    public static OutletModel getOutlet() {
        return outlet;
    }

    public static boolean isOutletInitialized() {
        return outlet != null;
    }

    public static void setOutletNull() {
        outlet = null;
    }

    public static void setDelivery(Delivery delivery) {
        Session.delivery = delivery;
    }

    public static Delivery getDelivery() {
        return delivery;
    }

    public static boolean isDeliveryInitialized() {
        return delivery != null;
    }

    public static void setDeliveryNull() {
        delivery = null;
    }

}
