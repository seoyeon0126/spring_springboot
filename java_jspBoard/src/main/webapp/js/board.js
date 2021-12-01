$(function() {
	$('#submit1').click(function(){
//		alert(1);
		//valichk() 호출해서 결과에따라 전송
		if(valichk()) {
			return false;
		}
	$('form').submit();
	});
})

function valichk(){
	var flen = $("form[name='wForm'] .chk").length;
	for(var i=0; i<flen; i++) {
		if($('.chk').eq(i).val()=="" || $('.chk').eq(i).val()==null || $('.chk').eq(i).val().trim()==""){
			alert("필수 입력 항목 입니다.");
			$('.chk').eq(i).focus();
			return true;
		}
	}
};

function sendConfirm(check) {
//		alert(check);
	if (check == "reply") {
		document.content.action = "/writeForm.iot";
	} else if (check == 'update') {
		pw = prompt('비밀번호를 입력하시오.');
		spw = document.content.passwd.value;
		if (pw == spw) {
			document.content.action = "/updateForm.iot";
		} else {
			alert('비밀번호가 틀렸습니다.');
			return;
		}
	} else if (check == 'delete') {
		pw = prompt('비밀번호를 입력하시오.');
		spw = document.content.passwd.value;
		if (pw == spw) {
			document.content.action = "/deletePro.iot";
		} else {
			alert('비밀번호가 틀렸습니다.');
			return;
		}
	}
	document.content.submit();
}