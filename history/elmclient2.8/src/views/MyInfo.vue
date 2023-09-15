<template>
	<div class="wrapper">
		<!--header部分-->
		<header>
			<p>我的信息</p>
		</header>
		<div class="info">
			<img src="../assets/userImg/yhtx02.png">
			<div class="info-text">
				<div class="info-text-li">{{user.userName}}</div>
				<div class="info-text-li">{{user.userId}}</div>
			</div>
		</div>
		<div class="login" @click="logIn">登录</div>
		<div class="logout" @click="logOut">退出登录</div>
		<!-- 底部菜单部分 -->
		<Footer></Footer>
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
	import removeSessionStorage from '../common.js';
	import router from '../router';
	import Footer from '../components/Footer.vue';
	export default {
		name: 'MyInfo',
		components: {
			Footer,
		},
		setup() {
			const user = ref(null);
			const storedUser = sessionStorage.getItem('user');
			user.value = storedUser ? JSON.parse(storedUser) : null;
			if (user.value === null) {
				user.value = {
					userName: '未登录',
					userId: 'xxxxxxxxxxx'
				}
			}
			const logIn = () => {
				if (user.value.userName == '未登录') {
					router.push('/login');
				} else {
					alert('已登录');
				}
			};
			const logOut = () => {
				if (user.value.userName == '未登录') {
					alert('未登录');
				} else {
					sessionStorage.removeItem('user');
					location.reload();
				}

			};
			return {
				user,
				logIn,
				logOut
			};
		}
	}
</script>

<style>
	.wrapper .info img {
		height: 10%;
		width: 20%;
		margin-left: 15%;
	}

	.wrapper .info {
		margin-top: 24vw;
		display: flex;
	}

	.wrapper .info-text {
		margin-right: 15%;
	}

	.wrapper .info-text-li {
		margin-bottom: 8%;
	}

	.wrapper .login {
		margin-left: 10%;
		background-color: green;
		width: 80%;
		text-align: center;
		margin-top: 60%;
		height: 4%;
		border-radius: 10px;
		align-items: center;
		line-height: 20px;
		font-size: 5vw;
		padding-top: 3vw;
	}

	.wrapper .logout {
		margin-left: 10%;
		background-color: red;
		width: 80%;
		text-align: center;
		margin-top: 10%;
		height: 4%;
		border-radius: 10px;
		align-items: center;
		line-height: 20px;
		font-size: 5vw;
		padding-top: 3vw;
	}
</style>
