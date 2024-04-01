package kr.co.lotteon.response.admin.cs;


import kr.co.lotteon.entity.admin.cs.CsArticleCateEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class CsArticleCateAPIResponse {



    Map<String, List<String>> menu2Map = new HashMap<>();
    /**
     * csArticleIds는 반드시 menu1으로 정렬되어 들어와야한다.
     *
     * @param csArticleIds
     */


    public CsArticleCateAPIResponse(List<CsArticleCateEntity> csArticleIds) {
        String Menu1Name = "";
        for(CsArticleCateEntity pair : csArticleIds){
            if(Menu1Name.equals("")){
                Menu1Name = pair.getMenu1();
            }
            log.info("menu1Name:"+Menu1Name);
            String curMenu1 = "menu1Name: "+pair.getMenu1();
            if(menu2Map.containsKey(curMenu1)){
                List<String> menu2s = menu2Map.get(curMenu1);
                menu2s.add("menu2Name: "+pair.getMenu2());
            }else{
                List<String> newMenu2s = new ArrayList<>();
                newMenu2s.add("menu2Name: "+pair.getMenu2());
                menu2Map.put(curMenu1,newMenu2s);
            }
        }


    }

}
///////////////////////////////////////
// cate, menu1, menu2까지 가지고 오는 메서드
///////////////////////////////////////
//    private Map<String, Map<String, List<String>>> menuMap1 = new HashMap<>();를 메서드 위에 선언해주어야 함
//    public CsArticleCateAPIResponse(List<CsArticleCateEntity> csArticleIds){
//        Set<String> cateNameSet = new HashSet<>();
//        String cateName = "";//카테고리의 이름
//        String menu1Name = "";//menu1의 이름
//        Map<String, List<String>> menu1Andmenu2 = new HashMap<>();//menu1과 menu2를 담을 맵 생성
//
//        for (CsArticleCateEntity pair : csArticleIds) {//csArticleIds에서 DB에 있는 데이터 객체를 가져와서 foreach문으로 pair에 저장
//            String curCateName = pair.getCate();
//                if(!cateNameSet.contains(curCateName)){
//                    cateNameSet.add(curCateName); // 처음 조회하는 cateName인 경우 cateNameSet에 등록처리해준다.
//
//                    cateName = curCateName; //카테고리 이름
//                    menu1Name = pair.getMenu1();
//                    menu1Andmenu2 = new HashMap<>();
//
//                    List<String> newMenu2s = new ArrayList<>();
//                    newMenu2s.add("menu2Name: "+pair.getMenu2());//menu2가 들어감
//                    menu1Andmenu2.put("menu1Name: "+menu1Name, newMenu2s);//
//                    menuMap1.put(cateName, menu1Andmenu2);
//
//
//                }else{//맵의 키값이 curCateName인 경우 실행
//
//                    String curMenu1 = pair.getMenu1();
//                        if (!menu1Name.equals(curMenu1)) {
//
//                            // cate는 그대로고 menu1이 갱신될때
//                            List<String> newMenu2List = new ArrayList<>();
//                            newMenu2List.add("menu2Name: "+pair.getMenu2());
//                            menu1Andmenu2.put("menu1Name: "+curMenu1, newMenu2List);
//
//                            // menu1, 초기화
//                            menu1Name = curMenu1;
//                            continue;
//                        }
//                    List<String> curMenu2List = menu1Andmenu2.get("menu1Name: "+menu1Name);
//                    curMenu2List.add("menu2Name: "+pair.getMenu2());
//                }
//        }
//    }

