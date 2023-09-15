<template>
  <div class="animation">Hello</div>
</template>

<script>
	import {
		ref,
		onMounted,
		onBeforeUnmount
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	import axios from 'axios';
	import qs from 'qs';
	import Footer from '../components/Footer.vue';
	import {
		getSessionStorage
	} from '../common.js';
export default {
  name: 'AI',
}
</script>

<style>
@keyframes move {
  0% { transform: translateX(100px); }
}

.animation {
  animation: move 3s;
}
</style>