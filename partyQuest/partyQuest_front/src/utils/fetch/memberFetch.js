import {axiClient, axiValid, getValidatedAxios} from "@/utils/globalAxios";
import {URLCONST} from "@/constants/APIconst";
import {AUTH_CONST} from "@/constants/authConst";

const SMALL_CATE_IDS = 'smallCateIds';
const MIDDLE_CATE_ID = 'middleCateId';
export const getLocations = ()=>{
    return axiClient.get(URLCONST.STUDY_LOCATIONS)
        .then(res=> res.data)
}

export const getPagedMembers = async ()=>{
    return await axiClient.get(`${URLCONST.MEMBER_LIST}`
    )
        .then(res=>{
            console.log(res.data);
            return res.data;
        }).catch(err => console.log(err))
}

export const searchMember = async (params) => {
    let { middleCateId, preferredLocation } = params;
    let copiedParams = { ...params };
    let searchParams = makeQueryString(copiedParams);
    try {
        const response = await axiClient.get(`${URLCONST.MEMBER_LIST}?${searchParams.toString()}`);
        console.log("Response", response.data);
        return response.data;
    } catch (error) {
        console.error(error);
        throw error;
    }
};

export const getPartyLocationForUpdateProfile = async (accessToken) => {
    let validatedAxios = getValidatedAxios(accessToken);
    return await validatedAxios.get(URLCONST.PARTY_LOCATION_LIST)
        .then(res => res.data);
};
export const getMbtis = async (accessToken) => {
    let validatedAxios = getValidatedAxios(accessToken);
    return await validatedAxios.get(URLCONST.MBTI_LIST)
        .then(res => res.data);
};

export const getProfile = async (accessToken) => {
    console.log('[get profile called]')
    return await axiValid.get(URLCONST.PROFILE_GET, {
        headers: {
            Authorization: AUTH_CONST.AUTH_BEARER + accessToken,
            'Content-type': 'application/json; charset=UTF-8',},
    })
        .then(res => res.data);
};

export const updateProfile = async (data,accessToken) => {
    let validatedAxios = getValidatedAxios(accessToken);
    return await validatedAxios.post(URLCONST.PROFILE_UPDATE, data,{
        headers:{
            'Content-type': 'application/json; charset=UTF-8'
        }
    })
        .then(res => res.data);
};
export const updateAvatar = async (data, accessToken) => {
    let validatedAxios = getValidatedAxios(accessToken);
    return await validatedAxios.post(URLCONST.AVATAR_UPDATE, data,{
        headers:{
            "Content-Type": "multipart/form-data",
        }
    })
        .then(res => res.data);
};
const makeQueryString = (obj) => {
    for (const key of Object.keys(obj)) {
        if (obj[key] === null || obj[key].length === 0) {
            delete obj[key];
        }
    }
    const tmpObj = {...obj};
    delete tmpObj[SMALL_CATE_IDS];
    const myQueryString = new URLSearchParams(tmpObj);
    return myQueryString;
}
