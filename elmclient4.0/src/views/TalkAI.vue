<template>
	<div>
		<!-- 聊天消息显示区域 -->


		<!-- 输入框和发送按钮 -->
		<input v-model="inputMessage" placeholder="请输入消息">
		<button @click="getReply">发送</button>
		<div class="big-box">{{messages[0]}}</div>
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
		name: 'AddUserAddress',
		components: {
			Footer
		},
		setup() {
			const messages = ref([null, null]); //消息数组
			const inputMessage = ref(''); //输入框中的消息
			const conversation_id = ref("null"); //对话Id
			const mark = ref(0);

			const getReply = async () => {
				if (mark.value === 0) {
					console.log(conversation_id.value);
					axios
						.post('AIController/getContinuousAnswer', qs.stringify({
							question: inputMessage.value.toString(),
							conversation_id: 'null'
						}))
						.then(response => {
							messages.value = response.data;
						})
						.catch(error => {
							console.error(error);
						});
					mark.value = 1;
					inputMessage.value = '';
				} else {
					console.log(conversation_id.value);
					axios
						.post('AIController/getContinuousAnswer', qs.stringify({
							question: inputMessage.value.toString(),
							conversation_id: conversation_id.value
						}))
						.then(response => {
							messages.value = response.data;
						})
						.catch(error => {
							console.error(error);
						});
						inputMessage.value = '';
				}

			};

			watch(messages, (newmessages) => {
				// 在 realPrice 发生变化时执行相应操作
				conversation_id.value = messages.value[1];
				console.log(messages.value);
				console.log('conversation_id 变化:', conversation_id);
				// 在这里可以执行其他操作，例如发起支付请求
			});
			return {
				messages,
				inputMessage,
				conversation_id,
				getReply
			};
		}
	};
</script>
<style>
	.big-box {
		margin: 5vw 0 0 0;
		padding: 0 5vw;
		width: 100%;
		height: 22vw;
		border: 1px solid #000;
		background-color: #fff;
		padding: 0vw 6vw;
		font-size: 5vw;
		font-family: 宋体;
		color: #333;
		display: flex;
	}
</style>