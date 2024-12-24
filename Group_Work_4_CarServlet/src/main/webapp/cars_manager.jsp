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
<h2>Input car data here, and press button above to <ins>save</ins> car,
<br/>Or press one of the buttons near car record, to <ins>update</ins> it.</h2>
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
    <% List<CarDTO> cars = (List<CarDTO>) request.getAttribute("cars");
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
            <form id="delete" method="post" action="delete">
                <button formmethod="post" formaction="delete"
                        name="id" value="<%= car.getId() %>">
                    Delete
                </button>
            </form>
        </td>
        <td>
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