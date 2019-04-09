<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Comercio View</title>
		<link rel="stylesheet" type="text/css" href="WebContent/styles.css">
	</head>
	
	<body>
		<shiro:user>
		    Welcome back, <shiro:principal />! Click <a href="LogoutServlet">here</a> to logout.
		</shiro:user>
		
		<hr>
		
		<shiro:hasRole name="comerciante">
			<p><a href="ComercianteServlet">&lt; Volver a vista comerciante</a></p>
			
			<p><a href="PruebaChartServlet">Prueba chart</a></p>
			
			<form action="PruebaGoogleChartServlet" method="get">
				<input type="hidden" name="merchantid" value="${comercio.merchantId}"/>
				<button type="submit">Ver gráficas</button>
			</form>
			
			<h2>Vista de comercio</h2>
		
			<h3>Comercio</h3>
			<table border="1">
				<thead>
					<tr>
						<th>MerchantID</th>
						<th>Nombre comercio</th>
						<th>Sector</th>
						<th>CP</th>
						<th>Banco</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${comercio.merchantId}</td>
						<td>${comercio.nombreComercio}</td>
						<td>${comercio.sector}</td>
						<td>${comercio.cp}</td>
						<td>${comercio.banco}</td>
					</tr>
				</tbody>
			</table>
			<p>Nº ventas: ${num_ventas}</p>
			<p>Suma importe: ${suma_importe}</p>
			<p>Importe medio: ${importe_medio}</p>
			<p>Nº clientes distintos: ${num_clientes_distintos}</p>
			
			<h3>Ventas</h3>
			<table border="1">
				<thead>
					<tr>
						<th>Id</th>
						<th>Fecha</th>
						<th>Importe</th>
						<th>CP cliente</th>
						<th>Sexo cliente</th>
						<th>Edad cliente</th>
						<th>Id cliente</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${comercio.ventas}" var="venta">
						<tr>
							<td>${venta.id}</td>
							<td>${venta.fecha}</td>
							<td>${venta.importe}</td>
							<td>${venta.persona.cp}</td>
							<td>
								<c:if test="${venta.persona.sexo == 0}">Hombre</c:if>
								<c:if test="${venta.persona.sexo == 1}">Mujer</c:if>
							</td>
							<td>${venta.persona.edad}</td>
							<td>${venta.persona.id}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<h4>Distribución por horas</h4>
			<table border="1">
				<thead>
					<tr>
						<th>Hora</th>
						<th>Nº ventas</th>
						<th>Importe ventas</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${prueba}" var="pruebai">
						<tr>
							<td>${pruebai.key}</td>
							<td>${pruebai.value}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</shiro:hasRole>
		
		<shiro:lacksRole name="comerciante">
			<p>No eres comerciante</p>
		</shiro:lacksRole>
		
	</body>
</html>