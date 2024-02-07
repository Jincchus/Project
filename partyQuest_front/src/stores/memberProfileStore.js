import {defineStore} from "pinia";
import {ref} from "vue";

export const useProfileStore = defineStore('profileStore',()=>{
    const tmpAvatar = ref(null);
    const isFieldChanged = ref(false); // favoriteFields,favoriteTechsㄱㅏ 수정 되었는지 체킹 하는 로직
    const isMiddledChanged = ref(false);
    const isSmalldChanged = ref(false);
    const profileInfo =ref({
        avatarURL: '',
        nickName:'',
        bio:'',
        preferredLocation: '부산특별시',
        mbti: 'INTJ',
        favoriteFields: new Set([]),
        favoriteTechs: new Set([]),
        link1:'',
        link2:''
    })
    const tmpProfileInfo =ref({
        avatarURL: '',
        nickName:'',
        bio:'',
        preferredLocation: '부산특별시',
        preferredLocationId:0,
        mbti: 'INTJ',
        favoriteFields: new Set([]),
        favoriteTechs: new Set([]),
        link1:'',
        link2:''
    })
    /**
     *
     * @param val : boolean
     */
    const isFieldContain = (val)=>{
        tmpProfileInfo.value.favoriteFields.has(val)
    }
    /**
     *
     * @param val : boolean
     */
    const isTechContain = (val) => {
        tmpProfileInfo.value.favoriteTechs.has(val)
    }
    const getTmpAvatar = () => {
        return tmpAvatar.value;
    };
    const setTmpAvatar = (data)=>{
        tmpAvatar.value = data;
    }
    const getIsMiddleChanged = ()=>{
        return isMiddledChanged.value
    }
    const getIsSmallChanged = ()=>{
        return isSmalldChanged.value
    }
    const getProfileInfo = ()=>{
        return profileInfo.value;
    }
    const getTmpProfileInfo = ()=>{
        return tmpProfileInfo;
    }
    const getTmpNickName = ()=>{
        return tmpProfileInfo.value.nickName;
    }
    const getIsFieldChanged = () => {
        return isFieldChanged.value;
    };
    const addField = (field)=>{
        tmpProfileInfo.value.favoriteFields.add(field);
    }
    const addTech = (tech)=>{
        tmpProfileInfo.value.favoriteTechs.add(tech);
    }
    /**
     *
     * @param val : boolean
     */
    const setIsMiddleChanged = (val)=>{
        isMiddledChanged.value = val;
    }
    /**
     *
     * @param val : boolean
     */
    const setIsSmallChanged = (val)=>{
        isSmalldChanged.value = val;
    }
    const setTmpLocation = (id)=>{
        tmpProfileInfo.value.preferredLocationId = id;
    }
    const setTmpMbti = (val)=>{
        tmpProfileInfo.value.mbti = val;
    }
    const setTmpNickName = (name)=>{
        tmpProfileInfo.value.nickName = name;
    }
    const setTmpNickbio = (bio)=>{
        tmpProfileInfo.value.bio = bio;
    }
    const setTmplink1 = (link)=>{
        tmpProfileInfo.value.link1 = link;
    }
    const setTmplink2 = (link)=>{
        tmpProfileInfo.value.link2 = link;
    }
    const setProfileInfo = (info) => {
        profileInfo.value = info;
        tmpProfileInfo.value = info;
        tmpProfileInfo.value.favoriteFields = new Set(tmpProfileInfo.value.favoriteFields)
        tmpProfileInfo.value.favoriteTechs = new Set(tmpProfileInfo.value.favoriteTechs)
    };
    /**
     * 파일이 수정 되었는지를 체킹하는 isFieldChanged를 수정해준다.
     * @param val : boolean
     */
    const setIsFieldChanged = (val) =>{
        isFieldChanged.value = val;
    }
    return {
        isFieldContain,isTechContain,
        addField,addTech
        ,getIsMiddleChanged,getIsSmallChanged
        ,getProfileInfo,getTmpAvatar,getTmpProfileInfo,getTmpNickName,getIsFieldChanged
        ,setIsFieldChanged,setTmpNickName,setTmpNickbio, setIsSmallChanged,setIsMiddleChanged
        ,setTmplink1,setTmplink2,setTmpLocation,setTmpMbti,setProfileInfo
        ,setTmpAvatar
    }
})