package charm.bot;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by charm on 2/19/15.
 */
public class GlobalKeyListener implements NativeKeyListener {

    private Robot robot;

    private ArrayList<Point> radiantLeft = new ArrayList<>();
    private ArrayList<Point> radiantRight = new ArrayList<>();
    private ArrayList<Point> radiantThreeLines = new ArrayList<>();
    private ArrayList<Point> direLeft = new ArrayList<>();
    private ArrayList<Point> direRight = new ArrayList<>();
    private ArrayList<Point> direThreeLines = new ArrayList<>();

    GlobalKeyListener() throws AWTException {
        robot = new Robot();

        radiantLeft.add(new Point(104, 966));
        radiantLeft.add(new Point(129, 948));
        radiantLeft.add(new Point(158, 958));

        radiantRight.add(new Point(158, 958));
        radiantRight.add(new Point(179, 973));
        radiantRight.add(new Point(198, 956));

        direLeft.add(new Point(62, 845));
        direLeft.add(new Point(85, 830));
        direLeft.add(new Point(107, 862));

        direRight.add(new Point(150, 849));
        direRight.add(new Point(126, 843));
        direRight.add(new Point(107, 862));

        radiantThreeLines.add(new Point(31, 934));
        radiantThreeLines.add(new Point(70, 952));
        radiantThreeLines.add(new Point(87, 993));

        direThreeLines.add(new Point(167, 812));
        direThreeLines.add(new Point(184, 856));
        direThreeLines.add(new Point(228, 874));
    }

    private void press(NativeKeyEvent e) throws InterruptedException {
        if (e.getKeyCode() == 81 || e.getKeyCode() == 76 || e.getKeyCode() == 112) {
            return;
        }
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        System.out.println(e.getKeyCode());

        ArrayList<Point> points;

        if (e.getKeyCode() == 44) {
            points = radiantLeft;
        } else if (e.getKeyCode() == 46) {
            points = radiantRight;

        } else if (e.getKeyCode() == 59) {
            points = direLeft;
        } else if (e.getKeyCode() == 222) {
            points = direRight;
        } else if (e.getKeyCode() == 91) {
            points = radiantThreeLines;
        } else if (e.getKeyCode() == 93) {
            points = direThreeLines;
        } else {
            return;
        }

        robot.keyPress(KeyEvent.VK_F1);
        robot.keyRelease(KeyEvent.VK_F1);
        Thread.sleep(0);
        //for (int i = 0; i < 5; i++) {
        robot.keyPress(KeyEvent.VK_Q);
        robot.keyRelease(KeyEvent.VK_Q);
        //}

        Thread.sleep(1400);

        for (int i = 0; i < 5; i++) {
            robot.keyPress(KeyEvent.VK_F1);
            robot.keyRelease(KeyEvent.VK_F1);
        }
        Thread.sleep(50);
        //Thread.sleep(3000);

        for (int i = 0; i < 3; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            Thread.sleep(20);
            //Thread.sleep(3000);

            robot.mouseMove(points.get(i).x, points.get(i).y);
            Thread.sleep(200);
            //Thread.sleep(3000);

            for (int j = 0; j < 10; j++) {
                //robot.keyPress(KeyEvent.VK_A);
                //robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                //robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                //robot.keyRelease(KeyEvent.VK_A);
                robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);

            }
            Thread.sleep(50);
        }
        robot.keyPress(KeyEvent.VK_F1);
        robot.keyRelease(KeyEvent.VK_F1);
        robot.keyPress(KeyEvent.VK_F1);
        robot.keyRelease(KeyEvent.VK_F1);
        robot.keyPress(KeyEvent.VK_F1);
        robot.keyRelease(KeyEvent.VK_F1);
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        try {
            press(e);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        //System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        //System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }

    public void start() throws AWTException {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }
        GlobalScreen.getInstance().addNativeKeyListener(new GlobalKeyListener());
    }

}
