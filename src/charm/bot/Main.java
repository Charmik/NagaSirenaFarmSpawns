package charm.bot;

import java.awt.*;
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException, InterruptedException, AWTException {

        GlobalKeyListener t = new GlobalKeyListener();
        t.start();

        Object object = new Object();

        for (; ; ) {
            synchronized (object) {
                object.wait();
            }
        }
        //Runtime.getRuntime().exec("/home/charm/Desktop/wow.sh").waitFor();
    }
}


