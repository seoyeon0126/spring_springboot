$(function() {
//	alert(1)
	$('#submit1').click(function() {
		//		alert(1);
		//valichk() 호출해서 결과에따라 전송
		if (valichk()) {
			return false;
		}
		$('form').submit();
	});
	
	//id check
	$('#idchck').on("propertychange change input past", function(){
//		alert("왔다");
		$.ajax({
			asynk:true,
			type:'post',
			url:"idCheck",
			dataType:"json",
			data:{"id":$("#idchck").val()},
			success:function(data){
				if(data>0){
					$('font[id=warning]').text(''); 
					$('font[id=warning]').attr('color','red'); 
					$('font[id=warning]').text('이미 존재하는 아이디 입니다.');
					$('#idchck').focus(); 
				} else {
					$('font[id=warning]').text(''); 
					$('font[id=warning]').attr('color','blue'); 
					$('font[id=warning]').text('사용가능한 아이디 입니다.');
					$('#idchck').focus(); 
				}
			},
			error:function(error){
				alert("error: "+error);
			}
		});
	});
})

function valichk() {
	var flen = $("form[name='wForm'] .chk").length;
	for (var i = 0; i < flen; i++) {
		if ($('.chk').eq(i).val() == "" || $('.chk').eq(i).val() == null || $('.chk').eq(i).val().trim() == "") {
			alert("필수 입력 항목 입니다.");
			$('.chk').eq(i).focus();
			return true;
		}
	}
};

function memUpdate(check) {
	if (check == "regi") {
		document.wForm.action = "registerForm";
	} else if (check == 'update') {
		pw = prompt('비밀번호를 입력하시오.');
		spw = document.wForm.pass.value;
		if (pw == spw) {
			document.wForm.action = "updateForm";
		} else {
			alert('비밀번호가 틀렸습니다.');
			return;
		}
	} else if (check == 'delete') {
		pw = prompt('비밀번호를 입력하시오.');
		spw = document.wForm.pass.value;
		if (pw == spw) {
			document.wForm.action = "deletePro";
		} else {
			alert('비밀번호가 틀렸습니다.');
			return;
		}
	}
	document.wForm.submit();
}