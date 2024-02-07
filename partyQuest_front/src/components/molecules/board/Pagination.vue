<!-- Pagination.vue -->
<template>
  <nav aria-label="Page navigation example">
    <ul class="pagination">
      <li class="page-item" :class="{ 'disabled': currentPage === 1 }">
        <a class="page-link" href="#" aria-label="Previous" @click.prevent="prevPage">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </li>
      <li class="page-item" v-for="page in getPageArray()" :key="page">
        <a class="page-link" href="#" @click.prevent="selectPage(page)">{{ page }}</a>
      </li>
      <li class="page-item" :class="{ 'disabled': !next }">
        <a class="page-link" href="#" aria-label="Next" @click.prevent="nextPage">
          <span aria-hidden="true">&raquo;</span>
        </a>
      </li>
    </ul>
  </nav>
</template>

<script setup>
const props = defineProps(['currentPage', 'getPageArray', 'prevPage', 'nextPage', 'selectPage', 'next']);
</script>