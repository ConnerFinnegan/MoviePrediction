import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;

public class UserSql {
  private Connection conn = null;
  private void connect() {
    //database parameters
    String path = "jdbc:sqlite:movies.db";

    try {
      //create database Connection
      conn = DriverManager.getConnection(path);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public Connection getConnection() {
    return conn;
  }

  //Function to add new users to the database
  public int addUser(String name, String pass) {
      this.connect();
      String sql = "INSERT INTO users(username,password) VALUES(?,?)";
      int val = 0;
      try (Connection conn = this.getConnection();
              PreparedStatement pstmt = conn.prepareStatement(sql)) {
          pstmt.setString(1, name);
          pstmt.setString(2, pass);
          pstmt.executeUpdate();
          val = 1;
      } catch (SQLException e) {
          System.out.println(e.getMessage());
          val = 0;
      }
    return val;
  }

  //checks for a valid user
  public int validUser(String name, String pass) {
      this.connect();
      String sql = "SELECT username, password FROM users WHERE username = ? AND password = ?";
      int val = 0;
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
               pstmt.setString(1, name);
               pstmt.setString(2, pass);
               ResultSet rs = pstmt.executeQuery();
               if (rs.next()) {
                 System.out.println("Good To Go");
                 val = 1;
               } else {
                 System.out.println("Not a valid username or password!");
                 val = 0;
               }
             } catch (SQLException e) {
                 System.out.println(e.getMessage());
             }
      return val;
  }

  //returns genreID for movies table
  public int getGenreID(String gen) {
    this.connect();
    String sqlGenre = "SELECT genreID FROM genres WHERE genres.genreType = ?";
    int genID = 0;

    try (Connection conn = this.getConnection();
         PreparedStatement genre = conn.prepareStatement(sqlGenre)) {
           genre.setString(1,gen);
           ResultSet rsG = genre.executeQuery();
           //gets genreID
           if (rsG.next()) {
             genID = rsG.getInt("genreID");
           } else {
             System.out.println("Genre not in database!");
             genID = addGenre(gen);
           }
         } catch(SQLException e) {
               System.out.println(e.getMessage());
         }
     return genID;
  }

  //Adds new director to directors table
  public int addGenre(String g) {
    this.connect();
    String sql = "INSERT INTO genres(genreType,score) VALUES(?,?)";
    int id = 0;
    try (Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, g);
        pstmt.setDouble(2, 0.0);
        pstmt.executeUpdate();
        id = getGenreID(g);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return id;
  }

  //Gets genre type from genreID in movies table
  public String getGenreName(int g) {
    this.connect();
    String sqlGenre = "SELECT genreType FROM genres WHERE genreID = ?";
    String genreName = "";

    try (Connection conn = this.getConnection();
         PreparedStatement genre = conn.prepareStatement(sqlGenre)) {
           genre.setInt(1,g);
           ResultSet rsG = genre.executeQuery();
           //gets genreID
           if (rsG.next()) {
             genreName = rsG.getString("genreType");
           } else {
             System.out.println("Genre not in database!");
           }
         } catch(SQLException e) {
               System.out.println(e.getMessage());
         }
     return genreName;
  }

  //Adds new director to directors table
  public int addDirector(String fname, String lname) {
    this.connect();
    String sql = "INSERT INTO directors(firstName,lastName) VALUES(?,?)";
    int id = 0;
    try (Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, fname);
        pstmt.setString(2, lname);
        pstmt.executeUpdate();
        id = getDirectorID(fname,lname);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return id;
  }

  //Gets directorID from directors table for movies table
  public int getDirectorID(String dF, String dL) {
    this.connect();
    String sqlDirector = "SELECT directorID FROM directors WHERE directors.firstName = ? AND directors.lastName = ?";
    int dID = 0;

    try (Connection conn = this.getConnection();
         PreparedStatement director = conn.prepareStatement(sqlDirector)) {
           director.setString(1, dF);
           director.setString(2, dL);
           ResultSet rsD = director.executeQuery();
           if (rsD.next()) {
             dID = rsD.getInt("directorID");
           } else {
             System.out.println("Director not in database!");
             dID = addDirector(dF,dL);
           }
         } catch(SQLException e) {
               System.out.println(e.getMessage());
           }
    return dID;
  }

  //Gets directors name from directors table using directorID in movie table
  public String getDirectorName(int d) {
    this.connect();
    String sqlDirector = "SELECT firstName,lastName FROM directors WHERE directorID = ?";
    String dir = "";

    try (Connection conn = this.getConnection();
         PreparedStatement director = conn.prepareStatement(sqlDirector)) {
           director.setInt(1, d);
           ResultSet rsD = director.executeQuery();
           if (rsD.next()) {
             dir = rsD.getString("firstName") + " " + rsD.getString("lastName");
           } else {
             System.out.println("Director not in database!");
           }
         } catch(SQLException e) {
               System.out.println(e.getMessage());
           }
    return dir;
  }

  //Adds new lead to leads table
  public int addLead(String lead) {
    this.connect();
    String sql = "INSERT INTO leads(name) VALUES(?)";
    int id = 0;
    try (Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, lead);
        pstmt.executeUpdate();
        id = getLeadID(lead);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return id;
  }

  //gets leadID from leads table for movies table
  public int getLeadID(String l) {
    this.connect();
    String sqlLead = "SELECT leadID FROM leads WHERE name = ?";
    int lID = 0;

    try (Connection conn = this.getConnection();
         PreparedStatement lead = conn.prepareStatement(sqlLead)) {
           lead.setString(1, l);
           ResultSet rsL = lead.executeQuery();
           if (rsL.next()) {
             lID = rsL.getInt("leadID");
           } else {
             System.out.println("Lead not in database!");
             lID = addLead(l);
           }
         } catch(SQLException e) {
               System.out.println(e.getMessage());
           }
    return lID;
  }

  //Gets directors name from directors table using directorID in movie table
  public String getLeadName(int l) {
    this.connect();
    String sqlLead = "SELECT name FROM leads WHERE leadID = ?";
    String leadName = "";

    try (Connection conn = this.getConnection();
         PreparedStatement lead = conn.prepareStatement(sqlLead)) {
           lead.setInt(1, l);
           ResultSet rsL = lead.executeQuery();
           if (rsL.next()) {
             leadName = rsL.getString("name");
           } else {
             System.out.println("Lead not in database!");
           }
         } catch(SQLException e) {
               System.out.println(e.getMessage());
           }
    return leadName;
  }

  public void addMovie(String gen, String title, String dFirstname, String dLastname, float score, String date, String fran, String lead, String budget) {
      this.connect();
      String insertSql = "INSERT INTO movies(genreID,title,directorID,releaseDate,score,franchise,leadID,budget) VALUES(?,?,?,?,?,?,?,?)";

      try (Connection conn = this.getConnection();
          PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            int dID = getDirectorID(dFirstname, dLastname);
            int genID = getGenreID(gen);
            int lID = getLeadID(lead);

            pstmt.setInt(1, genID);
            pstmt.setString(2, title);
            pstmt.setInt(3,dID);
            pstmt.setString(4,date);
            pstmt.setDouble(5,score);
            pstmt.setString(6,fran);
            pstmt.setInt(7,lID);
            pstmt.setString(8,budget);
            pstmt.executeUpdate();
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
  }

  public ArrayList<String> findMovie(String movieName) {
    this.connect();
    String likeMovie = "%"+movieName+"%";
    String sqlMovie = "SELECT genreID,directorID,leadID,title,releaseDate,franchise,ROUND(score,1) FROM movies WHERE title LIKE ?";
    ArrayList<String> movieList = new ArrayList<String>();
    try (Connection conn = this.getConnection();
         PreparedStatement movie = conn.prepareStatement(sqlMovie)) {
           movie.setString(1, likeMovie);
           ResultSet rs = movie.executeQuery();
           while (rs.next()) {
             String genre = getGenreName(rs.getInt("genreID"));
             String director = getDirectorName(rs.getInt("directorID"));
             String lead = getLeadName(rs.getInt("leadID"));
             String mov = "Genre: " + genre +
                    "\nTitle: " + rs.getString("title") +
                    "\nDirector: " + director +
                    "\nRelease Date: " + rs.getString("releaseDate") +
                    "\nFranchise: " + rs.getString("franchise") +
                    "\nLead Actor/Actress: " + lead +
                    "\nRating: " +rs.getDouble(7);
             movieList.add(mov);
           }
         } catch(SQLException e) {
               System.out.println(e.getMessage());
         }
     return movieList;
  }

  public ArrayList<String> findDirector(String first, String last) {
    this.connect();
    String fname = "%"+first+"%";
    String lname = "%"+last+"%";
    String sqlDirector = "SELECT * FROM directors WHERE firstName LIKE ? AND lastName LIKE ?";
    ArrayList<String> dir = new ArrayList<String>();
    try (Connection conn = this.getConnection();
         PreparedStatement director = conn.prepareStatement(sqlDirector)) {
           director.setString(1, fname);
           director.setString(2, lname);
           ResultSet rs = director.executeQuery();
           while (rs.next()) {
             String name = (rs.getString("firstName") + " " + rs.getString("lastName"));
             dir.add(name);
           }
         } catch(SQLException e) {
               System.out.println(e.getMessage());
         }
     return dir;
  }

  public ArrayList<String> findLead(String n) {
    this.connect();
    String name = "%"+n+"%";
    String sqlLead = "SELECT * FROM leads WHERE name LIKE ?";
    ArrayList<String> lead = new ArrayList<String>();
    try (Connection conn = this.getConnection();
         PreparedStatement l = conn.prepareStatement(sqlLead)) {
           l.setString(1, name);
           ResultSet rs = l.executeQuery();
           while (rs.next()) {
             String nameL = (rs.getString("name"));
             lead.add(nameL);
           }
         } catch(SQLException e) {
               System.out.println(e.getMessage());
         }
     return lead;
  }

  public float calcScore(String g, String l, String dF, String dL, String rel, String fran, String bud) {
    int gID = getGenreID(g);
    int lID = getLeadID(l);
    int dID = getDirectorID(dF, dL);
    this.connect();
    String sqlGenreAvg = "SELECT AVG(score),genreID FROM movies WHERE genreID = ?";
    String sqlLeadAvg = "SELECT AVG(score),leadID FROM movies WHERE leadID = ?";
    String sqlDirectorAvg = "SELECT AVG(score),directorID FROM movies WHERE directorID = ?";
    float cScore = 0;
    int bool = 0;
    int count = 0;
    try (Connection conn = this.getConnection();
         PreparedStatement genreScore = conn.prepareStatement(sqlGenreAvg)) {
           genreScore.setInt(1, gID);
           ResultSet genreRS = genreScore.executeQuery();
           genreRS.next();

           PreparedStatement leadScore = conn.prepareStatement(sqlLeadAvg);
      	   leadScore.setInt(1, lID);
      	   ResultSet leadRS = leadScore.executeQuery();
           leadRS.next();

           PreparedStatement directorScore = conn.prepareStatement(sqlDirectorAvg);
      	   directorScore.setInt(1, dID);
      	   ResultSet directorRS = directorScore.executeQuery();
           directorRS.next();

           if (genreRS.getInt("genreID") > 0 && genreRS.getInt("genreID") < 18) {
             cScore += genreRS.getDouble(1);
             count++;
           } else {
             bool++;
           }

      	   if (leadRS.getInt("leadID") > 0 && leadRS.getInt("leadID") < 120) {
      	      cScore += leadRS.getDouble(1);
              count++;
            } else {
              bool++;
            }

      	   if (directorRS.getInt("directorID") > 0 && directorRS.getInt("directorID") < 119) {
      	      cScore += directorRS.getDouble(1);
              count++;
           } else {
             bool++;
           }

           if (bool >= 2) {
             String sqlExtraAvg = "SELECT AVG(score) FROM movies WHERE releaseDate = ? OR franchise = ? OR budget = ?";
             PreparedStatement extraScore = conn.prepareStatement(sqlExtraAvg);
             extraScore.setString(1, rel);
             extraScore.setString(2, fran);
             extraScore.setString(3, bud);
             ResultSet extraRS = extraScore.executeQuery();

             cScore += extraRS.getDouble(1);
             count++;
           }
           cScore = cScore / count;
         } catch(SQLException e) {
           System.out.println(e.getMessage());
         }
     return cScore;
  }

  public double directorScore(String fname, String lname) {
    int dID = getDirectorID(fname, lname);
    this.connect();
    String sqlDirectorAvg = "SELECT AVG(score) FROM movies WHERE directorID = ?";
    double dScore = 0;
    try (Connection conn = this.getConnection();
         PreparedStatement director = conn.prepareStatement(sqlDirectorAvg)) {
           director.setInt(1, dID);
           ResultSet d = director.executeQuery();
           d.next();
           dScore += d.getDouble(1);
    }  catch(SQLException e) {
          System.out.println(e.getMessage());
    }
    return dScore;
  }

  public double leadScore(String name) {
    int lID = getLeadID(name);
    this.connect();
    String sqlLeadAvg = "SELECT AVG(score) FROM movies WHERE leadID = ?";
    double lScore = 0;
    try (Connection conn = this.getConnection();
         PreparedStatement lead = conn.prepareStatement(sqlLeadAvg)) {
           lead.setInt(1, lID);
           ResultSet l = lead.executeQuery();
           l.next();
           lScore += l.getDouble(1);
    } catch(SQLException e) {
          System.out.println(e.getMessage());
    }
    return lScore;
  }

  public ArrayList<String> topGenres() {
    this.connect();
    String sqlGenres = "SELECT genreID, COUNT(genreID) AS val_occ FROM movies GROUP BY genreID ORDER BY val_occ DESC LIMIT 3";
    ArrayList<String> topGenresList = new ArrayList<String>();
    try (Connection conn = this.getConnection();
         PreparedStatement gen = conn.prepareStatement(sqlGenres)) {
           ResultSet g = gen.executeQuery();
           while (g.next()) {
             topGenresList.add(getGenreName(g.getInt(1)));
           }
    } catch(SQLException e) {
      System.out.println(e.getMessage());
    }
    for (int i = 0; i < topGenresList.size(); i++) {
      String test = topGenresList.get(i);
      System.out.println(test);
    }
    return topGenresList;
  }
}
