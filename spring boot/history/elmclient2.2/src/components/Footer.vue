<template>
	<ul class="footer">
		<li @click="toIndex">
			<i class="fa fa-home"></i>
			<p>首页</p>
		</li>
		<li @click="toMyScore">
			<i class="fa fa-compass" ></i>
			<p>积分</p>
		</li>
		<li @click="toOrderList">
			<i class="fa fa-file-text-o"></i>
			<p>订单</p>
		</li>
		<li>
			<i class="fa fa-user-o"></i>
			<p>我的</p>
		</li>
	</ul>
</template>

<script>
	import { useRouter } from 'vue-router';
	import {ref} from 'vue';
	import {getSessionStorage} from '../common.js';
	export default {
	  name: 'Footer',
	  
	  setup() {
		const router = useRouter(); 
		const user = ref(getSessionStorage('user'));
		const userId =ref(null);
		
		const toOrderList = () => {
		  sessionStorage.setItem('redirectPath', '/orderList');
		  router.push({ path: '/orderList'});
		};
		const toIndex = () => {
		  router.push({ path: '/index'});
		};
		const toMyScore = () => {
		sessionStorage.setItem('redirectPath','/myScore');
		router.push({ path: '/myScore'});
		};	
		return {
		  toOrderList,
		  toIndex,
		  toMyScore
		};
	  }
	 
	}
</script>

<style>
	.wrapper .footer{
		width: 100%;
		height: 14vw;
		border-top: solid 1px #DDD;
		background-color: #fff;
		
		position: fixed;
		left: 0;
		bottom: 0;
		
		display: flex;
		justify-content: space-around;
		align-items: center;
	}
	.wrapper .footer li{
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		
		color: #999;
		user-select: none;
		cursor: pointer;
	}
	.wrapper .footer li p{
		font-size: 2.8vw;
	}
	.wrapper .footer li i{
		font-size: 5vw;
	}
</style>