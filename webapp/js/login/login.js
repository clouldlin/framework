
var login = login || {};

// 즉시실행 함수 : 생성순간, 한번밖에 호출할수 없다.
login = (function(win) {
 
	var global = win;
	var doc = global.document;
	
    var idSave = function() {
    	var id = doc.getElementById('id').value;
    	var check = doc.getElementById('id_save');
    	
    	if (check.checked == true) {
    		login.saveId(id);	
		} else {
			
		}
    };
 
/*    var saveId = function(test){
    	 if (id != "") {
    	        // userid 쿠키에 id 값을 7일간 저장
    	        setsave("userid", id, 7);
    	    } else {
    	        // userid 쿠키 삭제
    	        setsave("userid", id, -1);
    	    }
    }*/
    
/*    var setSave = function (name, value, expiredays) {
    	var today = new Date();
    	today.setDate(today.getDate() + expiredays);
    	document.cookie = name + "=" + escape(value) + "; path=/; expires=" + today.toGMTString() + ";";
    }	*/
    
    // Public 속성, 메소드
    return {
        idSave : idSave
    };
}(window));

Common.eventBind(window, "load", function () {
	Common.eventBind(document.getElementById("submit"), "click", function (event) {
		alert("이벤트!");
		event.preventDefault(); 
	});
});
/*// 버튼 클릭시 실행 function
function saveId(){
    //if (document.getElementById("idcheck").checked) {
        saveLogin(document.getElementById("userId").value);
    //} else {
        //saveLogin("");
    //}
}

// id를 받아와 쿠키에 저장할지 쿠키에서 삭제할지 결정.
function saveLogin(id) {
    if (id != "") {
        // userid 쿠키에 id 값을 7일간 저장
        setsave("userid", id, 7);
    } else {
        // userid 쿠키 삭제
        setsave("userid", id, -1);
    }
}*/