console.log("abc");
$(function () {
    var userData;
    var rateData;
    var result;
    var l = 0;
    async function downloadAndRefreshUI() {
        var p = $.ajax({
            type: "get",
            url: "/getRegister",
            dataType:"json"
        })
        var result = await p;
        userData = result; 
        refreshUI();
    }
    async function rateUI() {
        var p = $.ajax({
            type: "post",
            url: "/getRate",
            dataType:"json"
        })
         result = await p;
        console.log(result);
        console.log(result.listrate_list);
        console.log(result.linkedListmembers);
        rateData = result; 
    }
    rateUI();
    setTimeout(downloadAndRefreshUI,1000);

    function refreshUI() {
        console.log(result);
         if(userData.member_id == 0){
        // window.location.href = "http://127.0.0.1:5500/index.html";
        alert("請先登入帳號！！");
        }else {
        $("#member_nickname").text(userData.member_nickname);
        $("#member_region").text(userData.member_region);
        $("#member_introduction").text(userData.member_introduction);
        if(userData.member_icon != null){
            $("#headPhoto").attr("src","/img/" + userData.member_icon);
    }
        loginBtn.style.display = "none";
        logoutBtn.style.display = "block";
        createBtn.style.display = "none";
        $("#myRate").empty();
        console.log(rateData);
        console.log(rateData.linkedListmembers);
        // for (let i = 0; i < rateData["responseJSON"]["linkedListmembers"].length; i++) {
//             $liTemplate='<div class="card my-1">'
//             $liTemplate+=
//                             '<div class="card-body text-dark d-flex justify-content-between">'
//                             +'<span>'+rateData["responseJSON"]["linkedListitems"][i]["item_name"]+'</span>'
//                             +'<span>'+rateData["responseJSON"]["linkedListmembers"][i]["member_account"]+'</span>'
//                             +'<span>'+rateData["responseJSON"]["listrate_list"][i]["rate_description"]+'</span>'
//                             +'<div class="ratings">'
//               for(let j = 0; j < rateData["responseJSON"]["listrate_list"][i]["rate_grade"];j++) {
//             $liTemplate+='<i class="fa fa-star rating-color"></i>';    
//               } 
//             for(let k = 0; k < (5 - rateData["responseJSON"]["listrate_list"][i]["rate_grade"]);k++){
//                 $liTemplate+='<i class="fa fa-star"></i>';    
//                 }  
//               $liTemplate+='</div></div></div>'          
//             console.log($liTemplate)
//             jq = $($liTemplate)
//             jq.appendTo("#myRate")
// //				$("#myRate").text($liTemplate.join(''))
//             }
        for (let i = 0; i < rateData.listrate_list.length; i++) {
            let liTemplate = `<div class="card my-1">
                            <div class="card-body text-dark d-flex justify-content-between">
                                <span>${rateData.linkedListitems[i].item_name}</span>
                                <span>${rateData.linkedListmembers[i].member_account}</span>
                                <span>${rateData.listrate_list[i].rate_description}</span>
                                <div class="ratings">`;                                      
            $("#myRate").append(liTemplate);
            for(let j = 0; j < rateData.listrate_list[i].rate_grade ;j++) {
                $("#myRate").append(`<i class="fa fa-star rating-color"></i>`);    
                    }
                for(let k = 0; k < (5 - rateData.listrate_list[i].rate_grade);k++){
                    $("#myRate").append(`<i class="fa fa-star"></i>`); 
                        }
                        $("#myRate").append(`</div></div></div>`);         
            }
        }
    }

    $("#editbtn").on("click",function () {
        $("input[name='member_nickname']").attr("value",userData.member_nickname);
        $("input[name='member_region']").attr("value",userData.member_region);
        $("textarea[name='member_introduction']").val(userData.member_introduction);
        $("input[name='member_birthday']").attr("value",userData.member_birthday);
    })

        $("#createMember").on("click", async function () {
            console.log("OK?");
        var dataToServer = {
            member_account: $("#createAccountTextBox").val(),
            member_password: $("#createPasswordMTextBox").val(),
            member_email:$("#createMailTextBox").val()
        }
            var p = $.ajax({
                type: "post",
                url: "/doRegister",
                data: dataToServer
            })
            var result = await p;
            alert(result); 
        })

        $("#loginMember").on("click", async function () {
            l++;
            if(l == 3){
                $("#loginBox").append(`<label class="mx-2 my-1" style="color:red">忘記密碼？請輸入帳號並點擊忘記密碼，至登記信箱獲取驗證碼</label>`);
            }
        var dataToServer = {
            member_account: $("#loginAccountTextBox").val(),
            member_password: $("#loginPasswordMTextBox").val()
        }
            var p = $.ajax({
                type: "post",
                url: "/loginRegister",
                data: dataToServer
            })
            var result = await p;
            downloadAndRefreshUI();
            rateUI();
            alert(result);
        })

        $("#logoutBtn").on("click", async function () {
            var p = $.ajax({
                type: "get",
                url: "/logoutRegister",
            })
            var result = await p;
            alert(result);
            window.location.href = "/index.html";
        })

        $("#changeMember").on("click", async function () {
        var dataToServer = {
            member_account: $("#changeAccountTextBox").val(),
            member_password: $("#changePasswordMTextBox").val(),
            member_email:$("#changeMailTextBox").val()
        }
            var p = $.ajax({
                type: "post",
                url: "/changeRegister",
                data: dataToServer
            })
            var result = await p;
            alert(result); 
        })

        $("#forgetBtn").on("click", async function () {
        var dataToServer = {
            member_account: $("#loginAccountTextBox").val(),
        }
            var p = $.ajax({
                type: "post",
                url: "/forgetRegister",
                data: dataToServer
            })
            var result = await p;
            alert(result); 
        })

    })