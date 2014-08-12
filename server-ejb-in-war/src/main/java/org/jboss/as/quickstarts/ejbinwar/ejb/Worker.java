package org.jboss.as.quickstarts.ejbinwar.ejb;

import java.util.Map;

//import org.life.logic.Point;

//public class Worker implements Runnable {
public class Worker extends Thread {

    static Field f = new Field();

    static {
        fillField();
        f.prepareToStart();
    }

    private static void fillField() {
        for (int ix = 4; ix <= 6; ++ix) {
            for (int iy = 4; iy <= 6; ++iy) {
                f.addAlive(ix, iy);
            }
        }
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
        if (f == null) {
            System.out.println("f == null");
        } else {
            super.start();
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            f.makeStep();
            printField(f.getAliveOld());
            System.out.println("================================================");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void printField(Map<Integer, Map<Integer, Point>> m) {
        if (m == null) {
            System.out.println("printField(): m==null");
            return;
        }
        int xMax = 10;
        int yMax = 10;
        //Map<Integer, Map<Integer, Point>> m = f.getAliveNew();
        for (int iy = 1; iy <= yMax; ++iy) {
            String s = "";
            for (int ix = 1; ix <= xMax; ++ix) {
                Map<Integer, Point> mX = m.get(ix);
                if (mX == null) {
                    //                    System.out.print("-");
                    s += "-";
                } else {
                    Point p = mX.get(iy);
                    if (p == null) {
                        //                        System.out.print("-");
                        s += "-";
                    } else {
                        //                        System.out.print("+");
                        s += "+";
                    }
                }
            }
            System.out.println(s);
        }
    }

}
