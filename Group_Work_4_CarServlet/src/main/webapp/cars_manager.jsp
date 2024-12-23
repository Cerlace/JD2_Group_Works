<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="itacademy.dto.CarDTO" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Cars list</title>
    <style>
        table {
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
    </style>
</head>
<body>
<h2>Cars list:</h2>
<table>
    <tr>
        <td>Car ID
        </td>
        <td>VIN
        </td>
        <td>Car Name
        </td>
        <td>
            <form id="delete" method="post" action="delete">
                <button>delete</button>
            </form>
        </td>
    </tr>
    <% List<CarDTO> cars = (List<CarDTO>) request.getAttribute("cars");
        for (CarDTO car : cars) {
    %>
    <tr>
        <td><%= car.getId() %>
        </td>
        <td><%= car.getVin() %>
        </td>
        <td><%= car.getName() %>
        </td>
        <td>
            <label>
                <input form="delete" name="ids" type="checkbox"
                       value="<%= car.getId() %>">
            </label>
        </td>
    </tr>
    <%
        }
    %>
</table>
<br/>
Result: <%= request.getParameter("resultMessage") %> <br/>
<br/>
<a href="save">SAVE PAGE</a>
<br/>
<br/>
<a href="update">UPDATE PAGE</a>
</body>
</html>