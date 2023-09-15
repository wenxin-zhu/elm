<template>
	<div class="wrapper">

		<!--header部分-->
		<header>
			<p>AI助手</p>
		</header>
		<div class="all">
			<!-- AI形象部分 -->
			<div class="picture" @click="toTalk"> <img src="../assets/AI.png"></div>
			<!--输入部分-->
			<div class="form-box">
				<input type="text" v-model="need" placeholder="输入你的饮食偏好或直接点击推荐试试吧">	
				<button @click="postneed">推荐</button>			
			</div>
			<div class="rukou">
				<p>点击搜索框上方机器人，解锁自己的私人AI对话哦~</p>
			</div>
			<div :class="css">
				<template v-if="answer.length>0">
					<p v-for="item in answer">{{item}}</p>
				</template>

			</div>
			<div class="ps"><p> 显示框变色即为提交成功，请耐心等待显示。</p></div>
		</div>

		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>


<script>
	import {
		reactive,
		ref,
		watch
	} from 'vue';
	import {
		useRoute,
		useRouter
	} from 'vue-router';
	import axios from 'axios';
	import qs from 'qs';
	import Footer from '../components/Footer.vue';
	export default {
		name: 'MyAI',
		components: {
			Footer
		},
		setup() {
			const route = useRoute();
			const router = useRouter();
			const need = ref(null);
			const realneed = ref(null);//中间量处理0
			const answer = ref([]);
			const css = reactive({
				bigbox: true,
				gray: true,
				blue: false
			})
			const postneed = () => {
				//console.log(need.value);
				if (need.value == null) {
					realneed.value = 0;
				} else {
					realneed.value = need.value;
				}//中间量加工
				axios
					.post('AIController/listOptionByAdj', qs.stringify({
						adj: realneed.value
					}))
					.then(response => {
						answer.value = response.data;
					})
					.catch(error => {
						console.error(error);
					});
					need.value = null;
					css.gray = !css.gray;
					css.blue = !css.blue;//动态绑定改变款式
				//console.log(answer.value);


			}
			//监听返回值变化
			watch(answer, (newanswer) => {
				
				console.log('realanswer 变化:', newanswer);
				
			});
			//跳转
			const toTalk = () =>{
				router.push({path: '/conversation'});
			}
			console.log(need.value);
			console.log(answer.value);
			return {
				need,
				answer,
				postneed,
				realneed,
				toTalk,
				css
			}
		}
	}
</script>

<style scoped>
	/****************** 总容器 ******************/
	.wrapper {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
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
	.wrapper .all {
		display: flex;
		flex-direction: column;
		justify-content: center;
	}

	.wrapper .all .picture {
		width: 80%;
		height: 12vw;
		margin: 12vw 10vw 0 10vw;
		box-sizing: border-box;
		display: flex;
		justify-content: center;
		align-items: center;

	}

	.wrapper .all .form-box {
		width: 100%;
		display: flex;
		justify-content: space-around;
		padding: 0 3vw;
		align-items: center;
	}

	.wrapper .all .form-box input {
		width: 90%;
		height: 9vw;
		font-size: 4vw;
		display: flex;
	}

	.wrapper .all .form-box button {
		width: 10%;
		height: 10vw;
		font-size: 3.5vw;
		font-weight: 700;
		color: #fff;
		background-color: #38CA73;
		border-radius: 2px;
		border: none;
		outline: none;
		display: flex;
		align-items: center;
	}

	.wrapper .all .rukou p {
		margin: 1vw 3vw 2vw 3vw;
		font-size: 3.5vw;
		color: #666;
		display: flex;
	}

	.bigbox {
		transition: 1s border-color ease;
		box-sizing: border-box;
		margin-left: 3vw;
		padding: 6vw 2vw 0 2vw;
		width: 100%;
		height: 90vw;
		background-color: #fff;
		border: 2px solid #666;
		font-size: 6.5vw;
		font-family: 宋体;
		color: #333;
		display: flex;
	}
	.jray{
		border: 2px solid #38CA73;
	}
	.blue{
		border: 2px solid #0097FF;
	}
	@keyframes move {
	0% { transform: translateX(0px); }
	25% { transform: translateX(100px); }
	50% { transform: translateX(200px); }
	75% { transform: translateX(100px); }
	100% { transform: translateX(0px); }
	}
	
	.rukou {
	  animation: move 3s;
	}
	.ps p{
	font-size: 2.5vw;
	color: #666;
	display: flex;
	margin: 1vw 3vw 2vw 3vw;
	}
	/****************** 底部菜单部分 ******************/
</style>