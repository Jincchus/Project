
        /* json */
            
        let jsonData = {
            "categories" : [
                {
                    "cate1" : "10",
                    "c1Name" : "브랜드 의류",
                    "subCategories" : [
                        {"cate2" : "10","c2Name" : "브랜드 여성"},
                        {"cate2" : "11","c2Name" : "브랜드 남성"}
                    ]
                },
                {
                    "cate1" : "11",
                    "c1Name" : "패션/잡화",
                    "subCategories" : [
                        {"cate2" : "10","c2Name" : "여성의류"},
                        {"cate2" : "11","c2Name" : "남성의류"}
                    ]
                }
            ]
        };
        
        // 함수를 사용하여 카테고리 목록 생성
    function generateCategoryList() {
        let categories = jsonData.categories;
        var categoryMenu = $("ul.category");

        categories.forEach(function (category) {
            var c1Name = category.c1Name;
            var subCategories = category.subCategories;

            var categoryItem = $("<li>");
            categoryItem.append('<a href="#"><i class="fas fa-' + getCategoryIcon(c1Name) + '"></i>' + c1Name + '</a>');

            if (subCategories && subCategories.length > 0) {
                var subCategoryList = $("<ol>");
                subCategories.forEach(function (subCategory) {
                    subCategoryList.append('<li><a href="#">' + subCategory.c2Name + '</a></li>');
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
    }); 