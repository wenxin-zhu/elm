<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<p>商家信息</p>
		</header>

		<!-- 商家logo部分 -->
		<div class="business-logo">
			<img :src="business.businessImg">
		</div>

		<!-- 商家信息部分 -->
		<div class="business-info">
			<h1>{{business.businessName}}</h1>
			<p>&#165;{{business.starPrice}}起送 &#165;{{business.deliveryPrice}}配送</p>
			<p>{{business.businessExplain}}</p>
		</div>

		<!-- 食品列表部分"" -->
		<ul class="food">
			<li v-for="(item,index) in foodArr">
				<div class="food-left">
					<img :src="item.foodImg">
					<div class="food-left-info">
						<h3>{{item.foodName}}</h3>
						<p>{{item.foodExplain}}</p>
						<p>&#165;{{item.foodPrice}}</p>
					</div>
				</div>
				<div class="food-right">
					<div>
						<i class="fa fa-minus-circle" @click="minus(index)" v-show="item.quantity!=0"></i>
					</div>
					<p><span v-show="item.quantity!=0">{{item.quantity}}</span></p>
					<div>
						<i class="fa fa-plus-circle" @click="add(index)"></i>
					</div>
				</div>
			</li>
		</ul>

		<!-- 购物车部分 -->
		<div class="cart">
			<div class="cart-left"><!-- 分成左右两块 -->
				<div class="cart-left-icon"
					:style="totalQuantity==0?'background-color:#505051;':'background-color:#3190E8;'">
					<i class="fa fa-shopping-cart"></i>
					<div class="cart-left-icon-quantity" v-show="totalQuantity!=0">{{totalQuantity}}</div>
				</div>
				<div class="cart-left-info">
					<p>&#165;{{totalPrice}}</p>
					<p>另需配送费{{business.deliveryPrice}}元</p>
				</div>
			</div>

			<div class="cart-right">
				<!-- 不够起送费 -->
				<!--
				        <div class="cart-right-item">
				            &#165;15起送
				        </div>
				        -->
				<!-- 达到起送费 -->
				<div class="cart-right-item" v-show="totalSettle<business.starPrice"
					style="background-color: #535356;cursor: default;">
					&#165;{{business.starPrice}}起送
				</div>
				<div class="cart-right-item" @click="toOrder" v-show="totalSettle>=business.starPrice">
					去结算
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import {
		ref,
		onMounted,
		watch,
		computed
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	import axios from 'axios';
	import qs from 'qs';

	export default {
		name: 'BusinessInfo',
		setup() {
			const route = useRoute();
			const router = useRouter();
			const businessId = ref(null);
			const business = ref({});
			const foodArr = ref([]);
			const user = ref(null);
			const cartArr = ref([]);
			const deliveryPrice = ref(0);
			//向服务器请求商家信息，将响应数据存储在business中
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
			//向服务器请求食品信息，将响应数据存储在foodArr中
			const fetchFoodInfo = () => {
				axios
					.post('FoodController/listFoodByBusinessId', qs.stringify({
						businessId: businessId.value
					}))
					.then(response => {
						foodArr.value = response.data;
						//let foodArr = response.data;
						for (let i = 0; i < foodArr.value.length; i++) {
							foodArr.value[i].quantity = 0;
						}
						if (user.value != null) {
							listCart();
						}
					})
					.catch(error => {
						console.error(error);
					});
			};

			const getSessionStorage = () => {
				const storedUser = sessionStorage.getItem('user');
				user.value = storedUser ? JSON.parse(storedUser) : null;
			};
			//增加购物车中某食品数量
			const add = (index) => {
				if (user.value === null) {
					router.push({
						path: '/login'
					});
					return;
				}
				if (foodArr.value[index].quantity === 0) {
					saveCart(index);
				} else {
					updateCart(index, 1);
				}
			};
			//减少购物车中某食品数量或将其从购物车删除
			const minus = (index) => {
				if (user.value === null) {
					router.push({
						path: '/login'
					});
					return;
				}
				if (foodArr.value[index].quantity > 1) {
					updateCart(index, -1);
				} else {
					removeCart(index);
				}
			};
			//列出购物车中的食品及数量，将购物车中的食品数量与食品列表匹配
			const listCart = () => {
				axios
					.post('CartController/listCart', qs.stringify({
						businessId: businessId.value,
						userId: user.value.userId,
						//foodId: foodArr.value[index].foodId
					}))
					.then(response => {
						cartArr.value = response.data;
						//遍历所有食品列表，设置购物车中食品数量
						for (let foodItem of foodArr.value) {
							foodItem.quantity = 0;
							for (let cartItem of cartArr.value) {
								if (cartItem.foodId == foodItem.foodId) {
									foodItem.quantity = cartItem.quantity;
								}
							}
						}
						foodArr.value.sort(); //确保vue监听到了它的变化
					})
					.catch(error => {
						console.error(error);
					});
			};
			//将食品添加到购物车
			const saveCart = (index) => {
				axios
					.post('CartController/saveCart', qs.stringify({
						businessId: businessId.value,
						userId: user.value.userId,
						foodId: foodArr.value[index].foodId
					}))
					.then(response => {
						if (response.data === 1) {
							foodArr.value[index].quantity = 1;
							const arr = [...foodArr.value]; // 将响应式变量转换为数组
							arr.sort(); // 对数组进行排序
							foodArr.value = arr; // 将排序后的数组赋值回响应式变量
						} else {
							alert('向购物车中添加食品失败');
						}
						//console.log('saveadd');
						//console.log(response.data);
						//console.log('saveadd');
					})
					.catch(error => {
						console.error(error);
					});
			};
			//更新购物车中的食品数量，+1或-1
			const updateCart = (index, num) => {
				axios
					.post('CartController/updateCart', qs.stringify({
						businessId: businessId.value,
						userId: user.value.userId,
						foodId: foodArr.value[index].foodId,
						quantity: foodArr.value[index].quantity + num
					}))
					.then(response => {
						if (response.data === 1) {
							foodArr.value[index].quantity += num;
							const arr = [...foodArr.value]; // 将响应式变量转换为数组
							arr.sort(); // 对数组进行排序
							foodArr.value = arr; // 将排序后的数组赋值回响应式变量
						} else {
							alert('向购物车中更新食品失败!');
							console.log(response.data);
						}
						/*console.log('update');
						console.log(response.data);
						console.log('update');*/
					})
					.catch(error => {
						console.error(error);
					});
			};
			//从购物车中删除食品
			const removeCart = (index) => {
				axios
					.post('CartController/removeCart', qs.stringify({
						businessId: businessId.value,
						userId: user.value.userId,
						foodId: foodArr.value[index].foodId,
					}))
					.then(response => {
						if (response.data === 1) {
							foodArr.value[index].quantity = 0;
							const arr = [...foodArr.value]; // 将响应式变量转换为数组
							arr.sort(); // 对数组进行排序
							foodArr.value = arr; // 将排序后的数组赋值回响应式变量
						} else {
							alert('向购物车中删除食品失败!');
						}
						/*console.log('remove');
						console.log(response.data);
						console.log('remove');*/
					})
					.catch(error => {
						console.error(error);
					});
			};
			//计算购物车中的食品总价格，返回计算结果
			const totalPrice = computed(() => {
				let total = 0;
				for (let item of foodArr.value) {
					total += item.foodPrice * item.quantity;
				}
				let totall = parseFloat(total).toFixed(2);
				return totall;
			});
			//计算购物车中的食品总数量，返回计算结果
			const totalQuantity = computed(() => {
				let quantity = 0;
				for (let item of foodArr.value) {
					quantity += item.quantity;
				}
				return quantity;
			});
			//计算购物车中食品总价格与配送费相加后的总价格，返回计算结果
			const totalSettle = computed(() => {
				return totalPrice.value + deliveryPrice.value;
			});
			//跳转到订单页面，并传递参数businessId
			const toOrder = () => {
				router.push({
					path: '/orders',
					query: {
						businessId: business.value.businessId
					}
				});
			};
			//监视路由查询businessId的变化并及时修改businessId、调用函数
			watch(
				() => route.query.businessId,
				(newBusinessId) => {
					businessId.value = newBusinessId;
					fetchBusinessInfo();
					fetchFoodInfo();
					getSessionStorage();
				}, {
					immediate: true
				}
				// 立即执行回调函数，以便初始化 businessId
			);
			//组件被挂载到DOM后如果用户已登录，立即列出购物车中的食品及数量
			onMounted(() => {
				if (user.value !== null) {
					listCart(0);
				}
				sessionStorage.setItem('redirectPath','/businessInfo');
			});
			return {
				businessId,
				business,
				foodArr,
				user,
				add,
				minus,
				totalQuantity,
				totalPrice,
				totalSettle,
				toOrder
			};
		}
	};
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

	/****************** 商家logo部分 ******************/
	.wrapper .business-logo {
		width: 100%;
		height: 35vw;
		/*使用上外边距避开header部分*/
		margin-top: 12vw;

		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .business-logo img {
		width: 40vw;
		height: 30vw;
		border-radius: 5px;
		/*圆角图片*/
	}

	/****************** 商家信息部分 ******************/
	.wrapper .business-info {
		width: 100%;
		height: 20vw;

		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	.wrapper .business-info h1 {
		font-size: 5vw;
	}

	.wrapper .business-info p {
		font-size: 3vw;
		color: #666;
		margin-top: 1vw;
	}

	/****************** 食品列表部分 ******************/
	.wrapper .food {
		width: 100%;
		padding-bottom: 14vw;
		/*底外边距*/
	}

	.wrapper .food li {
		width: 100%;
		box-sizing: border-box;
		padding: 2.5vw;
		/*内边距*/
		user-select: none;
		/*文字不可选中*/

		display: flex;
		justify-content: space-between;
		/*紧靠两边对齐*/
		align-items: center;
	}

	.wrapper .food li .food-left {
		display: flex;
		align-items: center;
	}

	.wrapper .food li .food-left img {
		width: 20vw;
		height: 20vw;
	}

	.wrapper .food li .food-left .food-left-info {
		margin-left: 3vw;
	}

	.wrapper .food li .food-left .food-left-info h3 {
		font-size: 3.8vw;
		color: #555;
	}

	.wrapper .food li .food-left .food-left-info p {
		font-size: 3vw;
		color: #888;
		margin-top: 2vw;
	}

	.wrapper .food li .food-right {
		width: 16vw;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.wrapper .food li .food-right .fa-minus-circle {
		font-size: 5.5vw;
		color: #999;
		cursor: pointer;
	}

	.wrapper .food li .food-right p {
		font-size: 3.6vw;
		color: #333;
	}

	.wrapper .food li .food-right .fa-plus-circle {
		font-size: 5.5vw;
		color: #0097EF;
		cursor: pointer;
	}

	/****************** 购物车部分 ******************/
	.wrapper .cart {
		width: 100%;
		height: 14vw;

		position: fixed;
		left: 0;
		bottom: 0;
		display: flex;
	}

	.wrapper .cart .cart-left {
		flex: 2;
		background-color: #505051;
		display: flex;
		/*让购物车和文字水平排列*/
	}

	.wrapper .cart .cart-left .cart-left-icon {
		/*购物车图标，需要有边框*/
		width: 16vw;
		height: 16vw;
		box-sizing: border-box;
		/*设置为边框盒子*/
		border: solid 1.6vw #444;
		/*灰色边框*/
		border-radius: 8vw;
		/*圆角*/
		background-color: #3190E8;
		font-size: 7vw;
		/*购物车图标大小及颜色*/
		color: #fff;

		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: -4vw;
		/*购物车图标凸出*/
		margin-left: 3vw;

		position: relative;
		/*作为红点的父容器，相对定位*/
	}

	.wrapper .cart .cart-left .cart-left-icon-quantity {
		width: 5vw;
		height: 5vw;
		border-radius: 2.5vw;
		background-color: red;
		color: #fff;
		font-size: 3.6vw;

		display: flex;
		justify-content: center;
		/*圆点内数字居中*/
		align-items: center;

		position: absolute;
		/*绝对定位*/
		right: -1.5vw;
		/*靠右上方*/
		top: -1.5vw;
	}

	.wrapper .cart .cart-left .cart-left-info p:first-child {
		/*css3新值伪类选择器，可选择匹配的父容器中第一个和最后一个p类型子元素*/
		font-size: 4.5vw;
		color: #fff;
		margin-top: 1vw;
	}

	.wrapper .cart .cart-left .cart-left-info p:last-child {
		font-size: 2.8vw;
		color: #AAA;
	}

	.wrapper .cart .cart-right {
		flex: 1;
		/*2：1*/
	}

	/*达到起送费时的样式*/

	.wrapper .cart .cart-right .cart-right-item {
		width: 100%;
		height: 100%;
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

	/*不够起送费时的样式（只有背景色和鼠标样式（不可点）的区别）*/
	/*
	.wrapper .cart .cart-right .cart-right-item{
	    width: 100%;
	    height: 100%;
	    background-color: #535356;
	    color: #fff;
		
	    font-size: 4.5vw;
		font-weight: 700;
		user-select: none;
		
		display: flex;
		justify-content: center;
		align-items: center;
	}
	*/
</style>