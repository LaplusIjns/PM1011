function getproduct() {
    return $.ajax({
        type: "get",
        async:!1,
        url: `/product${item_id}`
    })}
function AjaxgetRegister() {
    return $.ajax({
        type: "get",
        url: "/getRegister",
        async: !1,
        success: function(res) {
            //			console.log(res["member_nickname"])
        },
        error: function(err) { console.log(err) },
    })
}
function creatchatroom(poster,viewer){
    return $.ajax({
		url:"/api/chat/creatchatroom/"+poster+"/"+viewer,
		type:"PUT",
		async:!1,
		success:function(res){},
		error:function(err){console.log(err)},
		});
}
$(function(){
    poster = getproduct()["responseJSON"]["member"]["member_id"]
    console.log("poster: "+poster)
    viewer = AjaxgetRegister()["responseJSON"]["member_id"]
    console.log("viewer: "+viewer)
    if(poster==viewer){
        x=document.getElementById('contactposter')
        x.hidden = true
        console.log(x)
    }
    $("#contactposter").click(function () {
        console.log(poster+" "+viewer)
        if(viewer!=0){
        var p = creatchatroom(poster,viewer)
        console.log(p)
        // window.location.href="chat.html";
        window.open("chat.html");
    }else{
        alert("請先登入")}
        })
    $("#rentfile").click(function () {
        poster = getproduct()["responseJSON"]["member"]["member_account"]
        console.log(poster)
        window.open("rentfile.html?account="+poster);
    })
    
})