<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<title>Teste</title>
</head>
<body>

<br>
	${request}
	<br>
	<c:forEach var="i" begin="1" end="3">
   		Item ${i} <br>
	</c:forEach>
</body>
</html>