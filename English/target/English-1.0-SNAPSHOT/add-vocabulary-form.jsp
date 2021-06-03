<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 6/1/2021
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>

<head>
  <title>Add Student</title>

  <link type="text/css" rel="stylesheet" href="css/style.css">
  <link type="text/css" rel="stylesheet" href="css/add-vocabulary-style.css">
</head>

<body>
<div id="wrapper">
  <div id="header">
    <h2>Test MVC</h2>
  </div>
</div>

<div id="container">
  <h3>Add Vocabulary</h3>

  <form action="VocabularyControllerServlet" method="GET">

    <input type="hidden" name="command" value="ADD" />

    <table>
      <tbody>
      <tr>
        <td><label>Words:</label></td>
        <td><input type="text" name="words" /></td>
      </tr>

      <tr>
        <td><label>Pronunciation:</label></td>
        <td><input type="text" name="pronunciation" /></td>
      </tr>

      <tr>
        <td><label>Meaning:</label></td>
        <td><input type="text" name="meaning" /></td>
      </tr>

      <tr>
        <td><label>Status:</label></td>
        <td><input type="text" name="status" /></td>
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
    <a href="VocabularyControllerServlet">Back to List</a>
  </p>
</div>
</body>

</html>

