<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Update car</title>
</head>
<body>
<h2>Update car:</h2>
<form name="update" method="post" action="update">
    <label>
        Fill car id:
        <input name="id" type="number">
    </label>
    <br/>
    <label>
        Fill new car vin:
        <input name="vin" type="text">
    </label>
    <br/>
    <label>
        Fill new car name:
        <input name="name" type="text">
    </label>
    <br/>
    <button>Send</button>
</form>
<br/>
Result: <%= request.getAttribute("resultMessage") %> <br/>
<br/>
<a href="index.jsp">RETURN TO TITLE PAGE</a><br/>
</body>
</html>
