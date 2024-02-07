import {defineStore} from "pinia";
import {ref} from "vue";
import axios from "axios";

export const useMessageStore = defineStore( 'messageStore',() =>{
    const unReadMessageSize = ref();

    const findUnreadMessage = async (memberId) => {
        const BASE_URL = "http://localhost:8080";
        const url = `${BASE_URL}/api/member/unreadMessage/${memberId}`;
        try {
            const response = await axios.get(url);
            unReadMessageSize.value = response.data;
        } catch (error) {
            console.error("error: " + JSON.stringify(error));
        }
    };
    return {
        unReadMessageSize,
        findUnreadMessage,
    };
    });