<script setup>
import AccordionItem from "@/components/molecules/board/AccordionItem.vue";
import Tab from "@/components/molecules/board/Tab.vue";
import Pagination from "@/components/molecules/board/Pagination.vue";
import {useAuthStore} from "@/stores/authStore";
import {ref,onMounted} from "vue";
import {getValidatedAxios} from "@/utils/globalAxios";
import {CSCONST} from "@/constants/APIconst";
import Img from "@/components/molecules/common/Img.vue";
const BASE_URL = "/api/cs";
const { user,accessToken } = useAuthStore();
const myAxios = getValidatedAxios(accessToken);

const boardListVar = ref({
  heading: "heading",
  collapse: "collapse"
})
const boards = ref([]);
const pg = ref(1);
const size = ref(10);
const total = ref('');
const boardList = ref([]);
const cateId = CSCONST.NOTICE;

const fetchData = async () => {
  try {
    const response = await myAxios.get(`${BASE_URL}/notice`,{
      params: {
        cateId: cateId,
        pg : pg.value,
        size : size.value,
      },
    });

    boards.value = response.data;
    if (response.data.pg !== undefined) {
      pg.value = response.data.pg;
    }
    if (response.data.size !== undefined) {
      size.value = response.data.size;
    }
    total.value = response.data.totalElements;
    boardList.value = boards.value.content;
  } catch (err) {
  }
};
onMounted(async () => {
  await fetchData();

});

const prevPage = () => {
  if (pg.value > 1) {
    pg.value--;
    fetchData();
  }
};

const nextPage = () => {
  pg.value++;
  fetchData();
};

const selectPage =  (pageNumber) => {
  pg.value = pageNumber;
  fetchData();
};
const getPageArray = () => {
  const countInPages = Math.ceil(total.value / size.value);
  const pageArray = Array.from({ length: countInPages }, (_, index) => index + 1);
  return pageArray;
};


</script>
<template>
  <main class>
    <section>
      <div class="container">
        <Tab></Tab>
        <div class="accordion accordion-flush" id="accordionFlushExample" >
            <AccordionItem
                v-for="(board) in boardList"
                :key="board.boardId"
                :headingId="boardListVar.heading+board.boardId "
                :collapseId="boardListVar.collapse+board.boardId"
                :title="board.title"
                :date="board.rdate"
                :menu="board.menu"
                :content="board.content"
                :boardThumb= "board.boardThumb"
            />
          </div>
          <div class="page">
            <Pagination
                :currentPage="pg"
                :getPageArray="getPageArray"
                :prevPage="prevPage"
                :nextPage="nextPage"
                :selectPage="selectPage"
                :next="next"
            />
        </div>
      </div>
    </section>
  </main>
</template>
<style scoped>
.container {
  padding-top: 100px;
  padding-bottom: 100px;
}
.accordion{
  padding-top:30px;
}
.page{
  margin-top: 100px;
  display: flex;
  justify-content: center;
}
</style>
