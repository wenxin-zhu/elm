<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<p>AI对话</p>
		</header>

		<h5>
			请在输入框中输入想要与AI讨论的内容，并点击发送，即可进行对话！
		</h5>

		<!-- 对话框容器 -->
		<div class="talk-con">
			<div class="talk-show">
				<div :class="(message.person === 'AI') ? 'atalk' : 'btalk'" v-for="message in messages" :key="message.id">
					<img v-if="message.person === 'AI'" src="../assets/avatar1.jpg">
					<!-- <img src="(message.person === 'AI') ? '../assets/dcfl01.png' : '../assets/dcfl01.png'"> -->
					<!-- <span>{{ message.person }}：{{ message.text }}</span> -->
					<span>{{ message.text }}</span>
					<img v-if="message.person === '用户'" src="../assets/avatar2.jpg">
				</div>
			</div>
			<div class="talk-input">
				<input type="text" class="talk-word" v-model="inputMessage">
				<input type="button" value="发送" class="talk-sub" @click="getReply">
			</div>
		</div>

		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>

<script>
	import {
		ref,
		onMounted,
		watch
	} from 'vue';
	import {
		useRouter
	} from 'vue-router';
	import qs from 'qs';
	import axios from 'axios';
	import Footer from '../components/Footer.vue';

	export default {
		name: 'Conversation',
		components: {
			Footer
		},
		setup() {
			const messages = ref([]);
			const messages0 = ref([null, null]); //每条消息构成的数组
			const inputMessage = ref(''); //输入框中的消息
			const conversation_id = ref('null');
			const mark = ref(0);

			//此函数用于更新message0数组，即消息内容+conversation_id
			const getReply = async (question) => {
				messages.value.push({
					id: Date.now(), //使用时间戳作为唯一键
					person: '用户',
					text: inputMessage.value,
				});
				//mark赋初值为0，第一次发送消息时设置conversation_id为'null'，设mark为1，之后conversation_id更新为messages0.value[1]并保持不变
				if (mark.value === 0) {
					//console.log('初始值', conversation_id.value);
					axios
						.post('AIController/getContinuousAnswer', qs.stringify({
							question: inputMessage.value.toString(),
							conversation_id: 'null'
						}))
						.then(response => {
							messages0.value = response.data;
							const aiResponse = response.data;
							messages.value.push({
								id: Date.now(), // 使用时间戳作为唯一键
								person: 'AI',
								text: aiResponse[0],
							});
						})
						.catch(error => {
							console.error(error);
						});
					mark.value = 1;
					inputMessage.value = '';
				} else {
					//console.log('初始值', conversation_id.value);
					axios
						.post('AIController/getContinuousAnswer', qs.stringify({
							question: inputMessage.value.toString(),
							conversation_id: conversation_id.value
						}))
						.then(response => {
							messages0.value = response.data;
							const aiResponse = response.data;
							messages.value.push({
								id: Date.now(), // 使用时间戳作为唯一键
								person: 'AI',
								text: aiResponse[0],
								// avatar: "../assets/dcfl02.png"
							});
						})
						.catch(error => {
							console.error(error);
						});
					//注意清空输入框
					inputMessage.value = '';
				}
			}

			watch(messages0, (newmessages0) => {
				// 在 messages0 发生变化时更新conversation_id
				conversation_id.value = messages0.value[1];
				// console.log(messages.value);
				// console.log('conversation_id 变化:', conversation_id.value);
			});

			return {
				messages0,
				messages,
				inputMessage,
				conversation_id,
				getReply
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

	.wrapper h5 {
		margin-top: 12vw;
		box-sizing: border-box;
		padding: 2vw 4vw;
		font-size: 2.3vw;
		font-weight: 300;
		color: #999;
	}

	/* 对话框容器样式 */
	.wrapper .talk-con {
		width: 100%;
		margin: 5px auto 0;
	}

	/* 对话显示区样式 */
	.wrapper .talk-con .talk-show {
		width: 90%;
		height: 400px;
		border: 1px solid #666;
		background: #fff;
		margin: 10px auto 0;
		overflow-y: scroll;
		padding: 5px;
	}
	
	/* 添加滚动条样式 */
	.wrapper .talk-con .talk-show::-webkit-scrollbar {
	  width: 10px;
	}
	
	/* 添加滚动条轨道样式 */
	.wrapper .talk-con .talk-show::-webkit-scrollbar-track {
	  background: #f1f1f1; /* 滚动条轨道背景色 */
	}
	
	/* 添加滚动条滑块样式 */
	.wrapper .talk-con .talk-show::-webkit-scrollbar-thumb {
	  background: #999; /* 滚动条滑块背景色 */
	}

	/* 对话框样式 */
	.wrapper .talk-con .talk-show .atalk,
	.wrapper .talk-con .talk-show .btalk {
		margin: 5px;
	}
	
	/*用户聊天框部分要靠右，使用justify-content设置*/
	.wrapper .talk-con .talk-show .btalk {
		text-align: right;
		display: flex;
		justify-content: flex-end;
	}

	.atalk,
	.btalk {
		display: flex;
	}
	
	.atalk img,
	.btalk img {
		width: 35px;
		height: 35px;
	}
	
	.atalk span,
	.btalk span {
		/*排列聊天框和内部文字*/
		display: inline-block;
		background: #0181cc;
		border-radius: 10px;
		color: #fff;
		padding: 5px 10px;
		max-width: 80%;
	}

	.wrapper .talk-con .talk-show .btalk span {
		margin-left: 10px;
	}
	
	.wrapper .talk-con .talk-show .btalk span {
		background: #ef8201;
		margin-right: 10px;
	}

	.wrapper .talk-con .talk-input {
		width: 90%;
		margin: 10px auto 0;
		display: flex;
	}

	.wrapper .talk-con .talk-input .talk-word {
		width: 420px;
		height: 26px;
		padding: 0px;
		text-indent: 10px;
		outline: none;
	}

	.wrapper .talk-con .talk-input .talk-sub {
		width: 56px;
		height: 30px;
		margin-left: 10px;
	}
</style>