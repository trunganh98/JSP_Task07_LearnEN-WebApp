<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <title>Vocabulary Tracker App</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>Trung Anh Web</h2>
    </div>
</div>

<div id="container">

    <div id="content">

        <!-- put new button: Add Vocabulary -->

        <input type="button" value="Add Vocabulary"
               onclick="window.location.href='add-vocabulary-form.jsp'; return false;"
               class="add-vocabulary-button"
        />

        <table>

            <tr>
                <th>Words</th>
                <th>Pronunciation</th>
                <th>Meaning</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <c:forEach var="tempVocabulary" items="${VOCABULARY_LIST}">

                <!-- set up a link for each student -->
                <c:url var="tempLink" value="VocabularyControllerServlet">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="vocabularyId" value="${tempVocabulary.id}"/>
                </c:url>

                <!-- set up a link to delete a student -->
                <c:url var="deleteLink" value="VocabularyControllerServlet">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="vocabulary" value="${tempVocabulary.id}"/>
                </c:url>

                <tr>
                    <td> ${tempVocabulary.words} </td>
                    <td> ${tempVocabulary.pronunciation} </td>
                    <td> ${tempVocabulary.meaning} </td>
                    <td> ${tempVocabulary.status} </td>
                    <td>
                        <a href="${tempLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this vocabulary?'))) return false">
                            Delete</a>
                    </td>
                </tr>

            </c:forEach>

        </table>

    </div>

</div>
</body>


</html>

