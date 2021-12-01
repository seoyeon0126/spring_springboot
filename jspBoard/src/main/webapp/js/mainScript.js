/**
 *  board의 전체 자바스크립트 모임
 */
function sendConfirm(check) {
	//	alert(check);
	if (check == "reply") {
		document.content.action = "./Board?command=writeForm";
	} else if (check == 'update') {
		pw = prompt('비밀번호를 입력하시오.');
		spw = document.content.passwd.value;
		if (pw == spw) {
			document.content.action = "./Board?command=updateForm";
		} else {
			alert('비밀번호가 틀렸습니다.');
			return;
		}
	} else if (check == 'delete') {
		pw = prompt('비밀번호를 입력하시오.');
		spw = document.content.passwd.value;
		if (pw == spw) {
			document.content.action = "./Board?command=deleteAction";
		} else {
			alert('비밀번호가 틀렸습니다.');
			return;
		}
	}
	document.content.submit();
}