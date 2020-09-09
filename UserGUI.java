import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import java.net.URL;
import java.text.DecimalFormat;

class UserGUI extends UserSql implements ActionListener {
  JFrame frame;
  JPanel buttonPanel;
  JPanel labelPanel;
  JPanel imagePanel;
  JLabel imageLabel;
  JButton registered;
  JButton newUser;
  JLabel label;
  JLabel registeredLabel;
  JPanel textPanel;
  JPanel textPanel2;
  JPanel textPanel6;
  JPanel textPanel5;
  JPanel textPanel7;
  JPanel textPanel8;
  JPanel textPanel9;
  JPanel textPanel10;
  JPanel textPanel11;
  JPanel textPanel12;
  JTextField password;
  JTextField username;
  JTextField newPassword;
  JTextField newUserName;
  JButton[] buttons = new JButton[2];
  String[] buttonName = {"Registered", "New Admin"};
  JLabel newUserLabel;

//Creates Initial Frame
  public void make() {
    frame = new JFrame("Movie Predictor");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    buttonPanel = new JPanel();
    labelPanel = new JPanel();
    imagePanel = new JPanel();
    imageLabel = new JLabel();
    label = new JLabel("Welcome to The Movie Predictor!");
    for (int i=0; i<2; i++) {
      JButton button = new JButton(buttonName[i]);
      buttons[i] = button;
      button.addActionListener(this);
      buttonPanel.add(button);

    }

    labelPanel.add(label);

    ImageIcon icon = new ImageIcon("img.png");
    Image image = icon.getImage();
    Image newimg = image.getScaledInstance(200,200, java.awt.Image.SCALE_SMOOTH);
    icon = new ImageIcon(newimg);
    imageLabel.setIcon(icon);

    imagePanel.add(imageLabel);
    frame.getContentPane().add(BorderLayout.PAGE_START, labelPanel);
    frame.getContentPane().add(BorderLayout.CENTER, buttonPanel);
    frame.getContentPane().add(BorderLayout.SOUTH, imagePanel);
    frame.setVisible(true);


  }
  @Override
  public void actionPerformed(ActionEvent e) {
    Object src = e.getSource();
    String action = e.getActionCommand();
    if (src == buttons[0]) {
      registeredWindow();
    }

    if (src == buttons[1]) {
      newUserWindow();
    }

    if (action.equals("Create")) {
      options();
    }

    if (action.equals("Submit")) {
      options();
    }

    if (action.equals("Lookup Movie")) {
      frame.remove(imagePanel);
      search();
    }

    if (action.equals("Add Movie")) {
      frame.remove(imagePanel);
      add();
    }

    if (action.equals("Lookup Directors")) {
      frame.remove(imagePanel);
      searchDirector();
    }

    if (action.equals("Add Director")) {
      frame.remove(imagePanel);
      addDirectors();
    }

    if (action.equals("Lookup Lead")) {
      frame.remove(imagePanel);
      lookupLead();
    }

    if (action.equals("Add Lead")) {
      frame.remove(imagePanel);
      addLeads();
    }

    if (action.equals("Top Genres")) {
      frame.remove(imagePanel);
      genrePage();
    }
  }

//Window to login
  public void registeredWindow() {
    frame.remove(buttonPanel);
    frame.remove(imagePanel);
    labelPanel.remove(label);


    registeredLabel = new JLabel("Please Enter Login Info");
    labelPanel.add(registeredLabel);
    textPanel = new JPanel();
    textPanel.setSize(200,200);

    password = new JTextField("Password");
    textPanel.add(password, BorderLayout.EAST);
    password.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        password.setText("");
      }
    });

    username = new JTextField("Username");
    textPanel.add(username, BorderLayout.WEST);
    username.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        username.setText("");
      }
    });

    JButton submitLogin = new JButton("Submit");
    submitLogin.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int good = validUser(username.getText(),password.getText());
        if (good==1) {
          frame.remove(imagePanel);
          options();
        } else {
            loginError();
        }
      }
    });


    textPanel.add(submitLogin, BorderLayout.SOUTH);

    ImageIcon icon = new ImageIcon("img2.png");
    Image image = icon.getImage();
    Image newimg = image.getScaledInstance(200,200, java.awt.Image.SCALE_SMOOTH);
    icon = new ImageIcon(newimg);
    imageLabel.setIcon(icon);

    imagePanel.add(imageLabel);

    frame.pack();
    frame.setSize(400,400);

    frame.getContentPane().add(BorderLayout.CENTER, textPanel);
    frame.getContentPane().add(BorderLayout.SOUTH, imagePanel);

    frame.setVisible(true);
  }

//Window for new users
  public void newUserWindow() {
    JFrame newUserFrame = new JFrame("New User");
    frame.remove(buttonPanel);
    frame.remove(imagePanel);
    labelPanel.remove(label);

    newUserLabel = new JLabel("Please enter your information");
    labelPanel.add(newUserLabel);

    textPanel = new JPanel();
    textPanel.setSize(200,200);

    newPassword = new JTextField("New Password");
    textPanel.add(newPassword, BorderLayout.EAST);
    newPassword.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        newPassword.setText("");
      }
    });

    newUserName = new JTextField("New Username");
    textPanel.add(newUserName, BorderLayout.WEST);
    newUserName.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        newUserName.setText("");
      }
    });

    JButton createLogin = new JButton("Create");
    createLogin.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int good = addUser(newUserName.getText(),newPassword.getText());
        if (good==1) {
          frame.remove(imagePanel);
          options();
        } else {
            loginError();
        }
      }
    });

    textPanel.add(createLogin, BorderLayout.SOUTH);

    ImageIcon icon = new ImageIcon("img2.png");
    Image image = icon.getImage();
    Image newimg = image.getScaledInstance(200,200, java.awt.Image.SCALE_SMOOTH);
    icon = new ImageIcon(newimg);
    imageLabel.setIcon(icon);

    imagePanel.add(imageLabel);

    frame.pack();
    frame.setSize(400,400);

    frame.getContentPane().add(BorderLayout.CENTER, textPanel);
    frame.getContentPane().add(BorderLayout.SOUTH, imagePanel);
    frame.setVisible(true);
  }

//Window with user options for program
  public void options() {
    frame.remove(textPanel);
    labelPanel.removeAll();

    JLabel optionsLabel = new JLabel("Please Choose");
    labelPanel.add(optionsLabel);

    textPanel5 = new JPanel();
    textPanel5.setSize(300,300);

    JButton lookButton = new JButton("Lookup Movie");
    lookButton.addActionListener(this);
    textPanel5.add(lookButton, BorderLayout.NORTH);

    JButton addButton = new JButton("Add Movie");
    addButton.addActionListener(this);
    textPanel5.add(addButton, BorderLayout.SOUTH);

    JButton lookDirectorButton = new JButton("Lookup Directors");
    lookDirectorButton.addActionListener(this);
    textPanel5.add(lookDirectorButton, BorderLayout.NORTH);

    JButton addDirectorButton = new JButton("Add Director");
    addDirectorButton.addActionListener(this);
    textPanel5.add(addDirectorButton, BorderLayout.SOUTH);

    JButton lookCastButton = new JButton("Lookup Lead");
    lookCastButton.addActionListener(this);
    textPanel5.add(lookCastButton, BorderLayout.NORTH);

    JButton addCastButton = new JButton("Add Lead");
    addCastButton.addActionListener(this);
    textPanel5.add(addCastButton, BorderLayout.SOUTH);

    JButton topGenresButton = new JButton("Top Genres");
    topGenresButton.addActionListener(this);
    textPanel5.add(topGenresButton, BorderLayout.NORTH);

    ImageIcon icon = new ImageIcon("img3.png");
    Image image = icon.getImage();
    Image newimg = image.getScaledInstance(200,200, java.awt.Image.SCALE_SMOOTH);
    icon = new ImageIcon(newimg);
    imageLabel.setIcon(icon);

    imagePanel.add(imageLabel);

    frame.pack();
    frame.setSize(400,400);
    frame.getContentPane().add(BorderLayout.CENTER, textPanel5);
    frame.getContentPane().add(BorderLayout.SOUTH, imagePanel);
    frame.setVisible(true);
  }

//Search for movies in the database
  public void search() {
    frame.remove(textPanel5);
    labelPanel.removeAll();

    JLabel searchLabel = new JLabel("Enter name of movie. Partial titles accepted.");
    labelPanel.add(searchLabel);

    textPanel6 = new JPanel();
    textPanel6.setSize(200,200);

    JTextField movieField = new JTextField("Movie Name");
    textPanel6.add(movieField, BorderLayout.WEST);
    movieField.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        movieField.setText("");
      }
    });

    ImageIcon icon = new ImageIcon("img4.png");
    Image image = icon.getImage();
    Image newimg = image.getScaledInstance(200,200, java.awt.Image.SCALE_SMOOTH);
    icon = new ImageIcon(newimg);
    imageLabel.setIcon(icon);

    imagePanel.add(imageLabel);

    JButton submitSearch = new JButton("Search");
    submitSearch.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.remove(imagePanel);
        ArrayList<String> movies = findMovie(movieField.getText());
        moviesIn(movies);
      }
    });


    textPanel6.add(submitSearch, BorderLayout.EAST);

    JButton back = new JButton("Back");
    back.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.remove(textPanel6);
        frame.remove(imagePanel);
        options();
      }
    });
    textPanel6.add(back);

    frame.pack();
    frame.setSize(400,400);
    frame.getContentPane().add(BorderLayout.CENTER, textPanel6);
    frame.getContentPane().add(BorderLayout.SOUTH, imagePanel);
    frame.setVisible(true);

  }

//Get movies from database
  public void moviesIn(ArrayList<String> s) {
    frame.remove(textPanel6);
    labelPanel.removeAll();

    JLabel searchLabel = new JLabel("Movies found in database.");
    labelPanel.add(searchLabel);

    textPanel11 = new JPanel();
    textPanel11.setSize(300,300);
    textPanel11.setLayout(new BoxLayout(textPanel11, BoxLayout.Y_AXIS));
    JPanel tPanel = new JPanel();
    tPanel.setSize(300,300);

    JScrollPane pane = new JScrollPane(textPanel11, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    for (int i = 0; i < s.size(); i++) {
      JTextArea txtArea = new JTextArea();
      txtArea.setEditable(false);
      textPanel11.add(txtArea);
      txtArea.setText(txtArea.getText() + s.get(i) + "\n\n");
    }

    JButton back = new JButton("Back");
    back.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.remove(pane);
        frame.remove(tPanel);
        options();
      }
    });

    tPanel.add(back);

    frame.pack();
    frame.setSize(400,400);
    frame.getContentPane().add(pane);
    frame.getContentPane().add(tPanel,BorderLayout.SOUTH);
    frame.setVisible(true);
  }

//Function to add movies to database
  public void add() {
    frame.remove(textPanel5);
    labelPanel.removeAll();

    JLabel addLabel = new JLabel("Add Movie Information");
    labelPanel.add(addLabel);

    textPanel7 = new JPanel();
    textPanel7.setSize(300,300);
    BoxLayout boxlayout = new BoxLayout(textPanel7, BoxLayout.Y_AXIS);
    textPanel7.setLayout(boxlayout);

    JTextField name = new JTextField("Name: ");
    name.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        name.setText("");
      }
    });

    JTextField directorFirst = new JTextField("Director First Name: ");
    directorFirst.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        directorFirst.setText("");
      }
    });

    JTextField directorLast = new JTextField("Director Last Name: ");
    directorLast.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        directorLast.setText("");
      }
    });

    JTextField lead = new JTextField("Lead Actor/Actress: ");
    lead.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        lead.setText("");
      }
    });

    JTextField date = new JTextField("Release Season: ");
    date.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        date.setText("");
      }
    });

    JTextField genre = new JTextField("Genre: ");
    genre.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        genre.setText("");
      }
    });

    JTextField franchise = new JTextField("Franchise and success? True, yes/no or False");
    franchise.addMouseListener(new MouseAdapter() {
      @Override
        public void mouseClicked(MouseEvent e) {
          franchise.setText("");
        }
    });

    JTextField budget = new JTextField("Low, Medium or High marketing budget? ");
    budget.addMouseListener(new MouseAdapter() {
      @Override
        public void mouseClicked(MouseEvent e) {
          budget.setText("");
        }
    });

    JPanel buttonPanel = new JPanel();

    JButton submitMovie = new JButton("Submit");
    submitMovie.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        float calculatedScore = calcScore(genre.getText(), lead.getText(), directorFirst.getText(),directorLast.getText(),date.getText(),franchise.getText(),budget.getText());
        addMovie(genre.getText(),name.getText(),directorFirst.getText(),directorLast.getText(),calculatedScore,date.getText(),franchise.getText(),lead.getText(),budget.getText());
        frame.remove(textPanel7);
        frame.remove(buttonPanel);
        prediction(name.getText(),calculatedScore);
      }
    });

    JButton back = new JButton("Back");
    back.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.remove(textPanel7);
        frame.remove(buttonPanel);
        options();
      }
    });

    textPanel7.add(name);
    textPanel7.add(directorFirst);
    textPanel7.add(directorLast);
    textPanel7.add(lead);
    textPanel7.add(date);
    textPanel7.add(genre);
    textPanel7.add(franchise);
    textPanel7.add(budget);
    buttonPanel.add(submitMovie);
    buttonPanel.add(back);

    frame.pack();
    frame.setSize(400,400);
    frame.getContentPane().add(BorderLayout.CENTER, textPanel7);
    frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
    frame.setVisible(true);
  }

//Function to search for directors in the database
  public void searchDirector() {
    frame.remove(textPanel5);
    labelPanel.removeAll();

    JLabel searchLabel = new JLabel("Enter name to find if director(s).");
    labelPanel.add(searchLabel);

    textPanel8 = new JPanel();
    textPanel8.setSize(200,200);

    JTextField first = new JTextField("First Name");
    textPanel8.add(first, BorderLayout.WEST);
    first.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        first.setText("");
      }
    });

    JTextField last = new JTextField("Last Name");
    textPanel8.add(last, BorderLayout.WEST);
    last.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        last.setText("");
      }
    });

    ImageIcon icon = new ImageIcon("img4.png");
    Image image = icon.getImage();
    Image newimg = image.getScaledInstance(200,200, java.awt.Image.SCALE_SMOOTH);
    icon = new ImageIcon(newimg);
    imageLabel.setIcon(icon);

    imagePanel.add(imageLabel);

    JButton submitSearch = new JButton("Search");
    submitSearch.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.remove(imagePanel);
        ArrayList<String> direct = findDirector(first.getText(), last.getText());
        directorsIn(direct, first.getText(), last.getText());
      }
    });
    textPanel8.add(submitSearch, BorderLayout.EAST);

    JButton back = new JButton("Back");
    back.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.remove(textPanel8);
        frame.remove(imagePanel);
        options();
      }
    });
    textPanel8.add(back);

    frame.pack();
    frame.setSize(400,400);
    frame.getContentPane().add(BorderLayout.CENTER, textPanel8);
    frame.getContentPane().add(BorderLayout.SOUTH, imagePanel);
    frame.setVisible(true);
  }

  public void directorsIn(ArrayList<String> d, String first, String last) {
    frame.remove(textPanel8);
    labelPanel.removeAll();

    JLabel searchLabel = new JLabel("Directors found in database.");
    labelPanel.add(searchLabel);

    textPanel12 = new JPanel();
    textPanel12.setSize(300,300);
    textPanel12.setLayout(new BoxLayout(textPanel12, BoxLayout.Y_AXIS));
    JPanel tPanel = new JPanel();
    tPanel.setSize(300,300);

    JScrollPane pane = new JScrollPane(textPanel12, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    DecimalFormat df = new DecimalFormat("###.##");

    for (int i = 0; i < d.size(); i++) {
      JTextArea txtArea = new JTextArea();
      txtArea.setEditable(false);
      textPanel12.add(txtArea);
      String score = Double.toString(directorScore(first, last));
      txtArea.setText(txtArea.getText() + d.get(i) + " Score: " + df.format(directorScore(first, last)) + "\n\n");
    }

    JButton back = new JButton("Back");
    back.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.remove(pane);
        frame.remove(tPanel);
        options();
      }
    });

    tPanel.add(back);

    frame.pack();
    frame.setSize(400,400);
    frame.getContentPane().add(pane);
    frame.getContentPane().add(tPanel,BorderLayout.SOUTH);
    frame.setVisible(true);
  }

//Function to add directors to database
  public void addDirectors() {
    frame.remove(textPanel5);
    labelPanel.removeAll();

    JLabel searchLabel = new JLabel("Add director's first and last name to database.");
    labelPanel.add(searchLabel);

    textPanel9 = new JPanel();
    textPanel9.setSize(300,300);

    ImageIcon icon = new ImageIcon("img5.png");
    Image image = icon.getImage();
    Image newimg = image.getScaledInstance(200,200, java.awt.Image.SCALE_SMOOTH);
    icon = new ImageIcon(newimg);
    imageLabel.setIcon(icon);

    imagePanel.add(imageLabel);

    JTextField first = new JTextField("First Name");
    textPanel9.add(first, BorderLayout.WEST);
    first.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        first.setText("");
      }
    });

    JTextField last = new JTextField("Last Name");
    textPanel9.add(last, BorderLayout.WEST);
    last.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        last.setText("");
      }
    });

    JButton submitSearch = new JButton("Add");
    submitSearch.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        addDirector(first.getText(), last.getText());
        directorAdded();
      }
    });
    textPanel9.add(submitSearch, BorderLayout.EAST);

    JButton back = new JButton("Back");
    back.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.remove(textPanel9);
        frame.remove(imagePanel);
        options();
      }
    });

    textPanel9.add(back);

    frame.pack();
    frame.setSize(400,400);
    frame.getContentPane().add(BorderLayout.SOUTH, imagePanel);
    frame.getContentPane().add(BorderLayout.CENTER, textPanel9);
    frame.setVisible(true);
  }

//Function to show director was added to database
  public void directorAdded() {
    frame.remove(textPanel9);
    labelPanel.removeAll();

    JLabel success = new JLabel("You added a director!");
    labelPanel.add(success);

    JButton back = new JButton("Back");
    back.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        options();
      }
    });

    labelPanel.add(back);

    frame.pack();
    frame.setSize(400,400);
    frame.setVisible(true);
  }

//Function to add lead actor or actress to database
  public void addLeads() {
    frame.remove(textPanel5);
    labelPanel.removeAll();

    JLabel searchLabel = new JLabel("Add lead's first and last name to database.");
    labelPanel.add(searchLabel);

    textPanel9 = new JPanel();
    textPanel9.setSize(300,300);

    ImageIcon icon = new ImageIcon("img5.png");
    Image image = icon.getImage();
    Image newimg = image.getScaledInstance(200,200, java.awt.Image.SCALE_SMOOTH);
    icon = new ImageIcon(newimg);
    imageLabel.setIcon(icon);

    imagePanel.add(imageLabel);

    JTextField first = new JTextField("Full Name");
    textPanel9.add(first, BorderLayout.WEST);
    first.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        first.setText("");
      }
    });

    JButton submitSearch = new JButton("Add");
    submitSearch.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        leadAdded();
        addLead(first.getText());
      }
    });
    textPanel9.add(submitSearch, BorderLayout.EAST);

    JButton back = new JButton("Back");
    back.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.remove(textPanel9);
        frame.remove(imagePanel);
        options();
      }
    });

    textPanel9.add(back);

    frame.pack();
    frame.setSize(400,400);
    frame.getContentPane().add(BorderLayout.SOUTH, imagePanel);
    frame.getContentPane().add(BorderLayout.CENTER, textPanel9);
    frame.setVisible(true);

  }

//Function to let user know they added a lead actor or actress
  public void leadAdded() {
    frame.remove(textPanel9);
    labelPanel.removeAll();

    JLabel success = new JLabel("You added a lead actor or actress!");
    labelPanel.add(success);

    JButton back = new JButton("Back");
    back.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        options();
      }
    });

    labelPanel.add(back);

    frame.pack();
    frame.setSize(400,400);
    frame.setVisible(true);
  }

//Function to look up leads in the database
  public void lookupLead() {
    frame.remove(textPanel5);
    labelPanel.removeAll();

    JLabel searchLabel = new JLabel("Enter name to see if lead is stored in database.");
    labelPanel.add(searchLabel);

    textPanel8 = new JPanel();
    textPanel8.setSize(300,300);

    ImageIcon icon = new ImageIcon("img4.png");
    Image image = icon.getImage();
    Image newimg = image.getScaledInstance(200,200, java.awt.Image.SCALE_SMOOTH);
    icon = new ImageIcon(newimg);
    imageLabel.setIcon(icon);

    imagePanel.add(imageLabel);

    JTextField first = new JTextField("Full Name");
    textPanel8.add(first, BorderLayout.WEST);
    first.addMouseListener(new MouseAdapter() {
    @Override
      public void mouseClicked(MouseEvent e) {
        first.setText("");
      }
    });

    JButton submitSearch = new JButton("Search");
    submitSearch.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.remove(imagePanel);
        ArrayList<String> lead = findLead(first.getText());
        leadsIn(lead);
      }
    });

    textPanel8.add(submitSearch, BorderLayout.EAST);

    JButton back = new JButton("Back");
    back.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.remove(textPanel8);
        frame.remove(imagePanel);
        options();
      }
    });

    textPanel8.add(back);

    frame.pack();
    frame.setSize(400,400);
    frame.getContentPane().add(BorderLayout.SOUTH, imagePanel);
    frame.getContentPane().add(BorderLayout.CENTER, textPanel8);
    frame.setVisible(true);

  }

//Function to get leads from database
  public void leadsIn(ArrayList<String> d) {
    frame.remove(textPanel8);
    labelPanel.removeAll();

    JLabel searchLabel = new JLabel("Leads found in database.");
    labelPanel.add(searchLabel);

    textPanel12 = new JPanel();
    textPanel12.setSize(300,300);
    textPanel12.setLayout(new BoxLayout(textPanel12, BoxLayout.Y_AXIS));
    JPanel tPanel = new JPanel();
    tPanel.setSize(300,300);

    JScrollPane pane = new JScrollPane(textPanel12, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    DecimalFormat df = new DecimalFormat("###.##");

    for (int i = 0; i < d.size(); i++) {
      JTextArea txtArea = new JTextArea();
      txtArea.setEditable(false);
      textPanel12.add(txtArea);
      txtArea.setText(txtArea.getText() + d.get(i) + " Score: " + df.format(leadScore(d.get(i))) + "\n\n");
    }

    JButton back = new JButton("Back");
    back.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.remove(pane);
        frame.remove(tPanel);
        options();
      }
    });

    tPanel.add(back);

    frame.pack();
    frame.setSize(400,400);
    frame.getContentPane().add(pane);
    frame.getContentPane().add(tPanel,BorderLayout.SOUTH);
    frame.setVisible(true);
  }

//Function to get three top genres
  public void genrePage() {
    frame.remove(textPanel5);
    labelPanel.removeAll();

    JLabel genreLabel = new JLabel("Current top genres");
    labelPanel.add(genreLabel);

    ArrayList<String> genres = topGenres();

    JPanel genrePanel = new JPanel();
    JTextArea genreText = new JTextArea();

    for (int i = 0; i < 3; i++) {
      genreText.setEditable(false);
      genreText.setText(genreText.getText() + genres.get(i) + "\n");
    }

    JPanel buttonPanel = new JPanel();
    JButton back = new JButton("Back");

    back.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.remove(genrePanel);
        frame.remove(buttonPanel);
        labelPanel.removeAll();
        options();
      }
    });

    buttonPanel.add(back);

    genrePanel.add(genreText);

    frame.pack();
    frame.setSize(400,400);
    frame.getContentPane().add(BorderLayout.CENTER, genrePanel);
    frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
    frame.setVisible(true);
  }

//Function to handle login errors
  public void loginError() {
    labelPanel.removeAll();
    frame.remove(textPanel);
    JPanel errorPanel = new JPanel();
    JTextArea errorText = new JTextArea("Sorry! Either incorrect login or username and password already taken. Please login again",3,18);
    errorText.setLineWrap(true);
    errorText.setWrapStyleWord(true);
    JButton errorButton = new JButton("Login");
    errorButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        make();
      }
    });
    errorPanel.add(errorText);
    errorPanel.add(errorButton);
    frame.pack();
    frame.setSize(400,400);
    frame.getContentPane().add(BorderLayout.CENTER, errorPanel);
    frame.setVisible(true);
  }

//Function to handle general errors
  public void generalError() {
    frame.removeAll();
    JPanel errorPanel = new JPanel();
    JLabel errorLabel = new JLabel();
    JButton errorButton = new JButton();
    errorButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        options();
      }
    });

    errorPanel.add(errorLabel);
    errorPanel.add(errorButton);
    frame.pack();
    frame.setSize(400,400);
    frame.getContentPane().add(BorderLayout.CENTER, errorPanel);
    frame.setVisible(true);
  }

//Function for movie prediction
  public void prediction(String name, float score) {
    JPanel predictionPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    JTextArea predictionText = new JTextArea("Predicted score for " + name + " is " + Float.toString(score));
    predictionText.setLineWrap(true);
    predictionText.setWrapStyleWord(true);

    JButton addAgain = new JButton("Add Again");
    addAgain.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.remove(predictionPanel);
        frame.remove(buttonPanel);
        add();
      }
    });

    JButton returnOptions = new JButton("Options");
    returnOptions.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent a) {
        frame.remove(predictionPanel);
        frame.remove(buttonPanel);
        options();
      }
    });

    predictionPanel.add(predictionText);
    buttonPanel.add(addAgain);
    buttonPanel.add(returnOptions);

    frame.pack();
    frame.setSize(400,400);
    frame.getContentPane().add(BorderLayout.CENTER, predictionPanel);
    frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
    frame.setVisible(true);

  }

}
