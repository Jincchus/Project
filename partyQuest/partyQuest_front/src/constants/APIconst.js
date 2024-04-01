export const URLCONST = {
    APP_BASE_URL : import.meta.env.VITE_APP_BASE_URL ,
    LOGIN : "/api/member/login",
    LOGOUT: "/api/member/sign-out",
    STUDY_LOCATIONS : "/api/study/locations",
    STUDY_LIST : "/api/study/list",
    STUDY_IMAGE_URL : "/api/resources/images",
    STUDY_SEARCH_URL : "/api/study/search",
    CATE_ALL_URL: "api/category/allCate",
    CATE_CACHED_ALL_URL: "api/category/cached/allCate",
    MEMBER_LIST: "api/member/list",
    PARTY_LOCATION_LIST: "/api/member/cached/locations",
    MBTI_LIST : "/api/member/cached/mbtis",
    PROFILE_GET : "/api/member/profile",
    PROFILE_UPDATE : "/api/member/profile/modify",
    AVATAR_UPDATE : "/api/member/profile/modify/avatar"
}
export const CSCONST = {
    NOTICE: 1,
    FAQ: 2
};
