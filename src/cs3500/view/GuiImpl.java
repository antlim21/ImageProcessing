package cs3500.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javax.swing.filechooser.FileNameExtensionFilter;

import cs3500.controller.ImageUtil;
import cs3500.controller.PhotoshopController;
import cs3500.model.PhotoshopAdditionalOperationsInterface;

/**
 * GuiImpl is where the GUI is created for the client to use and adds functionality to each of the
 *     buttons. Client is now able to see the images transform in real time.
 */
public class GuiImpl extends JFrame implements GuiInterface, ActionListener {

  private JPanel mainPanel;
  private JScrollPane imageScrollPane;
  private JTextArea input;

  private JButton loadButton;
  private JButton saveButton;
  private JButton redButton;
  private JButton greenButton;
  private JButton blueButton;
  private JButton valueButton;
  private JButton intensityButton;
  private JButton lumaButton;
  private JButton verticalFlipButton;
  private JButton horizontalFlipButton;
  private JButton brightenButton;
  //private JButton blurButton;
  private JButton sharpenButton;
  private JButton greyscaleButton;
  private JButton sepiaButton;
  private Appendable appendable;
  private JButton darkenButton;
  private JButton downsizeButton;
  //private JPanel buttonsPanel;
  private Consumer<String> commandCallback;
  private PhotoshopAdditionalOperationsInterface model;
  private PhotoshopController controller;
  private List<String> imageNames;

  private String currentImageName;
  private JLabel imageLabel;
  private JPanel histogramPanel;
  //private JPanel buttonsAndHisto;
  private JPanel imagePanel;
  private String brightenAmt;
  private JPanel mainFrame;

  /**
   * Creates the GUI and allows it to run. Client is able to use methods that were originally
   *     text and file based, but now clickable.
   * @param model Model that is taken in to run the GUI.
   */
  public GuiImpl(PhotoshopAdditionalOperationsInterface model) {
    super();

    this.appendable = new StringBuilder();
    this.imageNames = new ArrayList<String>();
    this.imageNames.add("Koala");

    this.model = model;
    this.controller = null;
    this.currentImageName = "Koala";

    /*
    JFrame frame = new JFrame();
    frame.setBackground(Color.GRAY);
    frame.setTitle("Photoshop");
    frame.setSize(1200,900);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     */

    //mainFrame = new JFrame("Photoshop");
    mainFrame = new JPanel(new FlowLayout());
    mainFrame.add(new Button());
    mainFrame.setBorder(BorderFactory.createTitledBorder("Where the image goes."));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // <- keep this
    mainFrame.setVisible(true);

    mainPanel = new JPanel();
    setSize(1200, 900);
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

    //scroll bars around this main panel
    imageScrollPane = new JScrollPane(mainPanel);
    add(imageScrollPane);

    //mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel

    //image area
    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Where the image goes."));
    imagePanel.setPreferredSize(new Dimension(900, 900));
    imageScrollPane = new JScrollPane(imagePanel);
    imageScrollPane.setPreferredSize(new Dimension(800, 800));
    mainPanel.add(imagePanel, BorderLayout.WEST);

    String images = "images/";
    imageLabel = new JLabel();
    imageScrollPane = new JScrollPane(imageLabel);
    imageLabel.setIcon(new ImageIcon(images));
    imageScrollPane.setPreferredSize(new Dimension(800, 800));
    imagePanel.add(imageScrollPane);


    //right hand side
    JPanel buttonsAndHisto = new JPanel();
    buttonsAndHisto.setBorder(BorderFactory.createTitledBorder("User Controls:"));
    buttonsAndHisto.setPreferredSize(new Dimension(200, 400));
    buttonsAndHisto.setLayout(new GridLayout(2, 1));
    mainPanel.add(buttonsAndHisto, BorderLayout.CENTER);


    //methods area
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setBorder(BorderFactory.createTitledBorder("Operations:"));
    //buttonsPanel.setLayout(new GridBagLayout());
    //buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
    //buttonsPanel.setLayout(new GridLayout(8,2));
    GridBagLayout layout = new GridBagLayout();

    buttonsPanel.setLayout(layout);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;

    //buttonsPanel.setSize(300,600);
    //buttonsAndHisto.add(buttonsPanel);

    loadButton = new JButton("Load");
    loadButton.setActionCommand("Load Button");
    saveButton = new JButton("Save");
    saveButton.setActionCommand("Save Button");
    redButton = new JButton("Red");
    redButton.setActionCommand("Red Button");
    greenButton = new JButton("Green");
    greenButton.setActionCommand("Green Button");
    blueButton = new JButton("Blue");
    blueButton.setActionCommand("Blue Button");
    valueButton = new JButton("Value");
    valueButton.setActionCommand("Value Button");
    intensityButton = new JButton("Intensity");
    intensityButton.setActionCommand("Intensity Button");
    lumaButton = new JButton("Luma");
    lumaButton.setActionCommand("Luma Button");
    verticalFlipButton = new JButton("Vertical Flip");
    verticalFlipButton.setActionCommand("Vertical Flip Button");
    horizontalFlipButton = new JButton("Horizontal Flip");
    horizontalFlipButton.setActionCommand("Horizontal Flip Button");
    brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("Brighten Button");
    darkenButton = new JButton("Darken");
    darkenButton.setActionCommand("Darken Button");
    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen Button");
    JButton blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur Button");
    greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setActionCommand("Greyscale Button");
    sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("Sepia Button");
    downsizeButton = new JButton("Downsize");
    downsizeButton.setActionCommand("Downsize Button");
    this.setActionListener(this);


    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 5, 5, 5);

    gbc.gridx = 0;
    gbc.gridy = 0;
    buttonsPanel.add(loadButton, gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    buttonsPanel.add(saveButton, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    buttonsPanel.add(redButton, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    buttonsPanel.add(greenButton, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    buttonsPanel.add(blueButton, gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    buttonsPanel.add(verticalFlipButton, gbc);

    gbc.gridx = 0;
    gbc.gridy = 5;
    buttonsPanel.add(horizontalFlipButton, gbc);

    gbc.gridx = 0;
    gbc.gridy = 6;
    buttonsPanel.add(brightenButton, gbc);

    gbc.gridx = 0;
    gbc.gridy = 7;
    buttonsPanel.add(darkenButton, gbc);

    gbc.gridx = 1;
    gbc.gridy = 1;
    buttonsPanel.add(valueButton, gbc);

    gbc.gridx = 1;
    gbc.gridy = 2;
    buttonsPanel.add(intensityButton, gbc);

    gbc.gridx = 1;
    gbc.gridy = 3;
    buttonsPanel.add(lumaButton, gbc);

    gbc.gridx = 1;
    gbc.gridy = 4;
    buttonsPanel.add(sharpenButton, gbc);

    gbc.gridx = 1;
    gbc.gridy = 5;
    buttonsPanel.add(blurButton, gbc);

    gbc.gridx = 1;
    gbc.gridy = 6;
    buttonsPanel.add(greyscaleButton, gbc);

    gbc.gridx = 1;
    gbc.gridy = 7;
    buttonsPanel.add(sepiaButton, gbc);

    gbc.gridx = 1;
    gbc.gridy = 8;
    buttonsPanel.add(downsizeButton, gbc);

    buttonsPanel.setSize(200, 150);
    buttonsPanel.setPreferredSize(new Dimension(200, 100));
    buttonsAndHisto.add(buttonsPanel);


    //histogram
    //Paint component, draw rectangle etc.
    histogramPanel = new DrawHistogram();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("histogram"));
    histogramPanel.setSize(200, 300);
    buttonsAndHisto.add(histogramPanel);

  }

  @Override
  public void refresh() {
    this.input.requestFocus();
    this.repaint();
    this.imagePanel.repaint();
    this.histogramPanel.repaint();
  }


  @Override
  public void addImageName(String imageName) {
    this.imageNames.add(imageName);
  }

  public void setController(PhotoshopController controller) {
    this.controller = controller;
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }


  @Override
  public String getMethod() {
    return null;
  }

  @Override
  public String getCurrentImageName() {
    return this.currentImageName;
  }

  /**
   * Sends a message to the client (used typically when sending error messages).
   *
   * @param message Message that photoshop program sends to client via console.
   * @throws IOException If message is not able to be sent to client.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }

  /**
   * SetActionListener waits for an input to be sent in when client presses a button.
   * @param listener Waits for an input to be given by pressing a button.
   */
  public void setActionListener(ActionListener listener) {
    loadButton.addActionListener(listener);
    saveButton.addActionListener(listener);
    redButton.addActionListener(listener);
    greenButton.addActionListener(listener);
    blueButton.addActionListener(listener);
    valueButton.addActionListener(listener);
    intensityButton.addActionListener(listener);
    lumaButton.addActionListener(listener);
    verticalFlipButton.addActionListener(listener);
    horizontalFlipButton.addActionListener(listener);
    brightenButton.addActionListener(listener);
    darkenButton.addActionListener(listener);
    sharpenButton.addActionListener(listener);
    blueButton.addActionListener(listener);
    greyscaleButton.addActionListener(listener);
    sepiaButton.addActionListener(listener);
    downsizeButton.addActionListener(listener);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("Action Performed");
    StringReader reader = new StringReader("");
    String temp = "";
    switch (e.getActionCommand()) {
      case "Load Button": {
        //reader = new StringReader("load images/Koala.jpg Koala");

        final JFileChooser fchooser = new JFileChooser(".");
        setActionListener(loadButton.getAction());
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image Files", "jpg", "gif", "ppm", "png", "bmp");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(GuiImpl.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          String stringFile = null;
          model.load(f.getPath(), getCurrentImageName());
          BufferedImage creation =
                  ImageUtil.createImage(this.model.getImage(this.getCurrentImageName()));
          imageLabel.setIcon(new ImageIcon(creation));
          currentImageName = getCurrentImageName();

          imagePanel.repaint();
          imagePanel.revalidate();
          imageScrollPane.repaint();
          imageScrollPane.revalidate();
          imageLabel.repaint();
          imageLabel.revalidate();
          mainPanel.repaint();
          mainPanel.revalidate();
          mainFrame.repaint();
          mainFrame.revalidate();
          imageLabel.setVisible(true);
          imagePanel.setVisible(true);
          imageScrollPane.setVisible(true);


        }
      }
      break;
      case "Save Button": {
        final JFileChooser fchooser = new JFileChooser(".");
        setActionListener(saveButton.getAction());
        System.out.println("i am here #save");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image Files", "jpg", "gif", "ppm", "png", "bmp");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showSaveDialog(GuiImpl.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          //fileSaveDisplay.setText(f.getAbsolutePath());
          temp = "save " + f.getPath() + " " + this.getCurrentImageName();
        }

      }
      break;
      case "Red Button":
        temp = "red-component " + this.getCurrentImageName() +
                " " + this.getCurrentImageName() + "-Red ";
        this.currentImageName += "-Red";

        break;
      case "Green Button":
        temp = "green-component " + this.getCurrentImageName() +
                " " + this.getCurrentImageName() + "-Green ";
        this.currentImageName += "-Green";

        break;
      case "Blue Button":
        temp = "blue-component " + this.getCurrentImageName() +
                " " + this.getCurrentImageName() + "-Blue ";
        this.currentImageName += "-Blue";

        break;
      case "Value Button":
        temp = "value-component " + this.getCurrentImageName() +
                " " + this.getCurrentImageName() + "-Value ";
        this.currentImageName += "-Value";

        break;
      case "Intensity Button":
        temp = "intensity-component " + this.getCurrentImageName() +
                " " + this.getCurrentImageName() + "-Intensity ";
        this.currentImageName += "-Intensity";

        break;
      case "Luma Button":
        temp = "luma-component " + this.getCurrentImageName() +
                " " + this.getCurrentImageName() + "-Luma ";
        this.currentImageName += "-Luma";

        break;
      case "Horizontal Flip Button":
        temp = "horizontal-flip " + this.getCurrentImageName() +
                " " + this.getCurrentImageName() + "-HF ";
        this.currentImageName += "-HF";

        break;
      case "Vertical Flip Button":
        temp = "vertical-flip " + this.getCurrentImageName() +
                " " + this.getCurrentImageName() + "-VF ";
        this.currentImageName += "-VF";

        break;
      case "Brighten Button": {
        //brightenAmt.setText(JOptionPane.showInputDialog("Brighten by: "));
        String someAmt = new String(JOptionPane.showInputDialog("How much do you " +
                "want to brighten by"));
        try {
          int i = Integer.parseInt(someAmt);
          temp = "brighten " + someAmt + " " + this.getCurrentImageName() +
                  " " + this.getCurrentImageName() + "-Brighten ";
          this.currentImageName += "-Brighten";
        } catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(GuiImpl.this, "Brighten " +
                  "increment must be an integer", "Error Message", JOptionPane.ERROR_MESSAGE);
          try {
            renderMessage("Need to put in a valid integer");
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        }
      }
      break;
      case "Darken Button": {
        String someAmt = new String(JOptionPane.showInputDialog("How much do you " +
                "want to darken by?"));
        try {
          int i = Integer.parseInt(someAmt);
          temp = "brighten -" + someAmt + " " + this.getCurrentImageName() +
                  " " + this.getCurrentImageName() + "-Darken ";
          System.out.println(temp);
          this.currentImageName += "-Darken";
        } catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(GuiImpl.this, "Darken " +
                  "increment must be an integer", "Error Message", JOptionPane.ERROR_MESSAGE);
          try {
            renderMessage("Need to put in a valid integer");
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        }
      }
      break;
      case "Downsize Button": {
        String someAmt = new String(JOptionPane.showInputDialog("Downsize height to (%)?"));
        String someAmt1 = new String(JOptionPane.showInputDialog("Downsize width to (%)?"));
        try {
          int i = Integer.parseInt(someAmt);
          int j = Integer.parseInt(someAmt1);
          System.out.println(getCurrentImageName() + " alpha");
          temp = "downsize " + someAmt + " " + someAmt1 + " " + this.getCurrentImageName() +
                  " " + this.getCurrentImageName() + "-Downsize ";

          System.out.println(temp);


          this.currentImageName += "-Downsize";
          System.out.println(currentImageName + " beta");
        } catch (NumberFormatException nfe) {
          JOptionPane.showMessageDialog(GuiImpl.this, "Downsize " +
                  "increment must be an integer", "Error Message", JOptionPane.ERROR_MESSAGE);
          try {
            renderMessage("Need to put in a valid integer");
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        }
      }
      break;
      case "Blur Button":
        temp = "blur " + this.getCurrentImageName() +
                " " + this.getCurrentImageName() + "-Blur ";
        this.currentImageName += "-Blur";

        break;
      case "Sharpen Button":
        temp = "sharpen " + this.getCurrentImageName() +
                " " + this.getCurrentImageName() + "-Sharpen ";
        this.currentImageName += "-Sharpen";

        break;
      case "Greyscale Button":
        temp = "greyscale " + this.getCurrentImageName() +
                " " + this.getCurrentImageName() + "-Greyscale ";
        this.currentImageName += "-Greyscale";

        break;
      case "Sepia Button":
        temp = "sepia " + this.getCurrentImageName() +
                " " + this.getCurrentImageName() + "-Sepia ";
        this.currentImageName += "-Sepia";

        break;
      default:
        temp = "";
        break;
    }
    reader = new StringReader(temp);
    this.controller.setReader(reader);

    //System.out.println(this.getCurrentImageName());
    try {
      BufferedImage creation =
              ImageUtil.createImage(this.model.getImage(this.getCurrentImageName()));
      if (!e.getActionCommand().equals("Load Button")) {
        imageLabel.setIcon(new ImageIcon(creation));
      }
    } catch (NullPointerException npe) {
      JOptionPane.showMessageDialog(GuiImpl.this, "Must load an image first.",
              "Error Message: Image does not exist.", JOptionPane.ERROR_MESSAGE);
    }
    try {
      DrawHistogram panel = (DrawHistogram) histogramPanel;
      panel.paintOverHistogram(this.model.getImage(this.getCurrentImageName()));
      panel.repaint();
    } catch (NullPointerException npe) {
      System.out.println("");
    }
  }
}