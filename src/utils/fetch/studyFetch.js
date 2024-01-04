import {axiClient, axiValid} from "@/utils/globalAxios";
import {URLCONST} from "@/constants/APIconst";

const SMALL_CATE_IDS = 'smallCateIds';
export const getLocations = ()=>{
    return axiClient.get(URLCONST.STUDY_LOCATIONS)
        .then(res=> res.data)
}

export const getStudies = async (sort) => {
    return await axiClient.get(URLCONST.STUDY_LIST+`?sort=${sort}`)
        .then(res => {
            console.log(res.data);
            return res.data;
        })
};
/**
 * accordion navbar와 서치 인풋에서 필터 조건에 맞는
 * 요청 URL을 보내서 모두 사용한다.
 * @param params : Object /search?middleCate=val?smallcates=val?page=val...
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export const searchStudy = async(params)=>{
    let smallCates = Array.from(params[SMALL_CATE_IDS]);
    let copiedParams = {...params};
    copiedParams[SMALL_CATE_IDS] = [...smallCates];
    console.log('copied params : ',copiedParams[SMALL_CATE_IDS])
    let searchParams = makeQueryString(copiedParams);
    console.log('후처리 됐냐?',copiedParams,'쿼리 스트링 : ',searchParams.toString());
    return await axiClient.get(`${URLCONST.STUDY_SEARCH_URL}?${searchParams.toString()}`
    )
        .then(res=>{
            console.log(res.data);
            return res.data;
        }).catch(err => console.log(err))
};

export const getPagedStudies = async ()=>{
    return await axiClient.get(`${URLCONST.STUDY_SEARCH_URL}`
    )
        .then(res=>{
            console.log(res.data);
            return res.data;
        }).catch(err => console.log(err))
}

const makeQueryString = (obj) => {
    for (const key of Object.keys(obj)) {
        if (obj[key] === null || obj[key].length === 0) {
            delete obj[key];
        }
    }
    const tmpObj = {...obj};
    delete tmpObj[SMALL_CATE_IDS];
    const myQueryString = new URLSearchParams(tmpObj);
    if (obj[SMALL_CATE_IDS] !== undefined) {
        if (obj[SMALL_CATE_IDS].length > 1) {
            for (const smallIds of obj[SMALL_CATE_IDS]) {
                myQueryString.append(SMALL_CATE_IDS, smallIds);
            }
        }else{
            myQueryString.set(SMALL_CATE_IDS, obj[SMALL_CATE_IDS][0]);
        }
    }
    return myQueryString;
}
