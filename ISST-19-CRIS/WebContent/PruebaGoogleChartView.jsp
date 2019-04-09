<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Prueba Google Chart View</title>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function() {
				$.ajax({
					url: "PruebaGoogleChartServlet",
					dataType: "JSON",
					success: function(result) {
						google.charts.load('current', {
							'packages': ['corechart']
						});
						google.charts.setOnLoadCallback(function() {
							drawChart(result);
						});
					}
				});
				
				function drawChart(result) {
					var data = new google.visualization.DataTable();
					data.addColumn('string', 'Name');
					data.addColumn('number', 'Quantity');
					var dataArray = [];
					$.each(result, function(i, obj) {
						dataArray.push([obj.name, obj.quantity]);
					});
					
					data.addRows(dataArray);
					
					var piechart_options = {
						title: 'Pie Chart: How Much Products Sold By Last Night',
						width: 400,
						height: 300
					};
					var piechart = new google.visualization.PieChart(document.getElementById('piechart_div'));
					piechart.draw(data, piechart_options);
					
					var barchart_options = {
							title: 'Barchart: How Much Products Sold By Last Night',
							width: 400,
							height: 300,
							legend: 'none'
						};
					var barchart = new google.visualization.BarChart(document.getElementById('barchart_div'));
					barchart.draw(data, barchart_options);
				}
				
			});
			
		</script>
	</head>
	
	<body>

		<table class="columns">
			<tr>
				<td><div id="piechart_div" style="border: 1 px solid #ccc"></div></td>
				<td><div id="barchart_div" style="border: 1 px solid #ccc"></div></td>
			</tr>
		</table>
		
		
		
	</body>
</html>