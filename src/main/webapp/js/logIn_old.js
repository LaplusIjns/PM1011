$(function () {
    var userData;
    var i = 0;
    var isManager = false;
    async function downloadAndRefreshUI() {
        var p = $.ajax({
            type: "get",
            url: "/getRegister",
            dataType: "json"
        })
        var result = await p;
        userData = result;
        refreshUI();
        if (isManager) {
            $("#backstage").removeClass("normal");
            $("#backstage").addClass("manager");
        }
    }
    downloadAndRefreshUI();
    function refreshUI() {
        if (i == 3) {
            $("#loginBox").append(`<label class="mx-2 my-1" style="color:red">忘記密碼？請輸入帳號並點擊忘記密碼，至登記信箱獲取驗證碼</label>`);
        }
        if (userData.member_id == 0) {
            // window.location.href = "http://127.0.0.1:5500/index.html";
            alert("請先登入帳號！！");
        } else {
            $("#member_nickname").text(userData.member_nickname);
            $("#member_region").text(userData.member_region);
            $("#member_introduction").text(userData.member_introduction);
            if (userData.member_icon != null) {
                $("#headPhoto").attr("src", "../img/" + userData.member_icon);
            }
            loginBtn.style.display = "none";
            logoutBtn.style.display = "block";
            if (userData.member_rank == "管理者") {
                isManager = true;
            }
        }
    }

    $("#editbtn").on("click", function () {
        $("input[name='member_nickname']").attr("value", userData.member_nickname);
        $("input[name='member_region']").attr("value", userData.member_region);
        $("textarea[name='member_introduction']").val(userData.member_introduction);
        $("input[name='member_birthday']").attr("value", userData.member_birthday);
    })

    $("#createMember").on("click", async function () {
        var dataToServer = {
            member_account: $("#createAccountTextBox").val(),
            member_password: $("#createPasswordMTextBox").val(),
            member_email: $("#createMailTextBox").val()
        }
        var p = $.ajax({
            type: "post",
            url: "http://localhost:8080/doRegister",
            data: dataToServer
        })
        var result = await p;
        alert(result);
    })

    $("#loginMember").on("click", async function () {
        i++;
        var dataToServer = {
            member_account: $("#loginAccountTextBox").val(),
            member_password: $("#loginPasswordMTextBox").val()
        }
        var p = $.ajax({
            type: "post",
            url: "http://localhost:8080/loginRegister",
            data: dataToServer
        })
        var result = await p;
        downloadAndRefreshUI();
        alert(result);
        console.log(i);
    })

    $("#logoutBtn").on("click", async function () {
        var p = $.ajax({
            type: "get",
            url: "http://localhost:8080/logoutRegister",
        })
        var result = await p;
        alert(result);
        location.href = "../index.html";
    })

    $("#changeMember").on("click", async function () {
        var dataToServer = {
            member_account: $("#changeAccountTextBox").val(),
            member_password: $("#changePasswordMTextBox").val(),
            member_email: $("#changeMailTextBox").val()
        }
        var p = $.ajax({
            type: "post",
            url: "http://localhost:8080/changeRegister",
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
            url: "http://localhost:8080/forgetRegister",
            data: dataToServer
        })
        var result = await p;
        alert(result);
    })

})  // end of init.
