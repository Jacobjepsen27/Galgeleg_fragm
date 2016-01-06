package jepsen.dk.galgeleg_fragm;

import android.app.Application;

/**
 * Created by JacobWorckJepsen on 06/01/16.
 */
public class SingleTon extends Application{
    private static SingleTon ourInstance = new SingleTon();

    public static SingleTon getInstance() {
        return ourInstance;
    }

    public void onCreate(){
        super.onCreate();

    }
}
