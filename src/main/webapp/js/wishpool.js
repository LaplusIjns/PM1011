var type = 1;
var list = [];
var len;
allforthcol=[]
allforthcol[0]=document.getElementById('firstcol')
allforthcol[1]=document.getElementById('secondcol')
allforthcol[2]=document.getElementById('thirdcol')
allforthcol[3]=document.getElementById('forthcol')
async function accessAndRefresh() {
    switch (type) {
        case 1:
            var temp = $.ajax({
                type: "get",
                url: "/api/wishpool/recent"
            });
            break;
        case 2:
            var temp = $.ajax({
                type: "get",
                url: "/api/wishpool/popular"
            });
            break;
    }

    list = await temp;

    fillUI();
}
function fillUI() {
    // $(".row").empty();
    $("#firstcol").empty();
    $("#secondcol").empty();
    $("#thirdcol").empty();
    $("#forthcol").empty();
    if (list.length > 12) {
        len = 12;
    } else {
        len = list.length;
    }
    // allforthcol=[]
    for (var i = 0; i < (len % 4 == 0 ? len / 4 : (len / 4) + 1); i++) {
        // $(".row").append(`<div class="col-3 mb-3 " id = "col${i}"></div>`);
        for (var k = 0; k < 4; k++) {
            var index = k + i * 4;
            // console.log(allforthcol[k])
            tmp='<div class="card mb-3"><div>'
            +'<img src="../img/big2.jpg" class="card-img-top col-12" alt="...">'
            // +'</div></div>'
            +'<div class="card-body" id="'+list[index].wishlist_id+'">'
            +'<p class="card-text">'+list[index].item_name+'</p>'
            +'<p class="card-text">'+list[index].item_description+'</p>'
            +'<img src="../img/like.png" alt="" width="20px" height="20px"'
            +'style="display: inline;" class="like">'
            +'<p style="display: inline;">'+list[index].item_likes+'</p>'
            +'</div></div>'+'</div></div>'

            // $tmp=$(tmp)
            // $(`#col${i}`).append($tmp)
            // $(allforthcol[k]).append($tmp)
            // allforthcol[k].appendChild(tmp)
            // console.log(allforthcol[k])
            // console.log(k)
            allforthcol[k].insertAdjacentHTML("beforeend",tmp)
            // console.log(allforthcol[k])
        }

    }
}
function refreshUI() {

    var documentElement = document.documentElement;

    if (documentElement.scrollTop + 10 >= documentElement.scrollHeight - documentElement.clientHeight) {
        switch (type) {
            case 1:
                var temp = $.ajax({
                    type: "get",
                    async: !1,
                    url: "/api/wishpool/recent"
                });
                break;
            case 2:
                var temp = $.ajax({
                    type: "get",
                    async: !1,
                    url: "/api/wishpool/popular"
                });
                break;
        }

        var newlist = temp;

        for (var i = 0; i < 2; i++) {
            // var row = document.querySelector(".row");
            // row.insertAdjacentHTML('beforeend', `<div class="col-3 mb-3 " id = "col${i + 3}"></div>`)
            for (var k = 0; k < 4; k++) {
                var index = k + i * 4;
                var creatediv = 
                tmp='<div class="card mb-3"><div>'
                +'<img src="../img/big2.jpg" class="card-img-top col-12" alt="...">'
                // +'</div></div>'
                +'<div class="card-body" id="'+newlist['responseJSON'][index + 12]['wishlist_id']+'">'
                +'<p class="card-text">'+newlist['responseJSON'][index + 12]['item_name']+'</p>'
                +'<p class="card-text">'+newlist['responseJSON'][index + 12]['item_description']+'</p>'
                +'<img src="../img/like.png" alt="" width="20px" height="20px"'
                +'style="display: inline;" class="like">'
                +'<p style="display: inline;">'+newlist['responseJSON'][index + 12]["item_likes"]+'</p>'
                +'</div></div>'+'</div></div>'

                // $tmp=$(tmp)
                // console.log(i)
                // console.log(allforthcol[i+3])
                // $(`#col${i + 3}`).append($tmp)
                // allforthcol[i+3].appendChild(tmp)
                // allforthcol[i+3].appendChild("beforeend",tmp)
                allforthcol[k].insertAdjacentHTML("beforeend",tmp)
                // document.getElementById('forthcol').appendChild(tmp)
                // $(allforthcol[i+3]).append($tmp)
            }

        }


    }
}

function showToast(heading, message) {

    $.toast({
        text: message, // Text that is to be shown in the toast
        heading: heading, // Optional heading to be shown on the toast
        icon: 'warning', // Type of toast icon
        showHideTransition: 'fade', // fade, slide or plain
        allowToastClose: true, // Boolean value true or false
        hideAfter: 3000, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
        stack: 5, // false if there should be only one toast at a time or a number representing the maximum number of toasts to be shown at a time
        position: 'top-right', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values
        textAlign: 'left',  // Text alignment i.e. left, right or center
        loader: true,  // Whether to show loader or not. True by default
        loaderBg: '#9ec600',  // Background color of the toast loader
        beforeShow: function () { }, // will be triggered before the toast is shown
        afterShown: function () { }, // will be triggered after the toat has been shown
        beforeHide: function () { }, // will be triggered before the toast gets hidden
        afterHidden: function () { }  // will be triggered after the toast has been hidden
    });
}

$(function () {

    $("#recent").click(function () {
        type = 1;
        accessAndRefresh();
    })
    $("#popular").click(function () {
        type = 2;
        accessAndRefresh();
    })
    $("#insert").click(function () {
        $("#nameTextBox").val("");
        $("#descriptionTextBox").val("");
        $("#priceTextBox").val("");
        $("#newsModal").modal({ backdrop: "static" });
    })
    $("#okButton").click(function () {
        var newItem = {
            item_name: $("#nameTextBox").val(),
            item_description: $("#descriptionTextBox").val(),
            expect_price: $("#priceTextBox").val()
        };
        $.ajax({
            type: "post",
            url: "/home/news",
            data: newItem
        })
            .then(function (e) {
                $.get("/api/wishpool/recent", function (e) {
                    newsList = e;
                    accessAndRefresh();
                })
            })
        $("#newsModal").modal("hide");
    }
    )

    accessAndRefresh();
    window.addEventListener('scroll', refreshUI);

    $(".row").on("click", ".like", async function () {

        var temp = $.ajax({
            type: "put",
            url: "/api/wishpool/like/" + this.parentNode.id
        })

        var r = await temp;
        accessAndRefresh();
        showToast("已按讚", "讚！");
    })

    // 使用方式:
    // showToast("標題", "提示文字") 例如:
    // showToast("Hint", "請點一下正確的圖案");

})  // end of init.
