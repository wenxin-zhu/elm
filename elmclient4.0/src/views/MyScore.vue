<template>
	<div class="wrapper">

		<!--header部分-->
		<header>
			<p>我的积分</p>
		</header>

		<!-- 订单列表部分 -->
		<h5>积分规则：每消费1人民币积累1分，每100分可抵消1元钱！积分有30天的使用期限，过期自动扣除！<p>请注意：每天00：00进行积分更新哦~</p>
		</h5>
		<div class="info">
			<p>
				当前积分：

			</p>

			<p>{{score}}
				<i class="fa fa-caret-down" @click="detailetShow"></i>
			</p>


		</div>
		<div class="listscore" v-show="isShowDetailet" v-if="scorelist">
			<li v-for="item of scorelist">
				<p>{{item.scoreCount}}</p>
				<p>{{item.leftTime}}天后逾期！</p>
			</li>
		</div>

		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
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
		name: 'MyScore',
		components: {
			Footer,
		},
		setup() {
			const route = useRoute();
			const router = useRouter();
			const isShowDetailet = ref(false);
			const score = ref(0);
			const userId = ref();
			const scorelist = ref([]);
			const user = ref(getSessionStorage('user'));
			//通过发送请求并传递userId，获取积分数量
			const getScore = () => {
				axios
					.post('/ScoreController/getTotalScore', qs.stringify({
						userId: user.value.userId
					}))
					.then(response => {
						score.value = response.data;
					})
					.catch(error => {
						console.error(error);
					});
			};

			//切换ref变量isShowDetailet的值，控制页面上元素或组件的显示及隐藏
			const detailetShow = () => {
				isShowDetailet.value = !isShowDetailet.value;
			}
			//获取积分列表
			const getlist = () => {
				axios.
				post('/ScoreController/listScoreDetial', qs.stringify({
						userId: user.value.userId
					}))
					.then(response => {
						scorelist.value = response.data;
					})
					.catch(error => {
						console.error();
					})
			}
			onMounted(() => {
				isShowDetailet.value = false;
				userId.value = route.query.userId;
				getScore();
				getlist();
			});
			//在组件即将被卸载之前，移除对浏览器的onpopstate事件的监听
			onBeforeUnmount(() => {
				window.onpopstate = null;
			});

			// 在线支付组件路由跳转时，禁止返回订单确认组件
			history.pushState(null, null, document.URL);
			window.onpopstate = () => {
				router.push({
					path: '/index'
				});
			};

			return {
				detailetShow,
				score,
				getScore,
				userId,
				scorelist,
				getlist,
				isShowDetailet
			}
		}

	};
</script>

<style>
	/****************** 总容器 ******************/
	.wrapper {
		width: 100%;
		height: 100%;
	}

	/****************** header部分 ******************/
	.wrapper header {
		width: 100%;
		height: 12vw;
		background-color: #0097FF;
		color: #fff;
		font-size: 4.8vw;

		position: fixed;
		left: 0;
		top: 0;
		z-index: 1000;

		display: flex;
		justify-content: center;
		align-items: center;
	}

	/****************** 列表部分 ******************/
	.wrapper h5 {
		margin-top: 12vw;
		box-sizing: border-box;
		padding: 2vw 4vw;
		font-size: 2.3vw;
		font-weight: 300;
		color: #999;
	}

	.info {
		box-sizing: border-box;
		padding: 0vw 4vw 1vw 4vw;
		font-size: 4.4vw;
		color: #666;

		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.wrapper .detailet {
		width: 100%;
	}

	.wrapper .listscore li {
		width: 100%;
		box-sizing: border-box;
		padding: 0vw 4vw;
		color: #666;
		font-size: 3.8vw;

		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	/****************** 底部菜单部分 ******************/
</style>