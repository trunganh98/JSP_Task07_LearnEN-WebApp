package dome;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class VocabularyDbUtil {

    private DataSource dataSource;

    public VocabularyDbUtil(DataSource theDataSource) { dataSource = theDataSource; }

    public List<Vocabulary> getVocabularies() throws Exception{
        List<Vocabulary> vocabularies = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "select * from english order by pronunciation";

            myStmt = myConn.createStatement();

            myRs = myStmt.executeQuery(sql);

            while (myRs.next()) {
                int id = myRs.getInt("id");
                String words = myRs.getString("words");
                String pronunciation = myRs.getString("pronunciation");
                String meaning = myRs.getString("meaning");
                int status = myRs.getInt("status");

                Vocabulary tempVocabulary = new Vocabulary(id, words, pronunciation, meaning, status);

                vocabularies.add(tempVocabulary);
            }
            return vocabularies;
        }
        finally {
            close(myConn, myStmt, myRs);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void addVocabulary(Vocabulary theVocabulary) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "insert into english "
                    + "(words, pronunciation, meaning, status)"
                    + "value (?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, theVocabulary.getWords());
            myStmt.setString(2, theVocabulary.getPronunciation());
            myStmt.setString(3, theVocabulary.getMeaning());
            myStmt.setInt(4, theVocabulary.getStatus());

            myStmt.execute();
        }
        finally {
            close(myConn, myStmt, null);
        }

    }

    public Vocabulary getVocabulary(String theVocabularyId) throws Exception {

        Vocabulary theVocabulary = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int vocabularyId;

        try {
            vocabularyId = Integer.parseInt(theVocabularyId);

            myConn = dataSource.getConnection();

            String sql = "select * from english where id=?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setInt(1, vocabularyId);

            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                String words = myRs.getString("words");
                String pronunciation = myRs.getString("pronunciation");
                String meaning = myRs.getString("meaning");
                Integer status = myRs.getInt("status");

                theVocabulary = new Vocabulary(vocabularyId, words, pronunciation, meaning, status);
            }
            else {
                throw new Exception("Could not find vocabulary id: " + vocabularyId);
            }

            return theVocabulary;
        }
        finally {
            close(myConn, myStmt, myRs);
        }
    }

    public void updateVocabulary(Vocabulary theVocabulary) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();

            // create SQL update statement
            String sql = "update english "
                    + "set words=?, pronunciation=?, pronunciation=?, ststus=? "
                    + "where id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setString(1, theVocabulary.getWords());
            myStmt.setString(2, theVocabulary.getPronunciation());
            myStmt.setString(3, theVocabulary.getMeaning());
            myStmt.setInt(4, theVocabulary.getStatus());
            myStmt.setInt(5, theVocabulary.getId());

            // execute SQL statement
            myStmt.execute();
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

    public void deleteVocabulary(String theVocabularyId) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // convert student id to int
            int vocabularyId = Integer.parseInt(theVocabularyId);

            // get connection to database
            myConn = dataSource.getConnection();

            // create sql to delete student
            String sql = "delete from english where id=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setInt(1, vocabularyId);

            // execute sql statement
            myStmt.execute();
        }
        finally {
            // clean up JDBC code
            close(myConn, myStmt, null);
        }
    }

    public void searchVocabulary(Vocabulary theVocabulary) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();

            // create SQL update statement
            String sql = "select  * from english "
                    + " words=?, pronunciation=?, pronunciation=?, ststus=? "
                    + "where words=?";

            // prepare statement
            myStmt = myConn.prepareStatement(sql);

            // set params
            myStmt.setString(1, theVocabulary.getWords());
            myStmt.setString(2, theVocabulary.getPronunciation());
            myStmt.setString(3, theVocabulary.getMeaning());
            myStmt.setInt(4, theVocabulary.getStatus());
            myStmt.setInt(5, theVocabulary.getId());

            // execute SQL statement
            myStmt.execute();
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }

}
