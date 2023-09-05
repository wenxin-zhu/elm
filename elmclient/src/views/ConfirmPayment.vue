<template>
	<div class="wrapper">
		<!--header部分-->
		<header>
			<p>支付成功</p>
		</header>
		<!--支付成功部分-->
		<div class="payment-success">
			<img src="../assets/payment.jpg" alt="支付成功">
		</div>
		<div class="points" @click="toMyScore">
			<p>查看积分详情</p>
		</div>
		<!--底部菜单部分-->
		<Footer></Footer>
	</div>
</template>

<script>
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	import Footer from '../components/Footer.vue';
	import {
		ref,
		onMounted
	} from 'vue';
	import axios from 'axios';
	import qs from 'qs';

	export default {
		name: 'Payment',
		components: {
			Footer
		},

		setup() {
			const route = useRoute();
			const router = useRouter();
			const usedScore = ref(null);
			const orderId = ref(null);
			const userId = ref();

			//路由导航到MyScore页面，并传递参数userId
			const toMyScore = () => {
				console.log(userId.value);
				router.push({
					path: '/myScore',
					query: {
						userId: userId.value,
					}
				});
			};

			//发送请求并传递参数的值用于支付订单，根据响应数据判断是否支付成功
			const payOrders = () => {
				axios
					.post('OrdersController/payOrders', qs.stringify({
						orderId: orderId.value,
						usedScore: usedScore.value
					}))
					.then(response => {
						const paymentResult = response.data; // 1 表示支付成功，0 表示支付失败
						if (paymentResult === 1) {
							console.log('支付成功');
						} else {
							console.log('支付失败');
						}
					})
					.catch(error => {
						console.error(error);
					});
			};

			// 在线支付组件路由跳转时，禁止返回订单确认组件
			history.pushState(null, null, document.URL);
			window.onpopstate = () => {
				router.push({
					path: '/index'
				});
			};

			//从当前路由的查询参数中获取需要的参数，并调用函数支付订单
			onMounted(() => {
				orderId.value = route.query.orderId;
				usedScore.value = route.query.usedScore;
				userId.value = route.query.userId;
				payOrders();
				//console.log(userId.value);
			});

			return {
				usedScore,
				orderId,
				toMyScore,
				payOrders,
				userId
			}
		}
	}
</script>

<style scoped>
	/***总容器***/
	.wrapper {
		width: 100%;
		height: 100%;
	}

	/****header部分***/
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
		/*层次提升*/

		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .payment-success {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .payment-success img {
		width: 60vw;
		height: 43vw;
		margin-top: 14vw;
	}

	.wrapper .points {
		width: 100%;
		text-align: center;
		display: flex;
		/* 使用flex布局 */
		align-items: center;
		/* 垂直居中 */
		justify-content: center;
	}

	.wrapper .points p {
		font-size: 4vw;
		color: #0097FF;
	}
</style>