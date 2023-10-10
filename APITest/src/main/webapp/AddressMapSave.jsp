<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

.modes {
	position: absolute;
	top: 10px;
	left: 10px;
	z-index: 1;
}

#drawingMap, #map {
	width: 100%;
	height: 350px;
}

#map {
	margin-top: 10px;
}
</style>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                addressSearch(addr);
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("detailAddress").focus();
            }
        }).open();
    }
    
    
</script>
</head>
<body>

<input type="text" id="postcode" placeholder="우편번호">
<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
<input type="text" id="address" placeholder="주소"><br>
<input type="text" id="detailAddress" placeholder="상세주소">
<input type="text" id="extraAddress" placeholder="참고항목">
<br>
<div class="map_wrap">
	<div id="map" style="width:100%;height:350px;"></div>
	<p class="modes">
	    <button onclick="selectOverlay('MARKER')">마커</button>
	    <button onclick="selectOverlay('POLYLINE')">선</button>
	    <button onclick="selectOverlay('CIRCLE')">원</button>
	    <button onclick="selectOverlay('RECTANGLE')">사각형</button>
	    <button onclick="selectOverlay('POLYGON')">다각형</button>	
	</p>
	<form id="regForm" action="mapreg" method="post">
		<input type="hidden" name="address" id="form_address"/>
		<input type="hidden" name="draw" id="draw"/>
		<input type="submit" id="regBtn" value="등록"/>
	</form>
</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0f92754065fd18fb9b2450d8077e930c&libraries=services,drawing"></script>
<script>

	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
		center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		level : 3
	// 지도의 확대 레벨
	};

	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption);

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
	
	var options = { // Drawing Manager를 생성할 때 사용할 옵션입니다
		    map: map, // Drawing Manager로 그리기 요소를 그릴 map 객체입니다
		    drawingMode: [ // Drawing Manager로 제공할 그리기 요소 모드입니다
		        kakao.maps.drawing.OverlayType.MARKER,
		        kakao.maps.drawing.OverlayType.POLYLINE,
		        kakao.maps.drawing.OverlayType.RECTANGLE,
		        kakao.maps.drawing.OverlayType.CIRCLE,
		        kakao.maps.drawing.OverlayType.POLYGON
		    ],
		    // 사용자에게 제공할 그리기 가이드 툴팁입니다
		    // 사용자에게 도형을 그릴때, 드래그할때, 수정할때 가이드 툴팁을 표시하도록 설정합니다
		    guideTooltip: ['draw', 'drag', 'edit'], 
		    markerOptions: { // 마커 옵션입니다 
		        draggable: true, // 마커를 그리고 나서 드래그 가능하게 합니다 
		        removable: true // 마커를 삭제 할 수 있도록 x 버튼이 표시됩니다  
		    },
		    polylineOptions: { // 선 옵션입니다
		        draggable: true, // 그린 후 드래그가 가능하도록 설정합니다
		        removable: true, // 그린 후 삭제 할 수 있도록 x 버튼이 표시됩니다
		        editable: true, // 그린 후 수정할 수 있도록 설정합니다 
		        strokeColor: '#39f', // 선 색
		        hintStrokeStyle: 'dash', // 그리중 마우스를 따라다니는 보조선의 선 스타일
		        hintStrokeOpacity: 0.5  // 그리중 마우스를 따라다니는 보조선의 투명도
		    },
		    rectangleOptions: {
		        draggable: true,
		        removable: true,
		        editable: true,
		        strokeColor: '#39f', // 외곽선 색
		        fillColor: '#39f', // 채우기 색
		        fillOpacity: 0.5 // 채우기색 투명도
		    },
		    circleOptions: {
		        draggable: true,
		        removable: true,
		        editable: true,
		        strokeColor: '#39f',
		        fillColor: '#39f',
		        fillOpacity: 0.5
		    },
		    polygonOptions: {
		        draggable: true,
		        removable: true,
		        editable: true,
		        strokeColor: '#39f',
		        fillColor: '#39f',
		        fillOpacity: 0.5,
		        hintStrokeStyle: 'dash',
		        hintStrokeOpacity: 0.5
		    }
		};

		// 위에 작성한 옵션으로 Drawing Manager를 생성합니다
		var manager = new kakao.maps.drawing.DrawingManager(options);

		// 버튼 클릭 시 호출되는 핸들러 입니다
		function selectOverlay(type) {
		    // 그리기 중이면 그리기를 취소합니다
		    manager.cancel();

		    // 클릭한 그리기 요소 타입을 선택합니다
		    manager.select(kakao.maps.drawing.OverlayType[type]);
		}	
</script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$("#regForm").submit(function() {
		$("#form_address").val($("#address").val());
		$("#draw").val(JSON.stringify(manager.getData()));
	})
</script>

</body>
</html>