var userData;
var rateData;
var result;
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
				console.log(res); 
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

//獲得session並更新資料
function downloadAndRefreshUI(dataToServer) {
	var p = AjaxgetRegister(dataToServer)
//	console.log(p)
//	console.log(p["responseJSON"])
	var result = p;
	userData = result;
	refreshUI();
}


function refreshUI() {
	if (userData["responseJSON"]["member_id"] == 0) {
		// window.location.href = "http://127.0.0.1:5500/index.html";
		alert("請先登入帳號！！");
		window.location.replace("../index.html");
	} else {
		if(userData["responseJSON"]["member_nickname"]==null){
			userData["responseJSON"]["member_nickname"] = "";
		}
		$("#member_nickname").text(userData["responseJSON"]["member_account"]+" "+userData["responseJSON"]["member_nickname"]);
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

function rateUI() {
	var p = AjaxgetRate()
	rateData = p;
	$("#myRate").text("")
	if(rateData["responseText"]!=""){
		for (let i = 0; i < rateData["responseJSON"]["linkedListmembers"].length; i++) {
			if(rateData["responseJSON"]["listrate_list"][i]["rate_description"]==null){
				rateData["responseJSON"]["listrate_list"][i]["rate_description"]="無評論"
			}
			// console.log((rateData["responseJSON"]["linkedListitems"][i]["item_name"]))
			$liTemplate = '<div class="card my-1">'
			$liTemplate +=
				'<div class="card-body text-dark d-flex justify-content-between">'
				+ '<span class="text-primary">' + rateData["responseJSON"]["linkedListitems"][i]["item_name"] + '</span>'
				+ '<span>' + rateData["responseJSON"]["linkedListmembers"][i]["member_account"] + '</span>'
				+ '<span>' + rateData["responseJSON"]["listrate_list"][i]["rate_description"] + '</span>'
				+ '<div class="ratings">'
			for (let j = 0; j < rateData["responseJSON"]["listrate_list"][i]["rate_grade"]; j++) {
				$liTemplate += '<i class="fa fa-star rating-color"></i>';
			}
			for (let k = 0; k < (5 - rateData["responseJSON"]["listrate_list"][i]["rate_grade"]); k++) {
				$liTemplate += '<i class="fa fa-star"></i>';
			}
			$liTemplate += '</div></div></div>'
			jq = $($liTemplate)
			jq.appendTo("#myRate")
		}
		}
}
$(function() {
	



	userData = AjaxgetRegister()
//	console.log(userData["responseJSON"]["member_rank"])
	if(userData["responseJSON"]["member_rank"]=="管理者"){
		loginBtn.style.display = "none";
	}
	refreshUI();
	rateUI();
	
	//setTimeout(downloadAndRefreshUI,1000);
	$("#editbtn").on("click", function() {
		$("input[name='member_nickname']").attr("value", userData["responseJSON"]["member_nickname"]);
		$("input[name='member_region']").attr("value", userData["responseJSON"]["member_region"]);
		$("textarea[name='member_introduction']").val(userData["responseJSON"]["member_introduction"]);
		$("input[name='member_birthday']").attr("value", userData["responseJSON"]["member_birthday"]);
	})



	       $("#formBtn").on("click", function () {
	       window.location.reload()
	       })


})