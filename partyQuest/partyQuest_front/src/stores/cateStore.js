import {defineStore} from "pinia";
import {ref} from "vue";

export const useCateStore = defineStore('cateStore',()=>{
    const globalCate = ref({})
    const setCate = (data)=>{
        globalCate.value = data;
    }
    const getCate = ()=> globalCate.value
    return {globalCate,setCate,getCate}
})