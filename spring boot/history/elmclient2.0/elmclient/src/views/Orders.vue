<template>
	<div class="wrapper">
		<!--header部分-->
		<header>
			<p>确认订单</p>
		</header>

		<!--订单信息部分-->
		<div class="order-info">
			<h5 class="h5">订单配送至</h5>
			<div class="order-info-address" @click="toUserAddress">
				<p>{{deliveryaddress!=null?deliveryaddress.address:'请选择送货地址'}}</p>
				<i class="fa fa-angle-right"></i>
			</div>
			<p v-if="deliveryaddress">{{deliveryaddress.contactName}}{{deliveryaddress.contactSex}} {{deliveryaddress.contactTel}}</p >
			<p v-if="!deliveryaddress">{{user.userName}}{{formatSex(user.userSex)}} {{user.userId}}</p>
		</div>

		<h3>{{business.businessName}}</h3>

		<!--订单明细部分-->
		<ul class="order-detailed">
			<li v-for="item in cartArr">
				<div class="order-detailed-left">
					<img :src="item.food.foodImg">
					<p>{{item.food.foodName}} x {{item.quantity}}</p>
				</div>
				<p>&#165;{{item.food.foodPrice*item.quantity}}</p>
			</li>
		</ul>
		<div class="order-deliveryfee">
			<p>配送费</p>
			<p>&#165;{{business.deliveryPrice}}</p>
		</div>
		
		
		
        <div class="chose">
			<div class="title">
					是否选择使用积分？
				</div>
        	<div class="content" style="font-size: 3vw;">
        		<input type="radio" v-model="ifused" value="1" style="width: 6vw;height: 3.2vw;">是
				<input type="radio" v-model="ifused" value="0" style="width: 6vw;height: 3.2vw;">否
        	</div>
        </div>
		
		
		
		<!--合计部分-->
		<div class="total">
			<div class="total-left">
				&#165;{{realPrice}}
			</div>
			<div class="total-right" @click="toPayment">
				去支付
			</div>
		</div>
	</div>
</template>

<script>
	import {
		ref,
		onMounted,
		computed,
		reactive,
		watch
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	import axios from 'axios';
	import qs from 'qs';
	import {
		setLocalStorage,
		getLocalStorage
	} from '../common.js';

	export default {
		name: 'Orders',
		setup() {
			const route = useRoute();
			const router = useRouter();
			const businessId = ref(null);
			//const business = ref({});
			const business = ref({});
			const user = ref(null);
			const cartArr = ref([]);
			const deliveryaddress = ref(null);

			// 获取会话存储中的用户数据
			user.value = JSON.parse(sessionStorage.getItem('user'));
			//businessId.value = route.query.businessId;
			// 获取本地存储中的交付地址
			// const storedDeliveryAddress = localStorage.getItem(user.value.userId);
			// deliveryaddress.value = storedDeliveryAddress ? JSON.parse(storedDeliveryAddress) : null;
			deliveryaddress.value = getLocalStorage(user.value.userId);

			onMounted(async () => {
				//const routeQuery = route.query;
				businessId.value = route.query.businessId;
				//user.value = await JSON.parse(sessionStorage.getItem('user'));
				//getSessionStorage();
				fetchBusinessInfo();
				fetchFoodInfo();
				getScore();
			});



			const ifused = ref(0);
			const score = ref(0);
			const usedScore = ref(0);
			const realPrice =ref(0);
			/*const getrealprice = () => {
				console.log(ifused.value);
				if (ifused.value == 1) {
				    usedScore.value = totalPrice.value > (score.value * 0.01) ? score.value : (totalPrice.value * 100);
				    let totall = totalPrice.value - usedScore.value * 0.01;
					console.log(totalPrice.value);
					console.log(totall);
					console.log(score.value);
					console.log(usedScore.value);
					console.log(ifused);
				    return totall;
				  } else {
				    usedScore.value = 0;
					console.log(ifused.value);
					console.log(totalPrice.value);
				    return totalPrice.value;
					
				  }
			};*/
			const getScore = () => {
				axios
					.post('/ScoreController/getTotalScore', qs.stringify({
						userId:user.value.userId
					}))
					.then(response => {
						score.value = response.data;
						//realPrice.value=getrealprice();
						if(score.value != null)
						{realPrice.value =  computed(() => {
							if(ifused.value == 1){
								usedScore.value = totalPrice.value > (score.value*0.01) ? score.value : (totalPrice.value*100);
								let totall = 0;
								totall = totalPrice.value - (usedScore.value*0.01);
							    console.log(totall);
								console.log(score.value);
								console.log(usedScore.value);
								console.log(totalPrice.value);
								return totall;
								
							}else{
								let totall = totalPrice.value;
								usedScore.value = 0;
								console.log(totall);
								console.log(score.value);
								console.log(usedScore.value);
								console.log(totalPrice.value);
								return totall;
							}
						})}else{
							realPrice.value = totalPrice.value;
						}
					})
					.catch(error => {
						console.error(error);
					});
			};
			
			/*const realPrice =  computed(() => {
				if(ifused.value == 1){
					usedScore.value = totalPrice.value > (score.value*0.01) ? score.value : (totalPrice.value*100);
					let totall = 0;
					totall = totalPrice.value - (usedScore.value*0.01);
				    console.log(totall);
					console.log(score.value);
					console.log(usedScore.value);
					console.log(totalPrice.value);
					return totall;
					
				}else{
					let totall = totalPrice.value;
					usedScore.value = 0;
					console.log(totall);
					console.log(score.value);
					console.log(usedScore.value);
					console.log(totalPrice.value);
					return totall;
				}
			});*/
			
			// const getSessionStorage = () => {
			// 	const storedUser = sessionStorage.getItem('user');
			// 	user.value = storedUser ? JSON.parse(storedUser) : null;
			// };

			//查询当前商家
			const fetchBusinessInfo = () => {
				axios
					.post('BusinessController/getBusinessById', qs.stringify({
						businessId: businessId.value
					}))
					.then(response => {
						business.value = response.data;
					})
					.catch(error => {
						console.error(error);
					});
			};
			//查询当前用户在购物车中的当前商家食品列表
			const fetchFoodInfo = () => {
				axios
					.post('CartController/listCart', qs.stringify({
						userId: user.value.userId,
						businessId: businessId.value
					}))
					.then(response => {
						cartArr.value = response.data;
					})
					.catch(error => {
						console.error(error);
					});
			};

			const totalPrice = computed(() => {
				let total = 0;
				for (let item of cartArr.value) {
					total += item.food.foodPrice * item.quantity;
				}
				total += business.value.deliveryPrice;
				return total;
			});

			const formatSex = (value) => {
				return value == 1 ? '先生' : '女士';
			};

			const toUserAddress = () => {
				router.push({
					path: '/userAddress',
					query: {
						businessId: business.value.businessId
					}
				});
			};

			const toPayment = async () => {
				if (deliveryaddress.value == null) {
					alert('请选择送货地址！');
					return;
				}
				const currentRealPrice = realPrice.value;
				axios
					.post('OrdersController/createOrders', qs.stringify({
						userId: user.value.userId,
						businessId: businessId.value,
						daId: deliveryaddress.value.daId,
						orderTotal: currentRealPrice.value
					}))
					.then(response => {
						let orderId = response.data;
						if (orderId > 0) {
							router.push({
								path: '/payment',
								query: {
									orderId: orderId,
									usedScore: usedScore.value,
		                            userId:user.value.userId
								}
							});
									
						} else {
							alert('创建订单失败！');
						}
					})
					.catch(error => {
						console.error(error);
					});
			};

            watch(realPrice, (newRealPrice) => {
              // 在 realPrice 发生变化时执行相应操作
              console.log('realPrice 变化:', newRealPrice);
              // 在这里可以执行其他操作，例如发起支付请求
            });
			return {
				businessId,
				business,
				user,
				cartArr,
				totalPrice,
				fetchBusinessInfo,
				fetchFoodInfo,
				formatSex,
				deliveryaddress,
				toUserAddress,
				toPayment
				
				
				
				,ifused,
				score,
				usedScore,
				realPrice,
				getScore,
				//getrealprice
			}
		}
	}
</script>

<style scoped>
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

	/****************** 订单信息部分 ******************/
	.wrapper .order-info {
		color: white;
		width: 100%;
		margin-top: 12vw;
		background-color: #0097FF;
		box-sizing: border-box;
		padding: 2vw;
	}

	.wrapper .order-info h5 {
		font-size: 3vw;
		font-weight: 300;
	}

	.wrapper .order-info .order-info-address {
		width: 100%;
		font-weight: 700;

		display: flex;
		justify-content: space-between;
		align-items: center;
		user-select: none;
		cursor: pointer;
		margin: 1vw 0;
		/*中间一行的上下 左右间距*/
	}

	.wrapper .order-info .order-info-address p {
		width: 90%;
		/*避免地址过长时换行太靠后*/
		font-size: 5vw;
	}

	.wrapper .order-info .order-info-address i {
		font-size: 6vw;
	}

	.wrapper .order-info p {
		font-size: 3vw;
	}

	.wrapper h3 {
		box-sizing: border-box;
		padding: 3vw;
		font-size: 4vw;
		color: #666;
		border-bottom: solid 1px #DDD;
	}

	/****************** 订单明细部分 ******************/
	.wrapper .order-detailed {
		width: 100%;
	}

	.wrapper .order-detailed li {
		width: 100%;
		height: 16vw;
		box-sizing: border-box;
		padding: 3vw;
		color: #666;

		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.wrapper .order-detailed li .order-detailed-left {
		display: flex;
		align-items: center;
	}

	.wrapper .order-detailed li .order-detailed-left img {
		width: 10vw;
		height: 10vw;
	}

	.wrapper .order-detailed li .order-detailed-left p {
		font-size: 3.5vw;
		margin-left: 3vw;
	}

	.wrapper .order-detailed li p {
		font-size: 3.5vw;
	}

	.wrapper .order-deliveryfee {
		width: 100%;
		height: 16vw;
		box-sizing: border-box;
		padding: 3vw;
		color: #666;

		display: flex;
		justify-content: space-between;
		align-items: center;
		font-size: 3.5vw;
	}

	/****************** 订单合计部分 ******************/
	.wrapper .total {
		width: 100%;
		height: 14vw;

		position: fixed;
		/*固定到底部*/
		left: 0;
		bottom: 0;

		display: flex;

	}

	.wrapper .total .total-left {
		flex: 2;
		background-color: #505051;
		color: #fff;
		font-size: 4.5vw;
		font-weight: 700;
		user-select: none;

		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .total .total-right {
		flex: 1;
		background-color: #38CA73;
		color: #fff;
		font-size: 4.5vw;
		font-weight: 700;
		user-select: none;
		cursor: pointer;

		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	/***新增***/
	.wrapper .chose {
		width: 100%;
		margin-top: 0vw;
		box-sizing: border-box;
		padding: 4vw 3vw 0 3vw;
		display: flex;
		align-items: center;
	}
	
	
	.wrapper .chose .title {
		flex: 0 0 71vw;
		/*标题部分不缩小&放大，固定18vw*/
		font-size: 3.5vw;
		color: #666;
	}
	
	.wrapper .chose .content {
		flex: 1;
	}
	
	.wrapper .chose input {
		border: none;
		outline: none;
		/*去掉外轮廓线*/
		width: 100%;
		height: 4vw;
		font-size: 3vw;
		}
</style>