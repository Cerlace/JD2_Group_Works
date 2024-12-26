<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="itacademy.dto.CarDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="itacademy.utils.ServletConstants" %>
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

<a href="<%= ServletConstants.CARS_SAVE_SERVLET %>"><h1>SAVE CAR PAGE</h1></a><br/>
<br/>
<h1>Cars list:</h1>
<table>
    <tr>
        <td>
            Car ID
        </td>
        <td>
            VIN
        </td>
        <td>
            Car Name
        </td>
        <td>
            Change time
        </td>
        <td colspan="2">
            Action
        </td>
    </tr>
    <% List<CarDTO> cars = (List<CarDTO>) request.getAttribute(ServletConstants.CARS_LIST_ATTRIBUTE);
        for (CarDTO car : cars) {
    %>
    <tr>
        <td>
            <%= car.getId() %>
        </td>
        <td>
            <%= car.getVin() %>
        </td>
        <td>
            <%= car.getName() %>
        </td>
        <td>
            <%= car.getChangeTime() %>
        </td>
        <td>
            <form name="delete"
                  method="post"
                  action="<%= ServletConstants.CARS_DELETE_SERVLET %>">
                <button name="<%= ServletConstants.CAR_ID_PARAMETER %>"
                        value="<%= car.getId() %>">
                    Delete
                </button>
            </form>
        </td>
        <td>
            <form name="update"
                  method="get"
                  action="<%= ServletConstants.CARS_UPDATE_SERVLET %>">
            <button name="<%= ServletConstants.CAR_ID_PARAMETER %>"
                    value="<%= car.getId() %>">
                Update
            </button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>