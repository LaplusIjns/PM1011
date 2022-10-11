var l = 0;
//獲得登入者session
function AjaxgetRegister() {
	return $.ajax({
		type: "get",
		url: "/getRegister",
		//            dataType:"json",
		async: !1,
		success: function(res) {
			//			console.log(res["member_nickname"])
		},
		error: function(err) { console.log(err) },
	})
}
//獲得評價
function AjaxgetRate() {
	return $.ajax({
		type: "post",
		url: "/getRate",
		//            dataType:"json",
		async: !1,
		success: function(res) { },
		error: function(err) { console.log(err) },
	})
}
//註冊
function AjaxdoRegister(dataToServer) {
	return $.ajax({
		type: "post",
		url: "/doRegister",
		data: dataToServer
	})
}

//登入
function AjaxloginRegister(dataToServer) {
	return $.ajax({
		type: "post",
		url: "/loginRegister",
		data: dataToServer,
		async: !1,
		success: function(res) {
			//	console.log(res); 
		},
		error: function(err) {
			console.log(err)
		},
	})
}
//登出
function AjaxlogoutRegister(dataToServer) {
	return $.ajax({
		type: "get",
		url: "/logoutRegister",
		async: !1,
		data: dataToServer
	})
}



//忘記密碼
function AjaxforgetRegister(dataToServer) {
	return $.ajax({
		type: "post",
		url: "/forgetRegister",
		async: !1,
		data: dataToServer
	})

}

//更新資料
function AjaxchangeRegister(dataToServer) {
	return $.ajax({
		type: "post",
		url: "/changeRegister",
		async: !1,
		data: dataToServer
	})

}

function loginrefreshUI() {
	if (userData["responseJSON"]["member_id"] == 0) {
		// window.location.href = "http://127.0.0.1:5500/index.html";
		// alert("請先登入帳號！！");
	} else {
		$("#member_nickname").text(userData["responseJSON"]["member_nickname"]);
		$("#member_region").text(userData["responseJSON"]["member_region"]);
		$("#member_introduction").text(userData["responseJSON"]["member_introduction"]);
		if (userData["responseJSON"]["member_icon"] != null) {
			$("#headPhoto").attr("src", "../img/" + userData["responseJSON"]["member_icon"]);
		}
		loginBtn.style.display = "none";
		logoutBtn.style.display = "block";
		createBtn.style.display = "none";
	}
}

$(function() {
	userData = AjaxgetRegister()
	console.log(userData["responseJSON"]["member_id"])
	loginrefreshUI()
	//setTimeout(downloadAndRefreshUI,1000);
	$("#editbtn").on("click", function() {
		$("input[name='member_nickname']").attr("value", userData["responseJSON"]["member_nickname"]);
		$("input[name='member_region']").attr("value", userData["responseJSON"]["member_region"]);
		$("textarea[name='member_introduction']").val(userData["responseJSON"]["member_introduction"]);
		$("input[name='member_birthday']").attr("value", userData["responseJSON"]["member_birthday"]);
	})

	$("#createMember").on("click", function() {
		console.log("OK?");
		var dataToServer = {
			member_account: $("#createAccountTextBox").val(),
			member_password: $("#createPasswordMTextBox").val(),
			member_email: $("#createMailTextBox").val()
		}
		var p = AjaxdoRegister(dataToServer)
		var result = p;
		alert(eval(result).responseText);
	})

	$("#loginMember").on("click", function() {
		l++;
		if (l == 3) {
			$("#loginBox").append(`<label class="mx-2 my-1" style="color:red">忘記密碼？請輸入帳號並點擊忘記密碼，至登記信箱獲取驗證碼</label>`);
		}
		var dataToServer = {
			member_account: $("#loginAccountTextBox").val(),
			member_password: $("#loginPasswordMTextBox").val()
		}
		var p = AjaxloginRegister(dataToServer)
		var result = p;
		//gyugyugyugyu
		console.log(eval(result).responseText)
		console.log(p)
		downloadAndRefreshUI(dataToServer);
		rateUI();
		alert(eval(result).responseText);
	})

	$("#logoutBtn").on("click", function() {
		var dataToServer = {
			member_account: $("#loginAccountTextBox").val(),
			member_password: $("#loginPasswordMTextBox").val()
		}
		var p = AjaxlogoutRegister(dataToServer)
		var result = p;
		alert(eval(result).responseText);
		//window.location.href = "#";
		document.location.reload(true);
	})

	$("#changeMember").on("click", function() {
		var dataToServer = {
			member_account: $("#changeAccountTextBox").val(),
			member_password: $("#changePasswordMTextBox").val(),
			member_email: $("#changeMailTextBox").val()
		}
		var p = AjaxchangeRegister(dataToServer)
		console.log(p)
		var result = p;
		alert(eval(result).responseText);
	})

	$("#forgetBtn").on("click", function() {
		var dataToServer = {
			member_account: $("#loginAccountTextBox").val(),
		}
		var p = AjaxforgetRegister(dataToServer)
		var result = p;
		alert(eval(result).responseText);
	})
})