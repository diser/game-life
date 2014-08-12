package org.game.life.client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GamePanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
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
            for (String p : points) {
                String[] coord = p.split(",");
                //                System.out.println("coord.length=" + coord.length);
                Integer x = Integer.valueOf(coord[0]);
                Integer y = Integer.valueOf(coord[1]);
                //                System.out.println("x=" + x + ", y=" + y);
                g2d.setColor(Color.BLUE);
                int size = 10;
                g2d.fillRect(Integer.valueOf(coord[0]) * size, Integer.valueOf(coord[1]) * size, size, size);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(Integer.valueOf(coord[0]) * size, Integer.valueOf(coord[1]) * size, size, size);
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
