let protocol;
let rootURL;
protocol = window.location.protocol;
rootURL = window.location.host;
const getAllCate1 = async () => {
    const apiURL = protocol + "//" + rootURL + "/api/product/fullCate";
    let response = await fetch(apiURL)
        .then(res => {
            return res.json();
        });
    let obj = new Object();
    let cate1Name = "";
    let curData = [];
    //Object 순회 할때는 for of로 순회한다.
    for (const res of response) {
        cate1Name = res["cate1Name"];
        curData = res["cate2ListDto"];
        obj[cate1Name] = curData;
    }
    return obj;
};
//
window.onload = async () => {
    let allCate = await getAllCate1();
    let productList = new Object();
    console.log(allCate);
    const rootUl = document.getElementById("rootUl");


    // 1차 카테 생성 시작
    let cate1ListString = "<li><i class=\"fa fa-bars\" aria-hidden=\"true\"></i>카테고리</li>";
    let filteringRootURL = protocol + "//" + rootURL + "/product/list/";
    for (const key in allCate) {
        let keyList = key.split(' ');
        let cate1Name = keyList[0];
        let cate1PK = keyList[1];
        cate1ListString+= "<li>"
        cate1ListString+= "<a href=\"#\">\n";
        cate1ListString += "    <i class=\"fas fa-laptop\"></i>" + cate1Name + "<i class=\"fas fa-angle-right\"></i>\n";
        cate1ListString += "</a>";
        //2차 카테 등록
        cate1ListString+="<ol>"
        //2차 카테 for문
        for (const cate2 of allCate[key]) {
            const cate2PK = cate2["cate2Id"];
            const cate2Name = cate2["cate2Name"];
            cate1ListString += "<li><a href=\"" + filteringRootURL + cate2PK + "\">" + cate2Name + "</a></li>";
        }
        cate1ListString+="</ol>"
        //2차 카테 끝
        cate1ListString +="</li>"
    }

    //카테 등록
    rootUl.innerHTML = cate1ListString;


}