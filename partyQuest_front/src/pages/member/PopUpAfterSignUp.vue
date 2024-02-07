<template>
  <div class="my-container">
    <canvas id="myCanvas"></canvas>
    <div class="congrats-container">
      <SignUpPopUp :user-nick-name="props.userNickName"/>
    </div>
  </div>
</template>

<script setup>
import {onMounted} from "vue";
import confettiModule from "canvas-confetti";
import SignUpPopUp from "@/components/molecules/member/SignUpPopUp.vue";

const props = defineProps({
  userNickName: String
});
const myCanvas = document.getElementById('myCanvas');
const myConfetti = confettiModule.create(myCanvas, {
  resize: true,
  useWorker: true
});

const firework = ()=>{
  const duration = 7 * 1000;
  const animationEnd = Date.now() + duration;
  const defaults = { startVelocity: 30, spread: 360, ticks: 60, zIndex: 0 };

  function randomInRange(min, max) {
    return Math.random() * (max - min) + min;
  }

  const interval = setInterval(function() {
    const timeLeft = animationEnd - Date.now();

    if (timeLeft <= 0) {
      return clearInterval(interval);
    }

    const particleCount = 100 * (timeLeft / duration);
    // since particles fall down, start a bit higher than random
    myConfetti({ ...defaults, particleCount, origin: { x: randomInRange(0.1, 0.3), y: Math.random() - 0.2 } });
    myConfetti({ ...defaults, particleCount, origin: { x: randomInRange(0.7, 0.9), y: Math.random() - 0.2 } });
  }, 250);
}

onMounted(()=>{
  firework();
})

</script>

<style scoped>
.my-container{
  position: relative;
  display:flex;
  justify-content:center;
}
#myCanvas{
  position:absolute;
  width:100%;
  height:100%;
}
.congrats-container {
  height: 80vh;
  width: max-content;
  display: flex;
  justify-content: center;
  align-items: center;

}
</style>