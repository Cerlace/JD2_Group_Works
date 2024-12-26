<%@ page import="itacademy.utils.ServletConstants" %>
<%@ page import="itacademy.dto.CarDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Update car</title>
</head>
<body>
<% CarDTO car = (CarDTO) request.getAttribute(ServletConstants.CAR_ATTRIBUTE); %>
<h2>Update car:</h2>
<form name="update"
      method="post"
      action="<%= ServletConstants.CARS_UPDATE_SERVLET %>">
    <input name="<%= ServletConstants.CAR_ID_PARAMETER %>"
           type="hidden"
           value="<%= car.getId() %>"
           required>
    <label>
        Fill new car vin:
        <input name="<%= ServletConstants.CAR_VIN_PARAMETER %>"
               type="text"
               value="<%= car.getVin() %>"
               required>
    </label>
    <br/>
    <label>
        Fill new car name:
        <input name="<%= ServletConstants.CAR_NAME_PARAMETER %>"
               type="text"
               value="<%= car.getName() %>"
               required>
    </label>
    <br/>
    <button>Send</button>
</form>
<br/>
<a href="<%= ServletConstants.CARS_LIST_SERVLET %>">RETURN TO LIST CARS</a><br/>
</body>
</html>