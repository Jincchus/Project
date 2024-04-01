import { ref } from "vue";
import { getLocations } from "@/utils/fetch/studyFetch";

export const useStudyLocation = () => {
  // 온라인 스터디, 오프라인 스터디, 위치
  const newStudy_onOff = ref("");
  const newStudy_Location = ref("");

  // on off 선택 시작
  const selectedOption = ref("온/오프라인 선택");
  const selectedLocation = ref("지역 선택");
  const onOffList = ref(["온/오프라인 모두 가능", "온라인", "오프라인"]);
  const isOn = ref(false); // 온라인만 진행하는 경우:true, 오프라인을 포함하는 경우:false
  const isLocationsVisible = ref(true); // 온라인인 경우 location을 선택 할 수 없다.=>이 경우 location == '온라인'
  const locations = ref([]);

  const onOffToggleHandler = async (onOff) => {
    if (onOff.includes("오프라인")) {
      isOn.value = false;
      isLocationsVisible.value = true;
      selectedOption.value = "오프라인";
      if (onOff === "오프라인") {
        newStudy_onOff.value = "OFF";
      } else {
        newStudy_onOff.value = "ON_OFF";
        selectedOption.value = "온/오프라인 모두 가능";
      }
    } else {
      isOn.value = true;
      isLocationsVisible.value = false;
      newStudy_Location.value = 100; // 온라인 모집인 경우 위치는 무조건 온라인
      newStudy_onOff.value = "ON"; // api에 실을 데이터 할당
      selectedOption.value = "온라인";
    }
  };
  // on off 선택 end

  // locations 선택
  const onLocationChangeHandler = (curLocation) => {
    newStudy_Location.value = curLocation.id;
    selectedLocation.value = curLocation.locationName;
  };

  const fetchLocations = async () => {
    try {
      const res = await getLocations();
      locations.value = res;
    } catch (error) {
      console.error(error);
    }
  };
  // locations 선택 끝


  return {
    newStudy_onOff,
    newStudy_Location,
    onOffList,
    isOn,
    selectedOption,
    selectedLocation,
    fetchLocations,
    isLocationsVisible,
    locations,
    onOffToggleHandler,
    onLocationChangeHandler,

  };
};
