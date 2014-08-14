package org.game.life.client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JPanel;

import org.apache.commons.io.IOUtils;

public class GamePanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    //@Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, 500, 500);
        paintAlive(g2d);
    }

    public void paintAlive(Graphics2D g2d) {
        System.out.println("paintAlive():   {");

        try {
            URL url = new URL("http://localhost:8080/jboss-ejb-in-war/game-life.jsf");
            //http://localhost:8080/jboss-ejb-in-war/game-life.jsf
            URLConnection con = url.openConnection();
            con.setUseCaches(false);
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            String body = IOUtils.toString(in, encoding);
            //            System.out.println(body);

            g2d.setColor(Color.BLACK);
            int labelStart = body.indexOf("<label>");
            int labelEnd = body.indexOf("</label>", labelStart + 7);
            String s = body.substring(labelStart + 7, labelEnd).trim();
            g2d.drawString("s=" + s, 25, 155);
            System.out.println("s=" + s);
            String[] points = s.split(";");
            //            System.out.println("points.length=" + points.length);

            // !1 changed only for compilation under maven 2.2.1 !!! Use more new version of maven: 3.0.4 or later !!!"
            //for (String p : points) {
            for (int i = 0; i < points.length; ++i) {
                String p = points[i]; // !1 added only for compilation under maven 2.2.1 !!! Use more new version of maven: 3.0.4 or later !!!"
                String[] coord = p.split(",");
                //                System.out.println("coord.length=" + coord.length);
                Integer x = Integer.valueOf(coord[0]);
                Integer y = Integer.valueOf(coord[1]);
                //                System.out.println("x=" + x + ", y=" + y);
                g2d.setColor(Color.BLUE);
                int size = 10;
                // !1 changed only for compilation under maven 2.2.1 !!! Use more new version of maven: 3.0.4 or later !!!"
                //g2d.fillRect(Integer.valueOf(coord[0]) * size, Integer.valueOf(coord[1]) * size, size, size);
                g2d.fillRect(Integer.valueOf(coord[0]).intValue() * size, Integer.valueOf(coord[1]).intValue() * size, size, size);
                g2d.setColor(Color.BLACK);
                // !1 changed only for compilation under maven 2.2.1 !!! Use more new version of maven: 3.0.4 or later !!!"
                //g2d.drawRect(Integer.valueOf(coord[0]) * size, Integer.valueOf(coord[1]) * size, size, size);
                g2d.drawRect(Integer.valueOf(coord[0]).intValue() * size, Integer.valueOf(coord[1]).intValue() * size, size, size);
            }
            in.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("paintAlive():   }");
    }
}
