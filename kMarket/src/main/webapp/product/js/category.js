
/* json */
    
let jsonData = {
    "categories" : [
        {
            "cate1" : "10",
            "c1Name" : "브랜드 의류",
            "subCategories" : [
                {"cate2" : "10","c2Name" : "브랜드 여성"},
                {"cate2" : "11","c2Name" : "브랜드 남성"},
                {"cate2" : "12","c2Name" : "브랜드 진/캐쥬얼"}
            ]
        },
        {
            "cate1" : "11",
            "c1Name" : "패션/잡화",
            "subCategories" : [
                {"cate2" : "10","c2Name" : "여성의류"},
                {"cate2" : "11","c2Name" : "남성의류"},
                {"cate2" : "12","c2Name" : "언더웨어"}
            ]
        },
        {
            "cate1" : "12",
            "c1Name" : "유아동",
            "subCategories" : [
                {"cate2" : "10","c2Name" : "출산/육아"},
                {"cate2" : "11","c2Name" : "장난감/완구"},
                {"cate2" : "12","c2Name" : "유아동 의류"}
            ]
        }
    ]
};


// ajax를 통한 데이터 get요청
$.ajax({
	url : '/kMarket/product/category.do',
	type : 'GET',
	data : jsonData,
	dataType : 'json',
	success : function(response){
		// 성공시 실행 할 코드
		console.log("cateData success : " + response);
	}
});
        
// 함수를 사용하여 카테고리 목록 생성
function generateCategoryList() {
    let categories = jsonData.categories;
    var categoryMenu = $("ul.category");

    categories.forEach(function (category) {
        var c1Name = category.c1Name;
        var subCategories = category.subCategories;

        var categoryItem = $("<li>");
        categoryItem
        	.append('<a href="#"><i class="fas fa-' 
        			+ getCategoryIcon(c1Name) + '"></i>' + c1Name + '</a>');

        if (subCategories && subCategories.length > 0) {
            var subCategoryList = $("<ol>");
            subCategories.forEach(function (subCategory) {
			subCategoryList
				.append('<li><a href="./list.do?cate1=' + category.cate1
						 + '&cate2=' + subCategory.cate2 + '&pg=1">' 
						 + subCategory.c2Name + '</a></li>');
           
            });
            categoryItem.append(subCategoryList);
        }

        categoryMenu.append(categoryItem);
    });
}

// 카테고리 아이콘을 가져오는 함수 (임의로 아이콘 지정)
function getCategoryIcon(categoryName) {
    var icons = {
        "브랜드 의류": "tshirt",
        "패션/잡화": "laptop",
        // 다른 카테고리 아이콘 지정(카테고리별 icon이름은 다시 확인)
    };
    return icons[categoryName] || "utensils"; // 기본 아이콘
}

// 페이지 로드 시 카테고리 목록 생성
$(document).ready(function () {
    generateCategoryList();
    /*
     cate1.on("change", function () {
                let selectedCategory = $(this).val();
                let newOptionsString = "";
                let cate2List = data[selectedCategory];
                for (let i = 0; i < cate2List.length; i++) {
                    let cate2Name = cate2List[i];
                    newOptionsString += `<option value="${i + 10}">${cate2Name}</option>`;
                }
                cate2.html(newOptionsString);
            });
	*/
}); 