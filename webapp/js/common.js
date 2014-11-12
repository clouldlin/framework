Common = function() {};

Common.eventBind = function addListener(elem, ev, listener) {
	if (elem.addEventListener) { // IE 이외의 브라우저 대응
		elem.addEventListener(ev, listener, false);
	} else if (elem.attachEvent) { // IE 대응
		elem.attachEvent('on' + ev, listener);
	} else { // 이벤트 리스너에 미대응인 경우
		throw new Error("이벤트 리스너에 미대응");
	}
}

