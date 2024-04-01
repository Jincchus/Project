//createStudy_StudyCategories
import { ref } from "vue";

export const useStudyCategories = () => {
  const cates = ref({});
  const major = ref({ curMajor: "", majors: [] });
  const middle = ref({ curMiddle: "", middles: [] });
  const small = ref({ curSmall: "", smalls: [] });
  const selectedMiddles = ref(new Set([]));
  const selectedSmalls = ref(new Set([]));
  return {
    cates,
    major,
    middle,
    small,
    selectedMiddles,
    selectedSmalls,
  };
};
