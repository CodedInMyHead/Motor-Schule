package gui;

import common.DrehstromMotor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MotorFrame extends JFrame {
  private static final Logger logger = Logger.getLogger("gui");
  private final static int WIDTH = 962;
  private final static int HEIGHT = 800;
  private final static String TEXT_HEADING = "Lastsimulation anhand eines SEW-Drehstromgetriebemotors";
  private final static String TITLE = "Lastsimulation";

  // start attributes
  private JLabel heading = new JLabel();
  private JPanel panelOben = new JPanel(null, true);
    private JLabel leistungsAuf = new JLabel();
    private JTextField leistungAuf = new JTextField();
    private JLabel leistungsAb = new JLabel();
    private JTextField leistungAb = new JTextField();
    private JLabel leistungsVerlust = new JLabel();
    private JTextField leistungVerlust = new JTextField();
    private JLabel wirkungsgrad = new JLabel();
    private JTextField wirkungsgrade = new JTextField();
  private JPanel panelMitte = new JPanel(null, true);
    private JLabel titleMitte = new JLabel();
    private JTextField inputMitte = new JTextField();
    private JCheckBox checkBox = new JCheckBox();
  private JPanel panelUnten = new JPanel(null, true);
    private JLabel labelAmpere = new JLabel();
    private JTextField inputAmpere = new JTextField();
    private JLabel labelDrehzahl = new JLabel();
    private JTextField inputDrehzahl = new JTextField();
  // end attributes
  
  public MotorFrame(final DrehstromMotor motor) {
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setSize(WIDTH, HEIGHT);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    final int x = (d.width - getSize().width) / 2;
    final int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle(TITLE);
    setResizable(false);
    final Container cp = getContentPane();
    cp.setLayout(null);
    
    heading.setBounds(16, 16, 632, 24);
    heading.setText(TEXT_HEADING);

    // PANEL Oben
    panelOben.setBounds(16, 48, 352, 176);
    panelOben.setOpaque(false);
    panelOben.setBorder(BorderFactory.createTitledBorder("Nennwerte mit Leistungsschild"));

    leistungsAuf.setBounds(8, 40, 151, 24);
    leistungAuf.setBounds(216, 40, 80, 24);
    leistungsAb.setBounds(8, 72, 192, 24);
    leistungAb.setBounds(216, 72, 80, 24);
    leistungsVerlust.setBounds(8, 104, 176, 24);
    leistungVerlust.setBounds(216, 104, 80, 24);
    wirkungsgrad.setBounds(8, 136, 160, 24);
    wirkungsgrade.setBounds(216, 136, 80, 24);

    leistungsAuf.setText("Leistungsaufnahme in Kw");
    leistungsAb.setText("Leistungsabgabe in Kw");
    leistungsVerlust.setText("Verlustleistung in Kw");
    wirkungsgrad.setText("Wirkunsgrad in Prozent ");
    leistungAuf.setText(motor.getLeistungsaufnahme() + "");
    leistungAb.setText(motor.getLeistungsabgabe() + "");
    leistungVerlust.setText(motor.getVerlustleistung() + "");
    wirkungsgrade.setText(motor.getWirkungsgrad() + "");


    panelOben.add(leistungsAuf);
    panelOben.add(leistungAuf);
    panelOben.add(leistungsAb);
    panelOben.add(leistungAb);
    panelOben.add(leistungsVerlust);
    panelOben.add(leistungVerlust);
    panelOben.add(wirkungsgrad);
    panelOben.add(wirkungsgrade);

    // Panel Mitte

    panelMitte.setBounds(16, 240, 352, 128);
    panelMitte.setOpaque(false);
    panelMitte.setBorder(BorderFactory.createTitledBorder("Lastsimulation Welle "));

    titleMitte.setBounds(16, 24, 256, 24);
    inputMitte.setBounds(16, 64, 80, 24);
    checkBox.setBounds(16, 96, 248, 24);

    titleMitte.setText("Nennwert: 5 Nm (1,0 Nm bis 6,0Nm)");
    checkBox.setText("Drehzahl an Last anpassen lt. Kennlinie");

    checkBox.setOpaque(false);

    panelMitte.add(titleMitte);
    panelMitte.add(inputMitte);
    panelMitte.add(checkBox);

    // Panel Unten

    panelUnten.setBounds(16, 392, 352, 120);
    panelUnten.setOpaque(false);
    panelUnten.setBorder(BorderFactory.createTitledBorder("Ausgabe Strom und Drehzahl "));

    labelAmpere.setBounds(16, 40, 103, 24);
    inputAmpere.setBounds(224, 40, 80, 24);
    labelDrehzahl.setBounds(16, 80, 128, 24);
    inputDrehzahl.setBounds(224, 80, 80, 24);

    labelAmpere.setText("Strom in Ampere ");
    labelDrehzahl.setText("Drehzahl bei Last");

    panelUnten.add(labelAmpere);
    panelUnten.add(inputAmpere);
    panelUnten.add(labelDrehzahl);
    panelUnten.add(inputDrehzahl);

    // Image

    BufferedImage sewCard = null;
    try {
      sewCard = ImageIO.read(new File("src/common/sew-card.png"));
    } catch (Exception e) {
      logger.warning("Failed to read image!");
      System.exit(1);
    }

    double scale = 1.4;
    int width = (int) (244 * scale);
    int height = (int) (134 * scale);

    final ImageIcon scaledIcon = new ImageIcon(new ImageIcon(sewCard).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    JLabel image = new JLabel(scaledIcon);
    image.setBounds(378+10, 48+7, width, height);
    image.setOpaque(false);

    // Add Canvas

    final int HEIGHT_CANVAS = 500;
    final Canvas canvas = new FunctionCanvas(HEIGHT_CANVAS);
    canvas.setBounds(378+10, 55 + height + 20, HEIGHT_CANVAS, HEIGHT_CANVAS);
    canvas.setBackground(Color.LIGHT_GRAY);

    // Add to cp

    cp.add(heading);
    cp.add(panelOben);
    cp.add(panelMitte);
    cp.add(panelUnten);
    cp.add(image);
    cp.add(canvas);

    setVisible(true);
  }
}
