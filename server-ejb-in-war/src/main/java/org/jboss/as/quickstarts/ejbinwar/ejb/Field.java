package org.jboss.as.quickstarts.ejbinwar.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// +---------------------> iX
// |
// |
// v
// iY

public class Field {
    final int xMax = 10;
    final int yMax = 10;

    //ArrayList<ArrayList<Point>> aliveOld;
    Map<Integer, Map<Integer, Point>> aliveOld;
    //Map<Integer, Integer> aliveOld;
    //ArrayList<ArrayList<Point>> aliveNew;
    Map<Integer, Map<Integer, Point>> aliveNew;

    //Map<Integer, Integer> aliveNew;

    public void addAlive(int ix, int iy) {
        addAliveNewPoint(ix, iy);
    }

    public Map<Integer, Map<Integer, Point>> getAliveNew() {
        return aliveNew;
    }

    public Map<Integer, Map<Integer, Point>> getAliveOld() {
        return aliveOld;
    }

    public String getAliveString() {
        String str = "";
        if (aliveOld != null) {
            for (Integer ix : aliveOld.keySet()) {
                Map<Integer, Point> m = aliveOld.get(ix);
                if (m != null) {
                    for (Integer iy : m.keySet()) {
                        str += ix + "," + iy + ";";
                    }
                }
            }
        }
        return str;
    }

    public int sizeOld() {
        int size = 0;
        if (aliveOld != null) {
            for (Integer i : aliveOld.keySet()) {
                Map<Integer, Point> m = aliveOld.get(i);
                size += m.size();
            }
            return size;
        } else {
            return -1;
        }
    }

    public int sizeNew() {
        //        if (aliveNew != null) {
        //            return aliveNew.size();
        //        } else {
        //            return -1;
        //        }
        int size = 0;
        if (aliveNew != null) {
            for (Integer i : aliveNew.keySet()) {
                Map<Integer, Point> m = aliveNew.get(i);
                size += m.size();
            }
            return size;
        } else {
            return -1;
        }
    }

    public void prepareToStart() {
        synchronized (aliveNew) {
            clear(aliveOld);
            swapLists();
        }
    }

    Integer synAlive = 1;

    private void swapLists() {
        synchronized (synAlive) {
            aliveOld = aliveNew;
            aliveNew = null;
        }
    }

    public void makeStep() {
        synchronized (synAlive) {
            for (Integer ix : aliveOld.keySet()) {
                Map<Integer, Point> m = aliveOld.get(ix);
                if (m == null) {
                    Utils.log("Should not happened");
                } else {
                    for (Integer iy : m.keySet()) {
                        doCell(ix, iy);
                    }
                }
            }
            clear(aliveOld);
            swapLists();
        }
    }

    private void clear(Map<Integer, Map<Integer, Point>> map) {
        if (map == null)
            return;
        for (Integer i : map.keySet()) {
            Map<Integer, Point> m = map.get(i);
            if (m == null)
                continue;
            m.clear();
        }
        map.clear();
    }

    private void doCell(int x, int y) {
        for (int ix = x - 1; ix <= x + 1; ++ix) {
            for (int iy = y - 1; iy <= y + 1; ++iy) {
                //Utils.log("ix=" + ix + ", iy=" + iy);
                Map<Integer, Point> m = aliveOld.get(ix);
                if (m == null) {
                    doDeadCell(ix, iy);
                } else {
                    Point p = m.get(iy);
                    if (p == null) {
                        doDeadCell(ix, iy);
                    } else {
                        doAliveCell(ix, iy);
                    }
                }
            }
        }
    }

    int counter = 0;

    private void doAliveCell(int ix, int iy) {
        counter = 0;
        count(ix, iy);
        if (counter == 2 || counter == 3) {
            addAliveNewPoint(ix, iy);
        }
    }

    private void doDeadCell(int ix, int iy) {
        counter = 0;
        count(ix, iy);
        if (counter == 3) {
            addAliveNewPoint(ix, iy);
        }
    }

    private void addAliveNewPoint(int ix, int iy) {
        if (aliveNew == null) {
            aliveNew = new HashMap<Integer, Map<Integer, Point>>();
        }
        if (aliveNew.get(ix) == null) {
            aliveNew.put(ix, new HashMap<Integer, Point>());
        }

        Map<Integer, Point> m = aliveNew.get(ix);
        Point p = m.get(iy);
        if (p == null) {
            p = new Point(ix, iy);
            m.put(iy, p);
        }

    }

    private void count(int ix, int iy) {
        if (ix == 0) {
            if (iy == 0) {
                doCornerLeftTop(ix, iy);
            } else if (iy == yMax) {
                doCornerLeftBottom(ix, iy);
            } else {
                doBorderLeft(ix, iy);
            }
        } else if (ix == xMax) {
            if (iy == 0) {
                doCornerRightTop(ix, iy);
            } else if (iy == yMax) {
                doCornerRightBottom(ix, iy);
            } else {
                doBorderRight(ix, iy);
            }
        } else if (iy == 0) {
            doBorderTop(ix, iy);
        } else if (iy == yMax) {
            doBorderBottom(ix, iy);
        } else {
            doPoint(ix, iy);
        }
    }

    private void doCornerLeftTop(int ix, int iy) {
    }

    private void doCornerLeftBottom(int ix, int iy) {
    }

    private void doCornerRightTop(int ix, int iy) {
    }

    private void doCornerRightBottom(int ix, int iy) {
    }

    private void doBorderLeft(int ix, int iy) {
        doMiddleVert(ix, iy);
        doRight(ix, iy);
    }

    private void doBorderRight(int ix, int iy) {
        doMiddleVert(ix, iy);
        doLeft(ix, iy);
    }

    private void doBorderTop(int ix, int iy) {
        doMiddleHor(ix, iy);
        doDown(ix, iy);
    }

    private void doBorderBottom(int ix, int iy) {
        doMiddleHor(ix, iy);
        doUp(ix, iy);
    }

    private void doPoint(int ix, int iy) {
        doMiddleVert(ix, iy);
        doRight(ix, iy);
        doLeft(ix, iy);
    }

    private void doLeft(int ix, int iy) {
        Map<Integer, Point> m = aliveOld.get(ix - 1);
        if (m == null)
            return;
        if (m.get(iy) != null)
            ++counter;
        if (m.get(iy - 1) != null)
            ++counter;
        if (m.get(iy + 1) != null)
            ++counter;
    }

    private void doRight(int ix, int iy) {
        Map<Integer, Point> m = aliveOld.get(ix + 1);
        if (m == null)
            return;
        if (m.get(iy) != null)
            ++counter;
        if (m.get(iy - 1) != null)
            ++counter;
        if (m.get(iy + 1) != null)
            ++counter;
    }

    //  +
    //  o
    //  +
    private void doMiddleVert(int ix, int iy) {
        Map<Integer, Point> m = aliveOld.get(ix);
        if (m == null)
            return;
        if (m.get(iy - 1) != null)
            ++counter;
        if (m.get(iy + 1) != null)
            ++counter;
    }

    // +o+
    private void doMiddleHor(int ix, int iy) {
        Map<Integer, Point> m = aliveOld.get(ix - 1);
        if (m != null) {
            if (m.get(iy) != null)
                ++counter;
        }
        m = aliveOld.get(ix + 1);
        if (m != null) {
            if (m.get(iy) != null)
                ++counter;
        }
    }

    //   +++
    //    o
    private void doUp(int ix, int iy) {
        Map<Integer, Point> m = aliveOld.get(ix - 1);
        if (m != null) {
            if (m.get(iy - 1) != null)
                ++counter;
        }
        m = aliveOld.get(ix);
        if (m != null) {
            if (m.get(iy - 1) != null)
                ++counter;
        }
        m = aliveOld.get(ix + 1);
        if (m != null) {
            if (m.get(iy - 1) != null)
                ++counter;
        }
    }

    //    o
    //   +++
    private void doDown(int ix, int iy) {
        Map<Integer, Point> m = aliveOld.get(ix - 1);
        if (m != null) {
            if (m.get(iy + 1) != null)
                ++counter;
        }
        m = aliveOld.get(ix);
        if (m != null) {
            if (m.get(iy + 1) != null)
                ++counter;
        }
        m = aliveOld.get(ix + 1);
        if (m != null) {
            if (m.get(iy + 1) != null)
                ++counter;
        }
    }

}
