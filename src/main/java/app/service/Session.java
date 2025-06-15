package app.service;

import app.model.*;

/**
 * Session class to manage the current state of the application.
 * It provides methods to set and retrieve object representing the state, as well as to check if they are initialized.
 */
public class Session {
    private static UserModel user = null;
    private static PromotionModel promotion = null;
    private static OutletModel outlet = null;
    private static DeliveryModel delivery = null;
    private static ProductModel product = null;
    private static RewardModel reward = null;

    public static void endSession(){
        user = null;
        promotion = null;
        outlet = null;
        delivery = null;
        product = null;
        reward = null;
    }

    public static void clearNonUserData() {
        promotion = null;
        outlet = null;
        delivery = null;
        reward = null;
        product = null;
    }

    public static void setUser(UserModel user) {
        Session.user = user;
    }

    public static UserModel getUser() {
        return user;
    }

    public static boolean isUserInitialized(){
        return user != null;
    }

    public static void setUserNull() {
        user = null;
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

    public static void setDelivery(DeliveryModel delivery) {
        Session.delivery = delivery;
    }

    public static DeliveryModel getDelivery() {
        return delivery;
    }

    public static boolean isDeliveryInitialized() {
        return delivery != null;
    }

    public static void setDeliveryNull() {
        delivery = null;
    }

    public static boolean isProductInitialized() {return product != null;}

    public static void setProduct(ProductModel product){Session.product = product;}

    public static ProductModel getProduct() {return product;}

    public static void setProductNull() {product = null;}

    public static void setReward(RewardModel reward) {
        Session.reward = reward;
    }

    public static RewardModel getReward() {
        return reward;
    }

    public static void setRewardNull(){
        reward=null;
    }
    public static boolean isRewardInitialized(){
        return reward != null;
    }
}
