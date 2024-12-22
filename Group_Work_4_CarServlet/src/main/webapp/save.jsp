<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Save car</title>
</head>
<body>
<h2>Save car:</h2>
<form name="save" method="post" action="save">
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
    <button>Send</button>
</form>
<br/>
Result: <%= request.getAttribute("resultMessage") %> <br/>
<br/>
<a href="index.jsp">RETURN TO TITLE PAGE</a><br/>
</body>
</html>
