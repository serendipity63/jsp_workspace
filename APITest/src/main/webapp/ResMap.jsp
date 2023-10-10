<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.map_wrap {
	width: 100%;
	position: relative;
}

#map {
	width: 100%;
	height: 350px;
	margin-top: 10px;
}
</style>
</head>
<body>
<div class="map_wrap">
	<div id="map" style="width:100%;height:350px;"></div>
</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0f92754065fd18fb9b2450d8077e930c&libraries=services,drawing"></script>
<script>

	//Drawing Manager에서 데이터를 가져와 도형을 표시할 아래쪽 지도 div
	var mapContainer = document.getElementById('map'), mapOptions = {
		center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		level : 3
	// 지도의 확대 레벨
	};

	// 지도 div와 지도 옵션으로 지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOptions), overlays = []; // 지도에 그려진 도형을 담을 배열

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	function addressSearch(address) {
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(address, function(result, status) {

			// 정상적으로 검색이 완료됐으면 
			if (status === kakao.maps.services.Status.OK) {

				var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

				// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				map.setCenter(coords);
			}
		});
	}
	
	// 가져오기 버튼을 클릭하면 호출되는 핸들러 함수입니다
	// Drawing Manager로 그려진 객체 데이터를 가져와 아래 지도에 표시합니다
	function getDataFromDrawingMap(marker) {
		var data = marker;
		console.log(data);
		// 아래 지도에 그려진 도형이 있다면 모두 지웁니다
		removeOverlays();

		// 지도에 가져온 데이터로 도형들을 그립니다
		drawMarker(data[kakao.maps.drawing.OverlayType.MARKER]);
		drawPolyline(data[kakao.maps.drawing.OverlayType.POLYLINE]);
		drawRectangle(data[kakao.maps.drawing.OverlayType.RECTANGLE]);
		drawCircle(data[kakao.maps.drawing.OverlayType.CIRCLE]);
		drawPolygon(data[kakao.maps.drawing.OverlayType.POLYGON]);
	}

	// 아래 지도에 그려진 도형이 있다면 모두 지웁니다
	function removeOverlays() {
		var len = overlays.length, i = 0;

		for (; i < len; i++) {
			overlays[i].setMap(null);
		}

		overlays = [];
	}

	// Drawing Manager에서 가져온 데이터 중 마커를 아래 지도에 표시하는 함수입니다
	function drawMarker(markers) {
		console.log(markers);
		var len = markers.length, i = 0;

		for (; i < len; i++) {
			var marker = new kakao.maps.Marker({
				map : map,
				position : new kakao.maps.LatLng(markers[i].y, markers[i].x),
				zIndex : markers[i].zIndex
			});

			overlays.push(marker);
		}
	}

	// Drawing Manager에서 가져온 데이터 중 선을 아래 지도에 표시하는 함수입니다
	function drawPolyline(lines) {
		var len = lines.length, i = 0;

		for (; i < len; i++) {
			var path = pointsToPath(lines[i].points);
			var style = lines[i].options;
			var polyline = new kakao.maps.Polyline({
				map : map,
				path : path,
				strokeColor : style.strokeColor,
				strokeOpacity : style.strokeOpacity,
				strokeStyle : style.strokeStyle,
				strokeWeight : style.strokeWeight
			});

			overlays.push(polyline);
		}
	}

	// Drawing Manager에서 가져온 데이터 중 사각형을 아래 지도에 표시하는 함수입니다
	function drawRectangle(rects) {
		var len = rects.length, i = 0;

		for (; i < len; i++) {
			var style = rects[i].options;
			var rect = new kakao.maps.Rectangle({
				map : map,
				bounds : new kakao.maps.LatLngBounds(new kakao.maps.LatLng(
						rects[i].sPoint.y, rects[i].sPoint.x),
						new kakao.maps.LatLng(rects[i].ePoint.y,
								rects[i].ePoint.x)),
				strokeColor : style.strokeColor,
				strokeOpacity : style.strokeOpacity,
				strokeStyle : style.strokeStyle,
				strokeWeight : style.strokeWeight,
				fillColor : style.fillColor,
				fillOpacity : style.fillOpacity
			});

			overlays.push(rect);
		}
	}

	// Drawing Manager에서 가져온 데이터 중 원을 아래 지도에 표시하는 함수입니다
	function drawCircle(circles) {
		var len = circles.length, i = 0;

		for (; i < len; i++) {
			var style = circles[i].options;
			var circle = new kakao.maps.Circle({
				map : map,
				center : new kakao.maps.LatLng(circles[i].center.y,
						circles[i].center.x),
				radius : circles[i].radius,
				strokeColor : style.strokeColor,
				strokeOpacity : style.strokeOpacity,
				strokeStyle : style.strokeStyle,
				strokeWeight : style.strokeWeight,
				fillColor : style.fillColor,
				fillOpacity : style.fillOpacity
			});

			overlays.push(circle);
		}
	}

	// Drawing Manager에서 가져온 데이터 중 다각형을 아래 지도에 표시하는 함수입니다
	function drawPolygon(polygons) {
		var len = polygons.length, i = 0;

		for (; i < len; i++) {
			var path = pointsToPath(polygons[i].points);
			var style = polygons[i].options;
			var polygon = new kakao.maps.Polygon({
				map : map,
				path : path,
				strokeColor : style.strokeColor,
				strokeOpacity : style.strokeOpacity,
				strokeStyle : style.strokeStyle,
				strokeWeight : style.strokeWeight,
				fillColor : style.fillColor,
				fillOpacity : style.fillOpacity
			});

			overlays.push(polygon);
		}
	}

	// Drawing Manager에서 가져온 데이터 중 
	// 선과 다각형의 꼭지점 정보를 kakao.maps.LatLng객체로 생성하고 배열로 반환하는 함수입니다 
	function pointsToPath(points) {
		var len = points.length, path = [], i = 0;

		for (; i < len; i++) {
			var latlng = new kakao.maps.LatLng(points[i].y, points[i].x);
			path.push(latlng);
		}

		return path;
	}
	
 	var address = '${res.address}';
	var drawStr = '${res.draw}';
	var draw = JSON.parse(drawStr);
	console.log(address);
	console.log(draw);
 	addressSearch(address);
	getDataFromDrawingMap(draw);
</script>
</body>
</html>