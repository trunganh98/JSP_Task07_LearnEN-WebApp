package dome;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class VocabularyControllerServlet
 */

@WebServlet(name = "VocabularyControllerServlet", value = "/VocabularyControllerServlet")
public class VocabularyControllerServlet extends HttpServlet {

    private VocabularyDbUtil vocabularyDbUtil;
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        Context initContext = null;
        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/TestDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        // create our student db util ... and pass in the conn pool / datasource
        try {
            vocabularyDbUtil = new VocabularyDbUtil(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");
            // if the command is missing, then default to listing students
            if (theCommand == null) {
                theCommand = "LIST";
            }
            // route to the appropriate method
            switch (theCommand) {
                case "LIST":
                    listVocabulary(request, response);
                    break;
                case "ADD":
                    addVocabulary(request, response);
                    break;
                case "LOAD":
                    loadVocabulary(request, response);
                    break;
                case "UPDATE":
                    updateVocabulary(request, response);
                    break;
                case "DELETE":
                    deleteVocabulary(request, response);
                    break;
                default:
                    listVocabulary(request, response);
            }

        } catch (Exception exc) {
            throw new ServletException(exc);
        }

    }

    private void deleteVocabulary(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // read student id from form data
        String theVocabularyId = request.getParameter("vocabularyId");

        // delete student from database
        vocabularyDbUtil.deleteVocabulary(theVocabularyId);

        // send them back to "list vocabulary" page
        listVocabulary(request, response);
    }

    private void updateVocabulary(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read student info from form data
        int id = Integer.parseInt(request.getParameter("vocabularyId"));
        String words = request.getParameter("words");
        String pronunciation = request.getParameter("pronunciation");
        String meaning = request.getParameter("meaning");
        int status = Integer.parseInt(request.getParameter("status"));

        // create a new Vocabulary object
        Vocabulary theVocabulary = new Vocabulary(id, words, pronunciation, meaning, status);

        // perform update on database
        vocabularyDbUtil.updateVocabulary(theVocabulary);

        // send them back to the "list vocabulary" page
        listVocabulary(request, response);

    }

    private void loadVocabulary(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read Vocabulary id from form data
        String theVocabularyId = request.getParameter("vocabularyId");

        // get Vocabulary from database (db util)
        Vocabulary theVocabulary = vocabularyDbUtil.getVocabulary(theVocabularyId);

        // place Vocabulary in the request attribute
        request.setAttribute("THE_VOCABULARY", theVocabulary);

        // send to jsp page: update-vocabulary-form.jsp
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-vocabulary-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addVocabulary(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read student info from form data
        String words = request.getParameter("words");
        String pronunciation = request.getParameter("pronunciation");
        String meaning = request.getParameter("meaning");
        int status = Integer.parseInt(request.getParameter("status"));

        // create a new student object
        Vocabulary theVocabulary = new Vocabulary(words, pronunciation, meaning, status);

        // add the student to the database
        vocabularyDbUtil.addVocabulary(theVocabulary);

        // send back to main page (the student list)
        listVocabulary(request, response);
    }

    private void listVocabulary(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // get Vocabulary from db util
        List<Vocabulary> vocabularies = vocabularyDbUtil.getVocabularies();

        // add Vocabulary to the request
        request.setAttribute("VOCABULARY_LIST", vocabularies);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-vocabulary.jsp");
        dispatcher.forward(request, response);
    }

}
