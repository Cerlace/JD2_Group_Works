<%@ page contentType="text/html;charset=UTF-8"%>
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
<h1>Save or update car:</h1>
<h2>Input car data here, and press button above to save car, or one of the buttons near car record, to update it.</h2>
<form id="saveOrUpdate" method="post">
    <label>
        Fill car vin:
        <input name="vin" type="text" required>
    </label>
    <br/>
    <label>
        Fill car name:
        <input name="name" type="text" required>
    </label>
    <br/>
    <button formaction="save">Save</button>
</form>
<br/>
Result: <%= request.getParameter("resultMessage") %> <br/>
<br/>
<h1>Cars list:</h1>
<table>
    <tr>
        <td>Car ID
        </td>
        <td>VIN
        </td>
        <td>Car Name
        </td>
        <td>
            Action
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
            <form id="delete" method="post" action="delete">
                <button name="id" value="<%= car.getId() %>">
                    Delete
                </button>
            </form>
                <button form="saveOrUpdate" formaction="update"
                        name="id" value="<%= car.getId() %>">
                    Update
                </button>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
