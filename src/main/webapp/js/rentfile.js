function creatchatroom(poster, viewer) {
    return $.ajax({
        url: "/api/chat/creatchatroom/" + poster + "/" + viewer,
        type: "PUT",
        async: !1,
        success: function (res) { },
        error: function (err) { console.log(err) },
    });
}
function AjaxgetRegister() {
    return $.ajax({
        type: "get",
        url: "/getRegister",
        async: !1,
        success: function (res) {
            //			console.log(res["member_nickname"])
        },
        error: function (err) { console.log(err) },
    })
}
function AjaxgetFindAccount(account) {
    return $.ajax({
        type: "POST",
        url: "/findmember_account/" + account,
        async: !1,
        success: function (res) {
            console.log(res)
        },
        error: function (err) { console.log(err) },
    })
}
function getaccount() {
    var urlstring = location.href
    var url = new URL(urlstring)
    return url.searchParams.get('account')
}
function getaccountfile(account) {
    if (account == null) {
        // 跳轉
    } else {
        var result = AjaxgetFindAccount(account)
        if (result["status"] == 404) {
            //跳轉
        }
        return result["responseJSON"]["0"]
    }
}
function AjaxgetRate2(accountid) {
    return $.ajax({
        type: "post",
        url: "/getRate/" + accountid,
        //            dataType:"json",
        async: !1,
        success: function (res) { },
        error: function (err) { console.log(err) },
    })
}
function rateUI(accountid) {
    var p = AjaxgetRate2(accountid)
    rateData = p;
    $("#myRate").text("")
    console.log(rateData)
    if (rateData["responseText"] != "") {
        if (rateData["responseJSON"]["linkedListmembers"].length == 0) {
            $("#myRate").text("暫無資料")
        }
        for (let i = 0; i < rateData["responseJSON"]["linkedListmembers"].length; i++) {
            if (rateData["responseJSON"]["listrate_list"][i]["rate_description"] == null) {
                rateData["responseJSON"]["listrate_list"][i]["rate_description"] = "無評論"
            }
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
$(function(){
    viewer = AjaxgetRegister()["responseJSON"]["member_id"]
    account = getaccount()
    accountfile = getaccountfile(account)
    accountrank = AjaxgetRate2(accountfile["member_id"])
    poster = accountfile["member_id"]
    rateUI(accountfile["member_id"])
    console.log(accountfile)
    console.log(accountrank)
    if(accountfile["member_nickname"]==null){
        accountfile["member_nickname"]=""
    }
    $("#member_nickname").text(accountfile["member_account"]+" "+accountfile["member_nickname"]);
    // member_account
    $("#member_region").text(accountfile["member_region"]);
    $("#member_introduction").text(accountfile["member_introduction"]);
    if (accountfile["member_icon"] != null) {
        $("#headPhoto").attr("src", "../img/" + accountfile["member_icon"]);
    }
    $("#contactposter").click(function () {
        console.log(poster + " " + viewer)
        if (viewer != 0) {
            var p = creatchatroom(poster, viewer)
            console.log(p)
            // window.location.href="chat.html";
            window.open("chat.html");
        } else {
            alert("請先登入")
        }

})

});



