
var items;
var r;
function downloadAndRefreshUI() {
    var p = $.ajax({
        type: "get",
        async:!1,
        url: "/pages/frontCommodity"
    })
    var temp = $.ajax({
        type: "get",
        async:!1,
        url: "/api/manage/popularItemCatagories/"
    })
    item =p["responseJSON"];
    console.log(item)
    popularCategories = temp["responseJSON"];
    console.log(popularCategories)
    popularCategories = popularCategories.categories;

    //返回的JSON資料會存在result
    // newsList = JSON.parse(result);
    items = item["litems"];
    iic = item.item_picture;
    refreshUI(items,popularCategories);

}
function refreshUI(items,popularCategories) {
    console.log(popularCategories)
    for (var i = 0; i < popularCategories.length; i++) {
        $("#popCategories").append(
            `<li><span class="list-group-item list-group-item-action popCategories">${popularCategories[i]}</span></li>`
        )
    }
    console.log(items)
    //             console.log(items.length);
    $(".bigbox").empty();
    console.log(Object.keys(items).length)
        $(".bigbox").append(`<div class="row superbigbox"></div>`);
        let j=1;
        for (let i = 0; i <items.length; i++) {
            console.log(i + " ")
            let src = iic[i]["picture_url"];
            let newsItem = items[i];

            //商品item_id 用來做連結到CART 跟商品頁面
            let item_id = newsItem.item_id;
            //商品敘述
            let item_description = `${newsItem.item_description}`;
            //商品名稱
            let item_name = `${newsItem.item_name}`;
            //商品狀態
            let item_state_id = `${newsItem.item_state_id}`
            //商品價格
            let item_price = `${newsItem.item_price}`
            let liTemplate = `<div class="col-3">
                                <div class="px-2 py-3">
                                    <div class="card outerBox">
                                      <a><img src="../img/${src}" data-value="${item_id}" class="img-fluid rounded-3 enterProduct" style="height:300px;width:auto;margin:auto" ></a>
                                        <p class="text-center card-title fs-2"> ${item_name}</p>
                                        <div class="mx-4">
                                            <span class="ellipsis2">${item_description}</span>
                                        </div>
                                        <div class="px-3 py-3">
                                            <div class="d-flex justify-content-between mx-3 my03">
                                                <span>${item_price}</span>                                              
                                                <button class="addCart btn btn-primary" name="${item_id}">加入購物車</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>  `;

            let liTemplate2 = `<div class="col-3">
                    <div class="px-1 py-3">
                        <div class="card outerBox border border-primary border-2">
                            <div class="bg-white text-center border border-primary border-3">
                                <a><img src="../img/${src}" data-value="${item_id}" class="card-img-top img-fluid bg-dark enterProduct" style="height:300px;width:auto;margin:auto"></a>
                                </div>
                            <h5 class="text-center card-title my-1 fs-2">${item_name}</h5>
                            <div class="mx-4">
                                <span class="ellipsis2">${item_description}</span>
                            </div>
                            <div class="">
                                <div class="d-flex justify-content-between   mx-3 my-2 ">
                                    <span class="fs-3 align-self-center"><i class="bi bi-currency-dollar"></i>${item_price}</span>
                                    <button class="addCart btn btn-info text-white border-3 border-dark px-3" name="${item_id}"><i class="bi bi-cart-fill "></i><br>加入</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`
            
            // console.log(liTemplate)
            // console.log($('.helloworld'+j))
            // $('.helloworld'+j).append(liTemplate);
            $('.superbigbox').append(liTemplate2);
            //進入商品頁面 把item_id 存到LocalStorage到商品頁面再用AJAX取回對應資料

            $(".enterProduct").click(function () {
                localStorage.setItem("item_id", $(this).attr("data-value"));
                location.href = "html/product.html";
            })

        }
        //加到購物車

    // }

}

function refreshUIs() {
    //   console.log(r);
    //               console.log(r.itps);
    //   console.log(r.itps[0][0].picture_url);
    $(".bigbox").empty();
    console.log(r)
    // r=r["responseJSON"]
    // if (its != "" && its != null && its != undefined) {
        $(".bigbox").append(`<div class="row superbigbox"></div>`);
        // console.log(r.itps)
        for (let i = 0; i <r.itps.length; i++) {
            console.log(i+" ")
            //商品圖片
            let src = r.itps[i][0].picture_url;
                                // console.log(src);
            let newsItem = r.its[i];

            //商品item_id 用來做連結到CART 跟商品頁面
            let item_id = newsItem.item_id;
            //商品敘述
            let item_description = `${newsItem.item_description}`;
            //商品名稱
            let item_name = `${newsItem.item_name}`;
            //商品狀態
            let item_state_id = `${newsItem.item_state_id}`
            //商品價格
            let item_price = `${newsItem.item_price}`
            let liTemplate = `<div class="col-3">
                                    <div class="px-2 py-3">
                                        <div class="card outerBox">
                                        <a><img src="../img/${src}" data-value="${item_id}" class="img-fluid rounded-3 enterProduct" style="height:300px;width:auto;margin:auto" ></a>
                                            <p class="text-center card-title fs-2"> ${item_name}</p>
                                            <div class="mx-4">
                                                <span class="ellipsis2">${item_description}</span>
                                            </div>
                                            <div class="px-3 py-3">
                                                <div class="d-flex justify-content-between mx-3 my03">
                                                    <span>${item_price}</span>                                              
                                                    <button class="addCart" name="${item_id}">加入購物車</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>  `;
            let liTemplate2= `<div class="col-3">
            <div class="px-1 py-3">
                <div class="card outerBox border border-primary border-2">
                    <div class="bg-white text-center border border-primary border-3">
                        <a><img src="../img/${src}" data-value="${item_id}" class="card-img-top img-fluid bg-dark enterProduct" style="height:300px;width:auto;margin:auto"></a>
                        </div>
                    <h5 class="text-center card-title my-1 fs-2">${item_name}</h5>
                    <div class="mx-4">
                        <span class="ellipsis2">${item_description}</span>
                    </div>
                    <div class="">
                        <div class="d-flex justify-content-between   mx-3 my-2 ">
                            <span class="fs-3 align-self-center"><i class="bi bi-currency-dollar"></i>${item_price}</span>
                            <button class="addCart btn btn-info text-white border-3 border-dark px-3" name="${item_id}"><i class="bi bi-cart-fill "></i><br>加入</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>`;
            $('.superbigbox').append(liTemplate2);
            //進入商品頁面 把item_id 存到LocalStorage到商品頁面再用AJAX取回對應資料

            $(".enterProduct").click(function () {
                localStorage.setItem("item_id", $(this).attr("data-value"));
                location.href = "html/product.html";
            })


        }
        // $(".bigbox").append(`</div>`)
    
}
function showRegion() {
    $("#extraRadio").empty();
    $("#extraRadio").append(
        '<label class="mx-1 my-1">地區</label><br />' +
        '<input type="radio" name="category_region" value="台灣">' +
        '<label for="台灣">台灣</label>' +
        '<input type="radio" name="category_region" value="香港">' +
        '<label for="香港">香港</label>' +
        '<input type="radio" name="category_region" value="日本">' +
        '<label for="日本">日本</label>' +
        '<input type="radio" name="category_region" value="韓國">' +
        '<label for="韓國">韓國</label><br>' +
        '<input type="radio" name="category_region" value="中國">' +
        '<label for="中國">中國</label>' +
        '<input type="radio" name="category_region" value="美國">' +
        '<label for="美國">美國</label>' +
        '<input type="radio" name="category_region" value="歐洲">' +
        '<label for="歐洲">歐洲</label>' +
        '<input type="radio" name="category_region" value="其他">' +
        '<label for="其他">其他</label><br>' +
        '<div id="typeRadio"></div>'
    )
}




//加到購物車





$(function(){

    downloadAndRefreshUI();

    //??????????????????????????????????

    $(".bigbox").on("click", ".addCart", function () {
        //                     console.log($(this).attr("name"));
        var items_id = $(this).attr("name")
        console.log(items_id)
        var p = $.ajax({
            type: "post",
            async: !1,
            url: "/addInCart",
            data: {
                items_id: items_id
            }
        })
        // alert("加入購物車");
        $.toast({
            heading: '通知',
            text: "加入購物車",
            icon: 'info',
            loader: true,       
            loaderBg: '#9EC600' ,
            showHideTransition: 'fade',
            position:"top-center",
        })
    })
    $(".addCart").click(function () {
        //                 console.log($(this).attr("name"));
        var items_id = $(this).attr("name")
        var p = $.ajax({
            type: "post",
            async: !1,
            url: "/addInCart",
            data: {
                items_id: items_id
            }
        })
    })
    //??????????????????????????????????


    
    $("#itemName").on("input", function () {
        $("#itemTag").val("");
        $("#minPrice").val("");
        $("#maxPrice").val("");
        var radio = document.querySelector("input[name=category_type]:checked");
        if(radio!=null){
        radio.checked = false;}
        $("#extraRadio").empty();
    })
    $("#itemTag").on("input", function () {
        $("#itemName").val("");
        $("#minPrice").val("");
        $("#maxPrice").val("");
        var radio = document.querySelector("input[name=category_type]:checked");
        if(radio!=null){
        radio.checked = false;}
        $("#extraRadio").empty();
    })
    $("#minPrice").on("input", function () {
        $("#itemName").val("");
        $("#itemTag").val("");
        var radio = document.querySelector("input[name=category_type]:checked");
        radio.checked = false;
        $("#extraRadio").empty();
    })
    $("#maxPrice").on("input", function () {
        $("#itemName").val("");
        $("#itemTag").val("");
        var radio = document.querySelector("input[name=category_type]:checked");
        radio.checked = false;
        $("#extraRadio").empty();
    })


    $("#searchItem").on("click", async function () {
        if ($("#itemName").val() != "") {
            var postdata = {
                item_name: $("#itemName").val()
            }
            var temp = $.ajax({
                type: "post",
                url: "/api/searchItem/itemName",
                data: postdata
            })
            r = await temp;
            //                 console.log(r);
        } else if ($("#itemTag").val() != "") {
            var postdata = {
                item_tag: $("#itemTag").val()
            }
            var temp = $.ajax({
                type: "post",
                url: "/api/searchItem/itemTag",
                data: postdata
            })
            r = await temp;
            //                 console.log(r);
        } else if ($("#minPrice").val() != "" && $("#maxPrice").val() != "") {
            var temp = $.ajax({
                type: "get",
                url: "/api/searchItem/price/" + $("#minPrice").val() + "/" + $("#maxPrice").val()
            })
            r = await temp;
            //                 console.log(r);
        } else if ($("[name='category_type']:checked").val()) {
            switch ($("[name='category_type']:checked").val()) {
                case "漫畫":
                    var postdata = {};
                    var temp;
                    if ($("[name='comic_type']:checked").val()) {
                        postdata = {
                            category_name:
                                $("[name='category_region']:checked").val() +
                                $("[name='comic_type']:checked").val() +
                                $("[name='category_type']:checked").val()
                        }
                        temp = $.ajax({
                            type: "post",
                            url: "/api/searchItem/itemCategory",
                            data: postdata
                        })
                    } else if ($("[name='category_region']:checked").val()) {
                        postdata = {
                            name01: $("[name='category_type']:checked").val(),
                            name02: $("[name='category_region']:checked").val()
                        }
                        temp = $.ajax({
                            type: "post",
                            url: "/api/searchItem/itemPartCategory",
                            data: postdata
                        })
                    } else {
                        postdata = {
                            category_name: $("[name='category_type']:checked").val()
                        }
                        temp = $.ajax({
                            type: "post",
                            url: "/api/searchItem/itemCategory",
                            data: postdata
                        })
                    }

                    r = await temp;
                    //                         console.log(r);
                    break;
                case "小說":
                    var postdata = {};
                    var temp;
                    if ($("[name='novel_type']:checked").val()) {
                        if ($("[name='novel_type']:checked").val() == "純文學") {
                            postdata = {
                                category_name:
                                    $("[name='category_region']:checked").val() +
                                    $("[name='novel_type']:checked").val()
                            }
                        } else {
                            postdata = {
                                category_name:
                                    $("[name='category_region']:checked").val() +
                                    $("[name='novel_type']:checked").val() +
                                    $("[name='category_type']:checked").val()
                            }
                        }

                        temp = $.ajax({
                            type: "post",
                            url: "/api/searchItem/itemCategory",
                            data: postdata
                        })
                    } else if ($("[name='category_region']:checked").val()) {
                        postdata = {
                            name01: $("[name='category_type']:checked").val(),
                            name02: $("[name='category_region']:checked").val()
                        }
                        temp = $.ajax({
                            type: "post",
                            url: "/api/searchItem/itemPartCategory",
                            data: postdata
                        })
                    } else {
                        postdata = {
                            category_name: $("[name='category_type']:checked").val()
                        }
                        temp = $.ajax({
                            type: "post",
                            url: "/api/searchItem/itemCategory",
                            data: postdata
                        })
                    }

                    r = await temp;
                    //                         console.log(r);
                    break;
                case "桌遊":
                    var postdata = {};
                    var temp;
                    if ($("[name='boardgame_type']:checked").val()) {
                        postdata = {
                            category_name:
                                $("[name='boardgame_size']:checked").val() +
                                $("[name='boardgame_type']:checked").val() +
                                $("[name='category_type']:checked").val()
                        }
                        temp = $.ajax({
                            type: "post",
                            url: "/api/searchItem/itemCategory",
                            data: postdata
                        })
                    } else if ($("[name='boardgame_size']:checked").val()) {
                        postdata = {
                            name01: $("[name='category_type']:checked").val(),
                            name02: $("[name='boardgame_size']:checked").val()
                        }
                        temp = $.ajax({
                            type: "post",
                            url: "/api/searchItem/itemPartCategory",
                            data: postdata
                        })
                    } else {
                        postdata = {
                            category_name: $("[name='category_type']:checked").val()
                        }
                        temp = $.ajax({
                            type: "post",
                            url: "/api/searchItem/itemCategory",
                            data: postdata
                        })
                    }

                    r = await temp;
                    //                         console.log(r);
                    break;
                case "主機":
                    var postdata = {};
                    if ($("[name='console_type']:checked").val()) {
                        postdata = {
                            category_name: $("[name='console_type']:checked").val()
                        }
                    } else {
                        postdata = {
                            category_name: $("[name='category_type']:checked").val()
                        }
                    }
                    var temp = $.ajax({
                        type: "post",
                        url: "/api/searchItem/itemCategory",
                        data: postdata
                    })
                    r = await temp;
                    //                         console.log(r);
                    break;
            }

        }
        refreshUIs();
    })

    $("[name='category_type']").change(function () {
        $("#itemName").val("");
        $("#itemTag").val("");
        $("#minPrice").val("");
        $("#maxPrice").val("");
        switch ($("[name='category_type']:checked").val()) {
            case "漫畫":
                showRegion();
                $("[name='category_region']").change(function () {
                    $("#typeRadio").empty();
                    $("#typeRadio").append(
                        '<label class="mx-1 my-1">類別</label><br />' +
                        '<input type="radio" name="comic_type" value="熱血">' +
                        '<label for="熱血">熱血</label>' +
                        '<input type="radio" name="comic_type" value="冒險">' +
                        '<label for="冒險">冒險</label>' +
                        '<input type="radio" name="comic_type" value="奇幻">' +
                        '<label for="奇幻">奇幻</label>' +
                        '<input type="radio" name="comic_type" value="科幻">' +
                        '<label for="科幻">科幻</label>' +
                        '<input type="radio" name="comic_type" value="競技">' +
                        '<label for="競技">競技</label>' +
                        '<input type="radio" name="comic_type" value="運動">' +
                        '<label for="運動">運動</label>' +
                        '<input type="radio" name="comic_type" value="校園">' +
                        '<label for="校園">校園</label>' +
                        '<input type="radio" name="comic_type" value="歷史">' +
                        '<label for="歷史">歷史</label>' +
                        '<input type="radio" name="comic_type" value="推理">' +
                        '<label for="推理">推理</label>' +
                        '<input type="radio" name="comic_type" value="懸疑">' +
                        '<label for="懸疑">懸疑</label>' +
                        '<input type="radio" name="comic_type" value="恐怖">' +
                        '<label for="恐怖">恐怖</label>' +
                        '<input type="radio" name="comic_type" value="美食">' +
                        '<label for="美食">美食</label>' +
                        '<input type="radio" name="comic_type" value="戀愛">' +
                        '<label for="戀愛">戀愛</label>' +
                        '<input type="radio" name="comic_type" value="日常">' +
                        '<label for="日常">日常</label>' +
                        '<input type="radio" name="comic_type" value="音樂">' +
                        '<label for="音樂">音樂</label>' +
                        '<input type="radio" name="comic_type" value="治癒">' +
                        '<label for="治癒">治癒</label>' +
                        '<input type="radio" name="comic_type" value="社會">' +
                        '<label for="社會">社會</label>' +
                        '<input type="radio" name="comic_type" value="搞笑">' +
                        '<label for="搞笑">搞笑</label>' +
                        '<input type="radio" name="comic_type" value="萌系">' +
                        '<label for="萌系">萌系</label>' +
                        '<input type="radio" name="comic_type" value="後宮">' +
                        '<label for="後宮">後宮</label>' +
                        '<input type="radio" name="comic_type" value="BL">' +
                        '<label for="BL">BL</label>' +
                        '<input type="radio" name="comic_type" value="GL">' +
                        '<label for="GL">GL</label>' +
                        '<input type="radio" name="comic_type" value="四格">' +
                        '<label for="四格">四格</label>' +
                        '<input type="radio" name="comic_type" value="武俠">' +
                        '<label for="武俠">武俠</label>' +
                        '<input type="radio" name="comic_type" value="格鬥">' +
                        '<label for="格鬥">格鬥</label>' +
                        '<input type="radio" name="comic_type" value="機戰">' +
                        '<label for="機戰">機戰</label>' +
                        '<input type="radio" name="comic_type" value="同人">' +
                        '<label for="同人">同人</label>' +
                        '<input type="radio" name="comic_type" value="溫馨">' +
                        '<label for="溫馨">溫馨</label>' +
                        '<input type="radio" name="comic_type" value="獵奇">' +
                        '<label for="獵奇">獵奇</label>' +
                        '<input type="radio" name="comic_type" value="其他">' +
                        '<label for="其他">其他</label>'
                    )
                })
    
                break;
            case "小說":
                showRegion();
                $("[name='category_region']").change(function () {
                    $("#typeRadio").empty();
                    $("#typeRadio").append(
                        '<label class="mx-1 my-1">類別</label><br />' +
                        '<input type="radio" name="novel_type" value="奇幻">' +
                        '<label for="奇幻">奇幻</label>' +
                        '<input type="radio" name="novel_type" value="科幻">' +
                        '<label for="科幻">科幻</label>' +
                        '<input type="radio" name="novel_type" value="玄幻">' +
                        '<label for="玄幻">玄幻</label>' +
                        '<input type="radio" name="novel_type" value="歷史">' +
                        '<label for="歷史">歷史</label>' +
                        '<input type="radio" name="novel_type" value="武俠">' +
                        '<label for="武俠">武俠</label>' +
                        '<input type="radio" name="novel_type" value="推理 ">' +
                        '<label for="推理">推理</label>' +
                        '<input type="radio" name="novel_type" value="懸疑">' +
                        '<label for="懸疑">懸疑</label>' +
                        '<input type="radio" name="novel_type" value="恐怖">' +
                        '<label for="恐怖">恐怖</label>' +
                        '<input type="radio" name="novel_type" value="言情">' +
                        '<label for="言情">言情</label>' +
                        '<input type="radio" name="novel_type" value="穿越">' +
                        '<label for="穿越">穿越</label>' +
                        '<input type="radio" name="novel_type" value="古典">' +
                        '<label for="古典">古典</label>' +
                        '<input type="radio" name="novel_type" value="輕">' +
                        '<label for="輕">輕小說</label>' +
                        '<input type="radio" name="novel_type" value="同人">' +
                        '<label for="同人">同人</label>' +
                        '<input type="radio" name="novel_type" value="純文學">' +
                        '<label for="純文學">純文學</label>' +
                        '<input type="radio" name="novel_type" value="其他">' +
                        '<label for="其他">其他</label>'
                    )
                })
    
                break;
            case "桌遊":
                $("#extraRadio").empty();
                $("#extraRadio").append(
                    '<label class="mx-1 my-1">大小<br />' +
                    '<input type="radio" name="boardgame_size" value="01">' +
                    '<label for="01">小型</label>' +
                    '<input type="radio" name="boardgame_size" value="02">' +
                    '<label for="02">中型</label>' +
                    '<input type="radio" name="boardgame_size" value="06">' +
                    '<label for="03">大型</label><br>' +
                    '<div id="typeRadio"></div>'
                )
                $("[name='boardgame_size']").change(function () {
                    $("#typeRadio").empty();
                    $("#typeRadio").append(
                        '<label class="mx-1 my-1">類別<br />' +
                        '<input type="radio" name="boardgame_type" value="兒童">' +
                        '<label for="兒童">兒童</label>' +
                        '<input type="radio" name="boardgame_type" value="家庭">' +
                        '<label for="家庭">家庭</label>' +
                        '<input type="radio" name="boardgame_type" value="派對">' +
                        '<label for="派對">派對</label><br>' +
                        '<input type="radio" name="boardgame_type" value="陣營">' +
                        '<label for="陣營">陣營</label>' +
                        '<input type="radio" name="boardgame_type" value="策略">' +
                        '<label for="策略">策略</label>' +
                        '<input type="radio" name="boardgame_type" value="主題">' +
                        '<label for="主題">主題</label><br>' +
                        '<input type="radio" name="boardgame_type" value="抽象">' +
                        '<label for="抽象">抽象</label>' +
                        '<input type="radio" name="boardgame_type" value="集換">' +
                        '<label for="集換">集換</label>'
                    )
                })
    
                break;
            case "主機":
                $("#extraRadio").empty();
                $("#extraRadio").append(
                    '<label class="mx-1 my-1">類別<br />' +
                    '<input type="radio" name="console_type" value="0001">' +
                    '<label for="0001">家用主機</label>' +
                    '<input type="radio" name="console_type" value="0002 ">' +
                    '<label for="0002">掌上主機</label>' +
                    '<input type="radio" name="console_type" value="0003">' +
                    '<label for="0003">大型機台</label><br>'
                )
                break;
            }
        }
    )
    $("#popCategories").on("click", ".popCategories", function () {
        var postdata = {
            category_name: this.innerText
        }
        temp = $.ajax({
            type: "post",
            url: "/api/searchItem/itemCategory",
            async:!1,
            data: postdata
        })
        r =temp["responseJSON"];
        refreshUIs();
    })

    console.log("hello")

});