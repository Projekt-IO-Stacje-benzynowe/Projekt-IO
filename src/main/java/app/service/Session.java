package app.service;

import app.model.UserModel;

//stores logged user data
public class Session {
    public static UserModel User = null;
    public static void EndSession(){
        User = null;
    }


    public static boolean isUser(){
        if( User != null) return true;
        return false;
    }
}
