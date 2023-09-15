<template>
	<div class="wrapper">
		<!--header部分-->
		<header>
			<p>商家列表</p>
		</header>
		<!--商家列表部分-->
		<ul class="business">
			<li v-for="item in businessArr" @click="toBusinessInfo(item.businessId)">
				<div class="business-img">
					<img :src="item.businessImg"> <!--绑定属性-->
					<div class="business-img-quantity" v-show="item.quantity > 0"> {{item.quantity}} </div>
					<!--大于0时显示数量-->
				</div>
				<div class="business-info">
					<h3>{{item.businessName}}</h3>
					<p>&#165; {{item.starPrice}}起送 | &#165;{{item.deliveryPrice}}配送</p>
					<p>{{item.businessExplain}}</p>
				</div>
			</li>
		</ul>

		<!--底部菜单部分-->
		<Footer></Footer>
	</div>
</template>

<script>
	import {
		ref,
		onMounted,
		reactive
	} from 'vue';
	import Footer from '../components/Footer.vue';
	import axios from 'axios';
	import {
		useRoute
	} from 'vue-router';
	import router from '../router/index.js';
	import qs from 'qs';

	export default {
		name: 'BusinessList',
		components: {
			Footer,
		},
		/* methods:{
		    toBusinessInfo(businessId){
			        router.push({path:'/businessInfo',query:{businessId:businessId}});
		    }
		 },*/
		setup() {
			const orderTypeId = ref(null);
			const businessArr = ref([]);
			const route = useRoute();
			const user = ref(null);

			onMounted(() => {
				const routeQuery = route.query;
				orderTypeId.value = parseInt(routeQuery.orderTypeId);
				//user.value = JSON.parse(localStorage.getItem('user'));
				fetchData();

			});
			const fetchData = async () => {
				//console.log(orderTypeId.value);
				user.value = JSON.parse(sessionStorage.getItem('user')) || null;
				try {
					//console.log(orderTypeId.value);
					const response = await axios.post('BusinessController/listBusinessByOrderTypeId', null, {
						params: {
							orderTypeId: orderTypeId.value
						}
					});
					/*const response = await axios.post('BusinessController/listBusinessByOrderTypeId', {
		    orderTypeId: orderTypeId.value
		  });
	       /*const response = await axios.post('BusinessController/listBusinessByOrderTypeId', /*{
	         orderTypeId: orderTypeId.value,
	       }*/
					/*orderTypeId.value);*/
					businessArr.value = response.data;
					if (user.value && user.value.userId != null) {
						listCart();
					}


				} catch (error) {
					console.error(error);
					console.log(error.message);
					console.log(error.stack);
					//console.log(orderTypeId.value);
				}
			};
			const toBusinessInfo = (businessId) => {
				router.push({
					path: '/businessInfo',
					query: {
						businessId: businessId
					}
				});
			};
			const listCart = () => {
				axios
					.post('CartController/listCart', qs.stringify({
						userId: user.value.userId,
					}))
					.then(response => {
						let cartArr = response.data;
						//遍历所有食品列表，设置购物车中食品数量
						for (let businessItem of businessArr.value) {
							businessItem.quantity = 0;
							for (let cartItem of cartArr) {
								if (cartItem.businessId == businessItem.businessId) {
									businessItem.quantity += cartItem.quantity;
								}
							}
						}
						businessArr.value.sort(); //确保vue监听到了它的变化
					})
					.catch(error => {
						console.error(error);
					});
			};
			/* onMounted(() => {
	   fetchData();
	 });
    /*const fetchData = async () => {
      try {
        const response = await axios.post('BusinessController/listBusinessByOrderTypeId', {
          orderTypeId: orderTypeId.value,
        });
        businessArr.value = response.data;
      } catch (error) {
        console.error(error);
      }
    };*/


			return {
				orderTypeId,
				businessArr,
				toBusinessInfo
			};
		},


	};
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

	/***商家列表部分***/
	.wrapper .business {
		width: 100%;
		margin-top: 12vw;
		padding-bottom: 14vw;
		/*margin-bottom: 14vw;*/
	}

	.wrapper .business li {
		width: 100%;
		box-sizing: border-box;
		padding: 2.5vw;
		border-bottom: solid 1px #DDD;
		user-select: none;
		cursor: pointer;

		display: flex;
		align-items: center;
	}

	.wrapper .business li .business-img {
		position: relative;
	}

	.wrapper .business li .business-img img {
		width: 20vw;
		height: 20vw;
	}

	.wrapper .business li .business-img .business-img-quantity {
		width: 5vw;
		height: 5vw;
		background-color: red;
		color: #fff;
		font-size: 3.6vw;
		border-radius: 2.5vw;

		display: flex;
		justify-content: center;
		align-items: center;

		position: absolute;
		right: -1.5vw;
		top: -1.5vw;
	}

	.wrapper .business li .business-info {
		margin-left: 3vw;
	}

	.wrapper .business li .business-info h3 {
		font-size: 3.8vw;
		color: #555;
	}

	.wrapper .business li .business-info p {
		font-size: 3vw;
		color: #888;
		margin-top: 2vw;
	}
</style>