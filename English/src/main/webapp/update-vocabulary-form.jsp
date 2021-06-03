<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 6/1/2021
  Time: 11:06 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>

<head>
    <title>Update Student</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>

<body>
<div id="wrapper">
    <div id="header">
        <h2>Test MVC</h2>
    </div>
</div>

<div id="container">
    <h3>Update Student</h3>

    <form action="VocabularyControllerServlet" method="GET">

        <input type="hidden" name="command" value="UPDATE" />

        <input type="hidden" name="studentId" value="${THE_VOCABULARY.id}" />

        <table>
            <tbody>
            <tr>
                <td><label>Words:</label></td>
                <td><input type="text" name="words"
                           value="${THE_VOCABULARY.words}" /></td>
            </tr>

            <tr>
                <td><label>Pronunciation:</label></td>
                <td><input type="text" name="pronunciation"
                           value="${THE_VOCABULARY.pronunciation}" /></td>
            </tr>

            <tr>
                <td><label>Meaning:</label></td>
                <td><input type="text" name="meaning"
                           value="${THE_VOCABULARY.meaning}" /></td>
            </tr>

            <tr>
                <td><label>Status:</label></td>
                <td><input type="text" name="status"
                           value="${THE_VOCABULARY.status}" /></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>

            </tbody>
        </table>
    </form>

    <div style="clear: both;"></div>

    <p>
        <a href="StudentControllerServlet">Back to List</a>
    </p>
</div>
</body>

</html>












