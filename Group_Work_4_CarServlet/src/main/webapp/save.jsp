<%@ page import="itacademy.utils.ServletConstants" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Save car</title>
</head>
<body>
<h2>Save car:</h2>
<form name="save"
      method="post"
      action="<%= ServletConstants.CARS_SAVE_SERVLET %>">
    <label>
        Fill car vin:
        <input name="<%= ServletConstants.CAR_VIN_PARAMETER %>" type="text" required>
    </label>
    <br/>
    <label>
        Fill car name:
        <input name="<%= ServletConstants.CAR_NAME_PARAMETER %>" type="text" required>
    </label>
    <br/>
    <button>Send</button>
</form>
<br/>
<a href=<%= ServletConstants.CARS_LIST_SERVLET %>>RETURN TO LIST CARS</a><br/>
</body>
</html>