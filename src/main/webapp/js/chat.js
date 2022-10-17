var ChatroomIDnow
var lastChatroomIDnow=0
// var lastChatroomID=0
var userid = AjaxgetRegister()["responseJSON"]["member_id"]
var firsttag=true
// console.log(AjaxgetRegister()["responseJSON"]["member_icon"])
//userid_img 當前使用者頭像
userid_img="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava2-bg.webp"
if(AjaxgetRegister()["responseJSON"]["member_icon"]!=null){
	userid_img ="../img/"+ AjaxgetRegister()["responseJSON"]["member_icon"]
}
// console.log(userid_img)
var ChatroomList=[]
function getuserid(memberid){
	return $.ajax({
		url:"/findmember_id/"+memberid,
		type:"POST",
		async:!1,
		success:function(res){
			// console.log(res)
		},
		error:function(err){console.log(err)},
		});
}

function getchatroom(userid){
	return $.ajax({
		url:"/api/chat/ALLchatrooms/"+userid,
		type:"GET",
		async:!1,
		success:function(res){},
		error:function(err){console.log(err)},
		});
}
function getchatroom(userid){
	// console.log(userid)
	return $.ajax({
		url:"/api/chat/ALLchatrooms/"+userid,
		type:"GET",
		async:!1,
		success:function(res){},
		error:function(err){console.log(err)},
		});
}
function getchatroommessages(chatroomid){
	return $.ajax({
		url:"/api/chat/ALLmessages/"+chatroomid,
		type:"GET",
		async:!1,
		success:function(res){},
		error:function(err){console.log(err)},
		});
}
function padLeft(str,lenght){
	if(str.length >= lenght)
	return str;
	else
	return padLeft("0" +str,lenght);
}
function timeparse(MessageDate){
		var MessageMonth = MessageDate.getMonth()+1;
		MessageMonth=padLeft(MessageMonth.toString(),2)
		var MessageDay = MessageDate.getDate();
		MessageDay = padLeft(MessageDay.toString(),2)
		var MessageHour = MessageDate.getHours();
		MessageHour = padLeft(MessageHour.toString(),2)
		var MessageMin = MessageDate.getMinutes();
		MessageMin = padLeft(MessageMin.toString(),2)
		var TimeTag = MessageHour+":"+MessageMin+" | "+MessageMonth+"/"+MessageDay
		return TimeTag;
}

//chat room connect
function connect(userid) {
    username = userid;
	// console.log("connect")
    if(username) {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    }
}
function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    // console.log("onconnected")
}


function onError(error) {
    console.log(error);
}

function sendMessage(userid,ChatroomIDnow) {
	// console.log("123")
    var messageContent = document.querySelector('#chatmessage1').value;
    MessageTime =new Date()
    year = MessageTime.getFullYear()
    month = MessageTime.getMonth()+1
    day = MessageTime.getDate()
    hour = MessageTime.getHours()
    minute = MessageTime.getMinutes()
    second = MessageTime.getSeconds()
    Timetag = year+"/"+month+"/"+day+" "+hour+":"+minute+":"+second
    var messageBean = {
	member_id:userid,
	chatroom_id:ChatroomIDnow,
	message_content:messageContent,
	message_sent_time:Timetag,
	}	
    if(messageContent && stompClient) {
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(messageBean));
        document.querySelector('#chatmessage1').value = '';
        // console.log("send!")
    }
    event.preventDefault();
}
function newchatlist(){
	nowDate = new Date();
	ChatroomList=getchatroom(userid)['responseJSON'];
	// console.log(ChatroomList)

	if(firsttag){
		console.log("storge!")
		localJson = ChatroomList
		for(var i=0;i<localJson.length;i++){
			localJson[i]["RedCount"]=0
		}
		var tmp1 = JSON.stringify(localJson)
		localStorage.setItem("chat",tmp1)
		firsttag=false
	}
	// localStorageJson = JSON.parse(localStorage.getItem("chat"))
	// console.log(localStorageJson)

	$("#chatroomlist").empty();
		if(ChatroomList!=undefined){
		for(var i=0;i<ChatroomList.length;i++){
			//抓聊天室資料
			//console.log("===")
			var lastMessage = getchatroommessages(ChatroomList[i]['chatroom_id'])['responseJSON'];
			// console.log(lastMessage[lastMessage.length-1])
			if(lastMessage[lastMessage.length-1]!=undefined){
				var lastMessageContent = lastMessage[lastMessage.length-1]['message_content']
				lastMessageTime =new Date( lastMessage[lastMessage.length-1]['message_sent_time'])
				//console.log(lastMessageTime)
				lastMessageWhoSend = lastMessage[lastMessage.length-1]['member_id']
				//console.log(lastMessageWhoSend)
				talkWHO = (userid==ChatroomList[i]['chatroom_member_id1'])?ChatroomList[i]['chatroom_member_id2'] :ChatroomList[i]['chatroom_member_id1']
				//console.log(talkWHO)
				//console.log("===")
				nickname = (getuserid(talkWHO)["responseJSON"][0]["member_nickname"]==null)?getuserid(talkWHO)["responseJSON"][0]["member_account"]:getuserid(talkWHO)["responseJSON"][0]["member_nickname"]
				compareDay = Math.floor( (nowDate - lastMessageTime)/1000/60/60/24);
				compareHour = Math.floor((nowDate - lastMessageTime)/1000/60/60 - compareDay*24);
				compareMin = Math.floor( (nowDate - lastMessageTime)/1000/60 - (compareDay*24+compareHour)*60);
				compareSec = Math.floor( (nowDate - lastMessageTime)/1000 - (((compareDay*24+compareHour)*60)+compareMin)*60 );
				var lastTimeTag;
				var DateTag;
				if(compareDay!=0){lastTimeTag=compareDay;DateTag="天";}
				else if(compareDay==0&&compareHour!=0){lastTimeTag=compareHour;DateTag="小時";}
				else if(compareDay==0&&compareHour==0&&compareMin!=0){lastTimeTag=compareMin;DateTag="分"}
				else{lastTimeTag = compareSec;DateTag="秒"}
				//console.log(lastTimeTag+DateTag+"之前")
				//更新會員列表
				var member_icon
				// console.log("icon "+getuserid(talkWHO)["responseJSON"][0]["member_icon"])
				if(getuserid(talkWHO)["responseJSON"][0]["member_icon"]!=null){
					member_icon ="../img/"+getuserid(talkWHO)["responseJSON"][0]["member_icon"]
					// console.log(member_icon)
					}else{
					member_icon= "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6-bg.webp"
				}
				
				var $li=$("<li></li>").addClass("p-1");
				$li.append('<a href="#" class="fs-5 card border-0" user='+ChatroomList[i]['chatroom_id']+'><div class="card-body mainbody"><div class="d-flex align-items-center mb-1 justify-content-between "><img src="'+member_icon+'" alt="avatar" class="d-flex align-self-center me-3" width="60"><div class=""><h6 class="fs-5 text-truncate mb-0 me-auto">'+nickname+'</h6><p class="fs-5 small font-weight-bold text-dark">'+lastMessageContent+'</p></div><div class="pt-1"><p class="fs-5 small font-weight-bold mb-1 text-success">'
				+lastTimeTag+DateTag+"之前"+'</p></div></div></div></a>');
				$li.appendTo("#chatroomlist");
			}else{
				//從沒聊天過
				//console.log(ChatroomList[i])
				talkWHO = (userid==ChatroomList[i]['chatroom_member_id1'])?ChatroomList[i]['chatroom_member_id2'] :ChatroomList[i]['chatroom_member_id1']
				nickname = (getuserid(talkWHO)["responseJSON"][0]["member_nickname"]==null)?getuserid(talkWHO)["responseJSON"][0]["member_account"]:getuserid(talkWHO)["responseJSON"][0]["member_nickname"]
				var member_icon
				if(getuserid(talkWHO)["responseJSON"][0]["member_icon"]!=null){
					member_icon ="../img/"+getuserid(talkWHO)["responseJSON"][0]["member_icon"]
					}else{
					member_icon= "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6-bg.webp"
				}
				lastMessageTime =new Date( ChatroomList[i]['chatroom_create_time'])
				compareDay = Math.floor( (nowDate - lastMessageTime)/1000/60/60/24);
				compareHour = Math.floor((nowDate - lastMessageTime)/1000/60/60 - compareDay*24);
				compareMin = Math.floor( (nowDate - lastMessageTime)/1000/60 - (compareDay*24+compareHour)*60);
				compareSec = Math.floor( (nowDate - lastMessageTime)/1000 - (((compareDay*24+compareHour)*60)+compareMin)*60 );
				//console.log(compareDay+"天"+compareHour+"時"+compareMin+"分"+compareSec+"秒之前")
				var lastTimeTag;
				var DateTag;
				if(compareDay!=0){lastTimeTag=compareDay;DateTag="天";}
				else if(compareDay==0&&compareHour!=0){lastTimeTag=compareHour;DateTag="小時";}
				else if(compareDay==0&&compareHour==0&&compareMin!=0){lastTimeTag=compareMin;DateTag="分"}
				else{lastTimeTag = compareSec;DateTag="秒"}
				$li=$("<li></li>").addClass("p-1");
				$li.append('<a href="#" class="fs-5 card border-0" user='+ChatroomList[i]['chatroom_id']+'><div class="card-body mainbody"><div class="d-flex align-items-center mb-1 justify-content-between "><img src="'+member_icon+'" alt="avatar" class="d-flex align-self-center me-3" width="60"><div class=""><h6 class="fs-5 text-truncate mb-0 me-auto">'+nickname+'</h6><p class="fs-5 small font-weight-bold text-dark"></p></div><div class="pt-1"><p class="fs-5 small font-weight-bold mb-1 text-success">'
				+lastTimeTag+DateTag+"之前創立"+'</p></div></div></div></a>');
				$li.appendTo("#chatroomlist");
			}
		}
			
		}
		test01 = document.getElementsByClassName("card")
		// console.log(test01.length)
		for(var i =0;i<test01.length;i++){
			test01[i].onclick = function(){
				// console.log(this.getAttribute('user'))
				//更新聊天室id
				// lastChatroomID = ChatroomIDnow
				lastChatroomIDnow = ChatroomIDnow
				ChatroomIDnow=this.getAttribute('user');
				rightsidechat()
			}
		}			
}
function rightsidechat(){
	console.log(lastChatroomIDnow)
	if(lastChatroomIDnow!=0){
		aa=$('[user="'+lastChatroomIDnow+'"]')
		aa.children().removeClass("lichecked")
		console.log("123")
	}
	if(ChatroomIDnow!=0){
		x= $('[user="'+ChatroomIDnow+'"]')
		y= x.find('span').remove()
		x.children().addClass("lichecked")
		loadJson = JSON.parse(localStorage.getItem("chat"))
		for(var i =0;i<Object.keys(loadJson).length;i++){
			if(loadJson[i]["chatroom_id"]==ChatroomIDnow){
			loadJson[i]["RedCount"]=0
		}}
		localStorage.setItem("chat",JSON.stringify(loadJson))
	}
	console.log("im here now")


	$("#MessageBoard").empty();
		var messagelist =[] 
		messagelist=getchatroommessages(ChatroomIDnow)['responseJSON']
		// console.log(messagelist)
		for(var j=0;j<messagelist.length;j++){
//			console.log(messagelist[j])
			var MessageDate =new Date(messagelist[j]["message_sent_time"]);
			//console.log(messagelist[j]["message_sent_time"])
			TimeTag = timeparse(MessageDate)
			//console.log(TimeTag)
			//對方訊息
			if(messagelist[j]['member_id']!=userid){
				var member_icon
				if(getuserid(messagelist[j]['member_id'])["responseJSON"][0]["member_icon"]!=null){
					member_icon ="../img/"+getuserid(messagelist[j]['member_id'])["responseJSON"][0]["member_icon"]}
				else{
					member_icon= "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6-bg.webp"
				}
				// console.log(member_icon)
				$div=$("<div></div>").addClass("d-flex flex-row justify-content-start");
				$div.append('<img src="'+member_icon+'"alt="avatar 1" style="width: 45px; height: 100%;">'
				+'<div>'
                +' <p class="small fs-3 ms-3 mb-1 rounded-3 text-center" style="background-color: #f5f6f7;">'+messagelist[j]["message_content"]+'</p>'
                +'<p class="fs-4 small ms-3 mb-3 rounded-3 text-muted float-end">'+TimeTag+'</p>'
                +'</div>');
				$div.appendTo("#MessageBoard")
			}else{
				$div = $("<div></div>").addClass("d-flex flex-row justify-content-end");
				$div.append('<div>'
				+'<p class="small fs-3 me-3 mb-1 text-white rounded-3 bg-primary text-center">'+messagelist[j]["message_content"]+'</p>'
				+'<p class="fs-4 small me-3 mb-3 rounded-3 text-muted">'+TimeTag+'</p>'
				+'</div>'
				+'<img src="'+userid_img+'" alt="avatar 1" style="width: 45px; height: 100%;">'
				);
				$div.appendTo("#MessageBoard")
			}
			
		}
	
}
//及時接收訊息
function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
	// console.log(message["message_content"])
	var MessageDate =new Date(message["message_sent_time"]);
	TimeTag = timeparse(MessageDate)
	// console.log(message)
	//更新右側聊天
	if(message["chatroom_id"]==ChatroomIDnow){
		if(message['member_id']!=userid){
					var member_icon
					
					if(getuserid(message["member_id"])["responseJSON"][0]["member_icon"]!=null){
						member_icon ="../img/"+getuserid(message['member_id'])["responseJSON"][0]["member_icon"]}
					else{
						member_icon= "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6-bg.webp"
					}
					// console.log("我在這裡: "+getuserid(message["member_id"])["responseJSON"][0]["member_icon"])
					$div=$("<div></div>").addClass("d-flex flex-row justify-content-start");
					$div.append('<img src="'+member_icon+'"alt="avatar 1" style="width: 45px; height: 100%;">'
					+'<div>'
	                +' <p class="small fs-3 ms-3 mb-1 rounded-3 text-center " style="background-color: #f5f6f7;">'+message["message_content"]+'</p>'
	                +'<p class="small ms-3 mb-3 rounded-3 text-muted float-end">'+TimeTag+'</p>'
	                +'</div>');
					$div.appendTo("#MessageBoard")
				}else{
					$div = $("<div></div>").addClass("d-flex flex-row justify-content-end");
					$div.append('<div>'
					+'<p class="small fs-3 me-3 mb-1 text-white rounded-3 bg-primary text-center">'+message["message_content"]+'</p>'
					+'<p class="fs-4 small me-3 mb-3 rounded-3 text-muted">'+TimeTag+'</p>'
					+'</div>'
					+'<img src="'+userid_img+'" alt="avatar 1" style="width: 45px; height: 100%;">'
					);
					$div.appendTo("#MessageBoard")
				}
			}
			//修改左側聊天欄位
			newchatlistReceived(message)
			console.log("renew chatlist")
}

function initalStorage(){
	localStorage.setItem("chat",null)
}

function newchatlistReceived(message){
	nowDate = new Date();
	ChatroomList=getchatroom(userid)['responseJSON'];
	// localStorageJson = localStorage.getItem("chat")
	localStorageJson = JSON.parse(localStorage.getItem("chat"))
	console.log(localStorageJson)
	jsonlength = Object.keys(localStorageJson).length
	existChatroom=false;
	for(var i = 0;i<jsonlength;i++){
		console.log("chatroom id "+localStorageJson[i]["chatroom_id"])
		if(localStorageJson[i]["chatroom_id"]==message["chatroom_id"]){
			existChatroom=true;
		}
	}
	if(existChatroom==false){
		newJson = {
			"chatroom_id":message["chatroom_id"],
			"chatroom_create_time":message["message_sent_time"],
			"chatroom_member_id1":userid,
			"chatroom_member_id2":message["member_id"],
			"RedCount":0
		}
		localStorageJson.push(newJson)
	}


	console.log(localStorageJson.length)
	for(var i=0;i<localStorageJson.length;i++){
		if( message["chatroom_id"]==localStorageJson[i]["chatroom_id"] ){
			if(message["member_id"]!=userid){
				if(message["chatroom_id"]!=ChatroomIDnow)
				localStorageJson[i]["RedCount"] += 1
		}}
	}
	var tmp123 = localStorageJson.length
			for(var i=0;i<tmp123;i++){
				for(var j = 0;j<tmp123;j++){
				if(localStorageJson[j]["chatroom_id"]==ChatroomList[i]["chatroom_id"]){
					tmp = localStorageJson[i]
					localStorageJson[i] = localStorageJson[j]
					localStorageJson[j]=tmp
				}}}
	localStorage.setItem("chat",JSON.stringify(localStorageJson))
	// console.log(message)
	console.log(ChatroomList)
	console.log(localStorageJson)

	$("#chatroomlist").empty();
		if(ChatroomList!=undefined){
		for(var i=0;i<ChatroomList.length;i++){
			//抓聊天室資料
			//console.log("===")
			var lastMessage = getchatroommessages(ChatroomList[i]['chatroom_id'])['responseJSON'];
			// console.log(lastMessage[lastMessage.length-1])
			if(lastMessage[lastMessage.length-1]!=undefined){
				var lastMessageContent = lastMessage[lastMessage.length-1]['message_content']
				lastMessageTime =new Date( lastMessage[lastMessage.length-1]['message_sent_time'])
				lastMessageWhoSend = lastMessage[lastMessage.length-1]['member_id']
				//console.log(lastMessageWhoSend)
				talkWHO = (userid==ChatroomList[i]['chatroom_member_id1'])?ChatroomList[i]['chatroom_member_id2'] :ChatroomList[i]['chatroom_member_id1']
				// console.log("跟誰交談: "+talkWHO+"交談內容: "+lastMessageContent+"最後一筆訊息時間: "+lastMessageTime)
				//console.log("===")
				nickname = (getuserid(talkWHO)["responseJSON"][0]["member_nickname"]==null)?getuserid(talkWHO)["responseJSON"][0]["member_account"]:getuserid(talkWHO)["responseJSON"][0]["member_nickname"]
				compareDay = Math.floor( (nowDate - lastMessageTime)/1000/60/60/24);
				compareHour = Math.floor((nowDate - lastMessageTime)/1000/60/60 - compareDay*24);
				compareMin = Math.floor( (nowDate - lastMessageTime)/1000/60 - (compareDay*24+compareHour)*60);
				compareSec = Math.floor( (nowDate - lastMessageTime)/1000 - (((compareDay*24+compareHour)*60)+compareMin)*60 );
				var lastTimeTag;
				var DateTag;
				if(compareDay!=0){lastTimeTag=compareDay;DateTag="天";}
				else if(compareDay==0&&compareHour!=0){lastTimeTag=compareHour;DateTag="小時";}
				else if(compareDay==0&&compareHour==0&&compareMin!=0){lastTimeTag=compareMin;DateTag="分"}
				else{lastTimeTag = compareSec;DateTag="秒"}
				//console.log(lastTimeTag+DateTag+"之前")
				//更新會員列表
				var member_icon
				// console.log("icon "+getuserid(talkWHO)["responseJSON"][0]["member_icon"])
				// console.log(getuserid(talkWHO)["responseJSON"])

					if(getuserid(talkWHO)["responseJSON"][0]["member_icon"]!=null){
						member_icon ="../img/"+getuserid(talkWHO)["responseJSON"][0]["member_icon"]
						// console.log(member_icon)
						}else{
						member_icon= "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6-bg.webp"
					}

				//
				if(localStorageJson[i]["RedCount"]==0){
					var $li=$("<li></li>").addClass("p-1");
					$li.append('<a href="#" class="fs-5 card border-0" user='+ChatroomList[i]['chatroom_id']+'><div class="card-body mainbody"><div class="d-flex align-items-center mb-1 justify-content-between "><img src="'+member_icon+'" alt="avatar" class="d-flex align-self-center me-3" width="60"><div class=""><h6 class="fs-5 text-truncate mb-0 me-auto">'+nickname+'</h6><p class="fs-5 small font-weight-bold text-dark">'+lastMessageContent+'</p></div><div class="pt-1"><p class="fs-5 small font-weight-bold mb-1 text-success">'
					+lastTimeTag+DateTag+"之前"+'</p></div></div></div></a>');
					$li.appendTo("#chatroomlist");
				}else{
					var $li=$("<li></li>").addClass("p-1");
					$li.append('<a href="#" class="fs-5 card border-0" user='+ChatroomList[i]['chatroom_id']+'><div class="card-body mainbody"><div class="d-flex align-items-center mb-1 justify-content-between "><img src="'+member_icon+'" alt="avatar" class="d-flex align-self-center me-3" width="60"><div class=""><h6 class="fs-5 text-truncate mb-0 me-auto">'+nickname+'</h6><p class="fs-5 small font-weight-bold text-dark">'+lastMessageContent+'</p></div><div class="pt-1">'
					+'<span class="badge bg-danger rounded-pill">'+localStorageJson[i]["RedCount"]+'</span>'
					+'<p class="fs-5 small font-weight-bold mb-1 text-success">'+lastTimeTag+DateTag+"之前"+'</p></div></div></div></a>');
					$li.appendTo("#chatroomlist");

				}
				//
			}else{
				//從沒聊天過
				//console.log(ChatroomList[i])
				talkWHO = (userid==ChatroomList[i]['chatroom_member_id1'])?ChatroomList[i]['chatroom_member_id2'] :ChatroomList[i]['chatroom_member_id1']
				nickname = (getuserid(talkWHO)["responseJSON"][0]["member_nickname"]==null)?getuserid(talkWHO)["responseJSON"][0]["member_account"]:getuserid(talkWHO)["responseJSON"][0]["member_nickname"]
				var member_icon
				if(getuserid(talkWHO)["responseJSON"][0]["member_icon"]!=null){
					member_icon ="../img/"+getuserid(talkWHO)["responseJSON"][0]["member_icon"]
					}else{
					member_icon= "https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6-bg.webp"
				}
				lastMessageTime =new Date( ChatroomList[i]['chatroom_create_time'])
				compareDay = Math.floor( (nowDate - lastMessageTime)/1000/60/60/24);
				compareHour = Math.floor((nowDate - lastMessageTime)/1000/60/60 - compareDay*24);
				compareMin = Math.floor( (nowDate - lastMessageTime)/1000/60 - (compareDay*24+compareHour)*60);
				compareSec = Math.floor( (nowDate - lastMessageTime)/1000 - (((compareDay*24+compareHour)*60)+compareMin)*60 );
				//console.log(compareDay+"天"+compareHour+"時"+compareMin+"分"+compareSec+"秒之前")
				var lastTimeTag;
				var DateTag;
				if(compareDay!=0){lastTimeTag=compareDay;DateTag="天";}
				else if(compareDay==0&&compareHour!=0){lastTimeTag=compareHour;DateTag="小時";}
				else if(compareDay==0&&compareHour==0&&compareMin!=0){lastTimeTag=compareMin;DateTag="分"}
				else{lastTimeTag = compareSec;DateTag="秒"}

				//
				if(localStorageJson[i]["RedCount"]==0){
					$li=$("<li></li>").addClass("p-1");
					$li.append('<a href="#" class="fs-5 card border-0" user='+ChatroomList[i]['chatroom_id']+'><div class="card-body  mainbody"><div class="d-flex align-items-center mb-1 justify-content-between "><img src="'+member_icon+'" alt="avatar" class="d-flex align-self-center me-3" width="60"><div class=""><h6 class="fs-5 text-truncate mb-0 me-auto">'+nickname+'</h6><p class="fs-5 small font-weight-bold text-dark"></p></div><div class="pt-1"><p class="fs-5 small font-weight-bold mb-1 text-success">'
					+lastTimeTag+DateTag+"之前創立"+'</p></div></div></div></a>');
					$li.appendTo("#chatroomlist");
				}else{
					$li=$("<li></li>").addClass("p-1");
					$li.append('<a href="#" class="fs-5 card border-0" user='+ChatroomList[i]['chatroom_id']+'><div class="card-body mainbody"><div class="d-flex align-items-center mb-1 justify-content-between "><img src="'+member_icon+'" alt="avatar" class="d-flex align-self-center me-3" width="60"><div class=""><h6 class="fs-5 text-truncate mb-0 me-auto">'+nickname+'</h6><p class="fs-5 small font-weight-bold text-dark"></p></div><div class="pt-1">'
					+'<span class="badge bg-danger rounded-pill">'+localStorageJson[i]["RedCount"]+'</span>'
					+'<p class="fs-5 small font-weight-bold mb-1 text-success">'+lastTimeTag+DateTag+"之前創立"+'</p></div></div></div></a>');
					$li.appendTo("#chatroomlist");
				}
				//<span class="badge bg-danger rounded-pill">3</span>
			}
		}
			
		}
		test01 = document.getElementsByClassName("card")
		// console.log(test01.length)
		for(var i =0;i<test01.length;i++){
			test01[i].onclick = function(){
				// console.log(this.getAttribute('user'))
				//更新聊天室id
				lastChatroomIDnow = ChatroomIDnow
				console.log(lastChatroomIDnow)
				ChatroomIDnow=this.getAttribute('user');
				rightsidechat()
			}
		}		
}


//初始化
$(
	function(){
		console.log("init")
		initalStorage()
		// userid = 1; //使用者id
		//更新左側聊天室列表
		newchatlist()
		if(ChatroomList!=undefined){
		ChatroomIDnow = ChatroomList[0]['chatroom_id']}
		//更新右側聊天室
		rightsidechat()
		
		console.log("end init")
		connect(userid)
		chatmessageform.addEventListener('submit',function() {sendMessage(userid,ChatroomIDnow) },true)
		
	}
);