<template>
	<div class="wrapper">

		<!--header部分-->
		<header>
			<p>用户登录</p>
		</header>

		<!--登陆表单部分-->
		<ul class="form-box">
			<li>
				<div class="title">
					手机号码：
				</div>
				<div class="content">
					<input type="text" v-model="userId" placeholder="手机号码">
				</div>
			</li>
			<li>
				<div class="title">
					密码：
				</div>
				<div class="content">
					<input type="password" v-model="password" placeholder="密码">
				</div>
			</li>
		</ul>

		<div class="button-login">
			<button @click="login">登录</button>
		</div>

		<div class="button-register">
			<button @click="register">去注册</button>
		</div>
		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>

</template>

<script>
	import {
		ref
	} from 'vue';
	import axios from 'axios';
	import qs from 'qs';
	import {
		useRouter
	} from 'vue-router';
	import Footer from '../components/Footer.vue';

	export default {
		name: 'Login',
		components: {
			Footer
		},
		setup() {
			const router = useRouter();
			const userId = ref('');
			const password = ref('');

			const login = () => {
				if (userId.value == '') {
					alert('手机号码不能为空！');
					return;
				}
				if (password.value == '') {
					alert('密码不能为空！');
					return;
				}

				// 登录请求
				axios.post('UserController/getUserByIdByPass', qs.stringify({
					userId: userId.value,
					password: password.value
				})).then(response => {
					const user = response.data;
					if (user == null) {
						alert('用户名或密码不正确！');
					} else {
						// sessionstorage有容量限制，为了防止数据溢出，所以不将userImg数据放入session中
						user.userImg = '';

						// 设置用户信息到 sessionStorage
						sessionStorage.setItem('user', JSON.stringify(user));
						router.go(-1); // 页面回退
					}
				}).catch(error => {
					console.error(error);
				});
			};
			const register = () => {
				router.push({
					path: '/register'
				});
			};

			return {
				userId,
				password,
				login,
				register
			};
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

	/****************** 用户表单部分 ******************/
	.wrapper .form-box {
		width: 100%;
		margin-top: 12vw;
	}

	.wrapper .form-box li {
		box-sizing: border-box;
		padding: 4vw 3vw 0 3vw;
		/*上 右 下 左*/

		display: flex;
		align-items: center;
	}

	.wrapper .form-box li .title {
		flex: 0 0 18vw;
		/*标题部分不缩小&放大，固定18vw*/
		font-size: 3vw;
		font-weight: 700;
		color: #666;
	}

	.wrapper .form-box li .content {
		flex: 1;
	}

	.wrapper .form-box li input {
		border: none;
		outline: none;
		/*去掉外轮廓线*/
		width: 100%;
		height: 4vw;
		font-size: 3vw;
	}

	.wrapper .button-login {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw 3vw 0 3vw;
	}

	.wrapper .button-login button {
		width: 100%;
		height: 10vw;
		font-size: 3.8vw;
		font-weight: 700;
		color: #fff;
		background-color: #38CA73;
		border-radius: 4px;

		border: none;
		outline: none;
	}

	.wrapper .button-register {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw 3vw 0 3vw;
	}

	.wrapper .button-register button {
		width: 100%;
		height: 10vw;
		font-size: 3.8vw;
		font-weight: 700;
		color: #666;
		background-color: #EEE;
		border-radius: 4px;

		border: none;
		outline: none;
		border: solid 1px #DDD;
	}

	/****************** 底部菜单部分 ******************/
	.wrapper .footer {
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

	.wrapper .footer li {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;

		color: #999;
		user-select: none;
		cursor: pointer;
	}

	.wrapper .footer li p {
		font-size: 2.8vw;
	}

	.wrapper .footer li i {
		font-size: 5vw;
	}
</style>