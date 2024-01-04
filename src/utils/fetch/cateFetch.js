import {axiClient} from "@/utils/globalAxios";
import {URLCONST} from "@/constants/APIconst";

export const fetchCates = async () => {
    const cates = {};
    const major = {curMajor: 'none', majors: []}

    await axiClient.get(URLCONST.CATE_CACHED_ALL_URL)
        .then((response) => {
            response.data.forEach((element) => {
                const majorKey = element["majorName"];
                const tempObj = {};
                element["middleCates"].forEach((mid) => {
                    const middleKey = mid["middleName"];
                    const smallCates = mid["smallCates"];
                    const tempList = [];

                    smallCates.forEach((sm) => {
                        tempList.push(sm["smallName"] + " " + sm["id"]);
                    });
                    tempObj[middleKey] = tempList;
                });
                cates[majorKey] = tempObj;
                // cates의 key(major cate)들을 먼저 등록 해준다.
                major.majors = Object.keys(cates.value);
            });
            //console.log("my cate objs", cates.value);
        })
        .catch((err) => {
            //console.log(err);
        });
    return {fetchedCates:cates, fetchedMajor:major}
};