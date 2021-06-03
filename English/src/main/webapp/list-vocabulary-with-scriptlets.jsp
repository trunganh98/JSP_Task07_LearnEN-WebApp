<%@ page import="java.util.*,dome.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Vocabulary Tracker App</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<%!
    private Vocabulary tempVocabulary;
%><%
    // get the students from the request object (sent by servlet)
    List<Vocabulary> theVocabulary =
            (List<Vocabulary>) request.getAttribute("VOCABULARY_LIST");
%>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Trung Anh Web</h2>
    </div>
</div>
<div id="container">
    <div id="content">
        <table>
            <tr>
                <th>Words</th>
                <th>Pronunciation</th>
                <th>Meaning</th>
                <th>Status</th>
            </tr>
            <% for (Vocabulary tempVocabulary : theVocabulary { %>
            <tr>
                <td> <%= tempVocabulary.getWords() %> </td>
                <td> <%= tempVocabulary.getPronunciation() %> </td>
                <td> <%= tempVocabulary.getMeaning() %> </td>
                <td> <%= tempVocabulary.getStatus() %> </td>
            </tr>
            <% } %>
        </table>
    </div>
</div>
</body>
</html>
