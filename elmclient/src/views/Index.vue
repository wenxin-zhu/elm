<template>
	<!-- 总容器 -->
	<div class="wrapper">
		<!-- header part -->
		<header @click="toUserAddress">
			<div class="icon-location-box">
				<div class="icon-location"></div>
			</div> <!--定位图标-->
			<div class="location-text">{{deliveryaddress!=null?deliveryaddress.address:'定位失败'}}<i
					class="fa fa-caret-down"></i></div>
		</header>
		<!-- search部分 -->
		<div class="search">
			<div class="search-fixed-top"  ref="fixedBox" :style="styles">
				<div class="search-box">
					<i class="fa fa-search"></i>搜索饿了么商家、商品名称
				</div>
			</div>
		</div>
		<!--点餐分类-->
		<ul class="foodtype">
			<li @click="toBusinessList(1)">
				<img src="../assets/dcfl01.png">
				<p>美食</p>
			</li>
			<li @click="toBusinessList(2)">
				<img src="../assets/dcfl02.png">
				<p>早餐</p>
			</li>
			<li @click="toBusinessList(3)">
				<img src="../assets/dcfl03.png">
				<p>跑腿代购</p>
			</li>
			<li @click="toBusinessList(4)">
				<img src="../assets/dcfl04.png">
				<p>汉堡披萨</p>
			</li>
			<li @click="toBusinessList(5)">
				<img src="../assets/dcfl05.png">
				<p>甜品饮料</p>
			</li>
			<li @click="toBusinessList(6)">
				<img src="../assets/dcfl06.png">
				<p>速食简餐</p>
			</li>
			<li @click="toBusinessList(7)">
				<img src="../assets/dcfl07.png">
				<p>地方小吃</p>
			</li>
			<li @click="toBusinessList(8)">
				<img src="../assets/dcfl08.png">
				<p>米粉面馆</p>
			</li>
			<li @click="toBusinessList(9)">
				<img src="../assets/dcfl09.png">
				<p>包子粥铺</p>
			</li>
			<li @click="toBusinessList(10)">
				<img src="../assets/dcfl10.png">
				<p>炸鸡炸串</p>
			</li>
		</ul>
		<!--横幅广告部分-->
		<div class="banner">
			<h3>品质套餐</h3>
			<p>搭配齐全吃得好</p>
			<a>立即抢购 &gt;</a>

		</div>
		<!--超级会员部分-->
		<div class="supermenber">
			<div class="left">
				<img src="../assets/super_member.png">
				<h3>新功能：AI</h3>
				<p>&#8226; 解锁你的私人AI助手</p>
			</div>
			<div class="right" @click="toAI">
				点击使用 &gt;
			</div>
		</div>
		<!--推荐商家部分-->
		<div class="recommend">
			<div class="recommend-line"></div>
			<p>推荐商家</p>
			<div class="recommend-line"></div>
		</div>
		<!--推荐方式部分-->
		<ul class="recommendtype">
			<li>综合排序<i class="fa fa-caret-down"></i></li>
			<li>距离最近</li>
			<li>销量最高</li>
			<li>筛选<i class="fa fa-filter"></i></li>
		</ul>
		<!--推荐商家列表部分-->
		<ul class="bussiness">
			<li>
				<img src="../assets/sj01.png">
				<div class="bussiness-info">
					<div class="bussiness-info-h">
						<h3>万家饺子（软件园E18店）</h3>
						<div class="bussiness-info-like">&#8226;</div>
					</div>
					<div class="bussiness-info-star">
						<div class="bussiness-info-star-left">
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<p>4.9 月售345单</p>
						</div>
						<div class="bussiness-info-star-right">
							蜂鸟专送
						</div>
					</div>
					<div class="bussiness-info-delivery">
						<p>&#165; 15起送 | &#165;3配送</p>
						<p>3.22km | 30分钟</p>
					</div>
					<div class="bussiness-info-explain">
						<div>各种饺子</div>
					</div>
					<div class="bussiness-info-promotion">
						<div class="bussiness-info-promotion-left">
							<div class="bussiness-info-promotion-left-incon">新</div>
							<p>饿了么新用户首单立减9元</p>
						</div>
						<div class="bussiness-info-promotion-right">
							<p>2个活动</p>
							<i class="fa fa-caret-down"></i>
						</div>
					</div>
					<div class="bussiness-info-promotion">
						<div class="bussiness-info-promotion-left">
							<div class="bussiness-info-promotion-left-incon" style="background-color: #F1884F;">特</div>
							<p>特价商品5元起</p>
						</div>
					</div>
				</div>
			</li>
			<li>
				<img src="../assets/sj02.png">
				<div class="bussiness-info">
					<div class="bussiness-info-h">
						<h3>小锅饭豆腐馆（全运店）</h3>
						<div class="bussiness-info-like">&#8226;</div>
					</div>
					<div class="bussiness-info-star">
						<div class="bussiness-info-star-left">
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<p>4.9 月售345单</p>
						</div>
						<div class="bussiness-info-star-right">
							蜂鸟专送
						</div>
					</div>
					<div class="bussiness-info-delivery">
						<p>&#165; 15起送 | &#165;3配送</p>
						<p>3.22km | 30分钟</p>
					</div>
					<div class="bussiness-info-explain">
						<div>各种饺子</div>
					</div>
					<div class="bussiness-info-promotion">
						<div class="bussiness-info-promotion-left">
							<div class="bussiness-info-promotion-left-incon">新</div>
							<p>饿了么新用户首单立减9元</p>
						</div>
						<div class="bussiness-info-promotion-right">
							<p>2个活动</p>
							<i class="fa fa-caret-down"></i>
						</div>
					</div>
					<div class="bussiness-info-promotion">
						<div class="bussiness-info-promotion-left">
							<div class="bussiness-info-promotion-left-incon" style="background-color: #F1884F;">特</div>
							<p>特价商品5元起</p>
						</div>
					</div>
				</div>
			</li>
			<li>
				<img src="../assets/sj03.png">
				<div class="bussiness-info">
					<div class="bussiness-info-h">
						<h3>麦当劳麦乐送（全运路店）</h3>
						<div class="bussiness-info-like">&#8226;</div>
					</div>
					<div class="bussiness-info-star">
						<div class="bussiness-info-star-left">
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<p>4.9 月售345单</p>
						</div>
						<div class="bussiness-info-star-right">
							蜂鸟专送
						</div>
					</div>
					<div class="bussiness-info-delivery">
						<p>&#165; 15起送 | &#165;3配送</p>
						<p>3.22km | 30分钟</p>
					</div>
					<div class="bussiness-info-explain">
						<div>各种饺子</div>
					</div>
					<div class="bussiness-info-promotion">
						<div class="bussiness-info-promotion-left">
							<div class="bussiness-info-promotion-left-incon">新</div>
							<p>饿了么新用户首单立减9元</p>
						</div>
						<div class="bussiness-info-promotion-right">
							<p>2个活动</p>
							<i class="fa fa-caret-down"></i>
						</div>
					</div>
					<div class="bussiness-info-promotion">
						<div class="bussiness-info-promotion-left">
							<div class="bussiness-info-promotion-left-incon" style="background-color: #F1884F;">特</div>
							<p>特价商品5元起</p>
						</div>
					</div>
				</div>
			</li>
			<li>
				<img src="../assets/sj04.png">
				<div class="bussiness-info">
					<div class="bussiness-info-h">
						<h3>米村拌饭（潭南店）</h3>
						<div class="bussiness-info-like">&#8226;</div>
					</div>
					<div class="bussiness-info-star">
						<div class="bussiness-info-star-left">
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<p>4.9 月售345单</p>
						</div>
						<div class="bussiness-info-star-right">
							蜂鸟专送
						</div>
					</div>
					<div class="bussiness-info-delivery">
						<p>&#165; 15起送 | &#165;3配送</p>
						<p>3.22km | 30分钟</p>
					</div>
					<div class="bussiness-info-explain">
						<div>各种饺子</div>
					</div>
					<div class="bussiness-info-promotion">
						<div class="bussiness-info-promotion-left">
							<div class="bussiness-info-promotion-left-incon">新</div>
							<p>饿了么新用户首单立减9元</p>
						</div>
						<div class="bussiness-info-promotion-right">
							<p>2个活动</p>
							<i class="fa fa-caret-down"></i>
						</div>
					</div>
					<div class="bussiness-info-promotion">
						<div class="bussiness-info-promotion-left">
							<div class="bussiness-info-promotion-left-incon" style="background-color: #F1884F;">特</div>
						</div>
					</div>
				</div>
			</li>
			<li>
				<img src="../assets/sj05.png">
				<div class="bussiness-info">
					<div class="bussiness-info-h">
						<h3>申记串道（中海康城店）</h3>
						<div class="bussiness-info-like">&#8226;</div>
					</div>
					<div class="bussiness-info-star">
						<div class="bussiness-info-star-left">
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<p>4.9 月售345单</p>
						</div>
						<div class="bussiness-info-star-right">
							蜂鸟专送
						</div>
					</div>
					<div class="bussiness-info-delivery">
						<p>&#165; 15起送 | &#165;3配送</p>
						<p>3.22km | 30分钟</p>
					</div>
					<div class="bussiness-info-explain">
						<div>各种饺子</div>
					</div>
					<div class="bussiness-info-promotion">
						<div class="bussiness-info-promotion-left">
							<div class="bussiness-info-promotion-left-incon">新</div>
							<p>饿了么新用户首单立减9元</p>
						</div>
						<div class="bussiness-info-promotion-right">
							<p>2个活动</p>
							<i class="fa fa-caret-down"></i>
						</div>
					</div>
					<div class="bussiness-info-promotion">
						<div class="bussiness-info-promotion-left">
							<div class="bussiness-info-promotion-left-incon" style="background-color: #F1884F;">特</div>
							<p>特价商品5元起</p>
						</div>
					</div>
				</div>
			</li>
			<li>
				<img src="../assets/sj06.png">
				<div class="bussiness-info">
					<div class="bussiness-info-h">
						<h3>半亩良田排骨米饭</h3>
						<div class="bussiness-info-like">&#8226;</div>
					</div>
					<div class="bussiness-info-star">
						<div class="bussiness-info-star-left">
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<i class="fa fa-star"></i>
							<p>4.9 月售345单</p>
						</div>
						<div class="bussiness-info-star-right">
							蜂鸟专送
						</div>
					</div>
					<div class="bussiness-info-delivery">
						<p>&#165; 15起送 | &#165;3配送</p>
						<p>3.22km | 30分钟</p>
					</div>
					<div class="bussiness-info-explain">
						<div>各种饺子</div>
					</div>
					<div class="bussiness-info-promotion">
						<div class="bussiness-info-promotion-left">
							<div class="bussiness-info-promotion-left-incon">新</div>
							<p>饿了么新用户首单立减9元</p>
						</div>
						<div class="bussiness-info-promotion-right">
							<p>2个活动</p>
							<i class="fa fa-caret-down"></i>
						</div>
					</div>
					<div class="bussiness-info-promotion">
						<div class="bussiness-info-promotion-left">
							<div class="bussiness-info-promotion-left-incon" style="background-color: #F1884F;">特</div>
							<p>特价商品5元起</p>
						</div>
					</div>
				</div>
			</li>
		</ul>
		<!--底部菜单部分-->
		<Footer></Footer>

	</div>
</template>

<script>
	import Footer from '../components/Footer.vue';
	import {
		onMounted,
		onBeforeUnmount,
		ref,
		reactive
	} from 'vue';
	import {
		useRouter
	} from 'vue-router';
	import {
		setLocalStorage,
		getLocalStorage,
		getSessionStorage
	} from '../common.js';
	export default {
		name: 'Index',
		components: {
			Footer,
		},
		setup() {
			const router = useRouter();
			const fixedBox = ref(null);
			const deliveryaddress = ref(null);
			const styles = reactive({
			    position: 'static',
			    left: 'unset',
			    top: 'unset'
			  });
			onMounted(() => {
				whereIm();
				const handleScroll = () => {
					/*let s1 = document.documentElement.scrollTop;
					let s2 = document.body.scrollTop;
					let scroll = s1 === 0 ? s2 : s1;
						

					let width = document.documentElement.clientWidth;*/
					let s1 = document.documentElement.scrollTop;
					let s2 = document.body.scrollTop;
					let scroll = s1 === 0 ? s2 : s1;
					const width = window.innerWidth || document.documentElement.clientWidth || document.body
						.clientWidth;

					// 获取顶部固定块
					//let search = fixedBox.value.parentNode;
					let search = fixedBox.value.value;

					// 判断滚动条超过视口宽度的12%，搜索块变成固定定位
					if (scroll > width * 0.12) {
						styles.position = 'fixed';
						    styles.left = '0';
						    styles.top = '0';
						/*search.style.position = 'fixed';
						search.style.left = '0';
						search.style.top = '0';*/
					} else {
						 styles.position = 'static';
						//search.style.position = 'static';
					}
				};

				document.addEventListener('scroll', handleScroll);

				onBeforeUnmount(() => {
					document.removeEventListener('scroll', handleScroll);
				});
			});
			//地址显示
			const whereIm = () => {
				const user = ref(getSessionStorage('user'));
				if (user.value) {
					deliveryaddress.value = getLocalStorage(user.value?.userId);
				}
			}
			//设置地址跳转
			const toUserAddress = () => {
				sessionStorage.setItem('redirectPath', '/index');
				router.push({
					path: '/userAddress'
				});

			}
			const toBusinessList = (orderTypeId) => {
				router.push({
					path: '/businessList',
					query: {
						orderTypeId: orderTypeId
					}
				});
			};
			const toAI = () => {
				router.push({
					path: '/myAI'
				});
			};
			return {
				fixedBox,
				whereIm,
				toBusinessList,
				toUserAddress,
				deliveryaddress,
				toAI,styles
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

	/***header***/
	.wrapper header {
		width: 100%;
		height: 12vw;
		background-color: #0097FF;

		display: flex;
		justify-content: flex-start;
		align-items: center;
		position: absolute;
	}

	.wrapper header .icon-location-box {
		width: 3.5vw;
		height: 3.5vw;
		margin: 0 1vw 0 3vw;
		/*上顺时针*/
	}


	.wrapper header .icon-location-box .icon-location {
		position: relative;
		width: 100%;
		height: 100%;
		/*正方形*/
		border-radius: 50% 50% 50% 0;
		/*切三个圆角*/
		background: #fff;
		transform: rotate(-45deg);
	}


	.wrapper header .icon-location-box .icon-location:after {
		content: '';
		width: 40%;
		height: 40%;
		margin: 30% 0 0 29%;
		background-color: #0097FF;
		position: absolute;
		border-radius: 50%;
	}




	.wrapper header .location-text {
		font-size: 4.5vw;
		font-weight: 700;
		color: #fff;
	}

	.wrapper header .location-text .fa-caret-down {
		margin-left: 1vw;
	}

	/***search***/
	.wrapper .search {
		width: 100%;
		height: 13vw;
		margin-top: 12vw;
	}

	.wrapper .search .search-fixed-top {
		width: 100%;
		height: 13vw;
		background-color: #0097FF;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .search .search-fixed-top .search-box {
		width: 90%;
		height: 9vw;
		background-color: #fff;
		border-radius: 2px;

		display: flex;
		justify-content: center;
		align-items: center;
		/*弹性布局*/

		font-size: 3.5vw;
		color: #AEAEAE;
		font-family: "宋体";
		user-select: none;
		/*文本选中状态无效*/
	}

	.wrapper .search .search-fixed-top .search-box .fa-search {
		margin-right: 1vw;
	}

	/***点餐分类部分***/
	.wrapper .foodtype {
		width: 100%;
		height: 48vw;

		display: flex;
		flex-wrap: wrap;
		justify-content: space-around;
		align-content: center;
	}

	.wrapper .foodtype li {
		width: 18vw;
		height: 20vw;

		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;

		user-select: none;
		cursor: pointer;
	}

	.wrapper .foodtype li img {
		width: 12vw;
		height: 12vw;
	}

	.wrapper .foodtype li p {
		font-size: 3.2vw;
		color: #666;
	}

	/***横幅广告部分***/
	.wrapper .banner {
		width: 95%;
		margin: 0 auto;
		/*传统方式水平居中*/
		height: 29vw;

		background-image: url(../assets/index_banner.png);
		background-repeat: no-repeat;
		background-size: cover;

		box-sizing: border-box;
		padding: 2vw 6vw;
	}

	.wrapper .banner h3 {
		font-size: 4.2vw;
		margin-bottom: 1.2vw;
	}

	.wrapper .banner p {
		font-size: 3.4vw;
		color: #666;
		margin-bottom: 2.4vw;
	}

	.wrapper .banner a {
		font-size: 3vw;
		color: #C79060;
		font-weight: #700;
	}

	/***超级会员部分***/
	.wrapper .supermenber {
		width: 95%;
		margin: 0 auto;
		height: 11.5vw;
		background-color: #FEEDC1;
		margin-top: 1.3vw;
		border-radius: 2px;
		color: #644F1B;

		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.wrapper .supermenber .left {
		display: flex;
		align-items: center;
		margin-left: 4vw;
		user-select: none;
	}

	.wrapper .supermenber .left img {
		width: 6vw;
		height: 6vw;
		margin-right: 2vw;
	}

	.wrapper .supermenber .left h3 {
		font-size: 4vw;
		margin-right: 2vw;
	}

	.wrapper .supermenber .left p {
		font-size: 3vw;
	}

	.wrapper .supermenber .right {
		font-size: 3vw;
		margin-right: 4vw;
		cursor: pointer;
	}

	/***推荐商家部分***/
	.wrapper .recommend {
		width: 100%;
		height: 14vw;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .recommend .recommend-line {
		width: 6vw;
		height: 0.2vw;
		background-color: #888;
	}

	.wrapper .recommend p {
		font-size: 4vw;
		margin: 0 4vw;
	}

	/***推荐方式***/
	.wrapper .recommendtype {
		width: 100%;
		height: 5vw;
		margin-bottom: 5vw;

		display: flex;
		justify-content: space-around;
		align-items: center;
	}

	.wrapper .recommendtype li {
		font-size: 3.5vw;
		color: #555;
	}

	/***推荐商家列表部分***/
	.wrapper .bussiness {
		width: 100%;
		padding-bottom: 14vw;
	}

	.wrapper .bussiness li {
		width: 100%;
		box-sizing: border-box;
		padding: 2.5vw;

		user-select: none;

		border-bottom: solid 1px #DDD;

		display: flex;
	}

	.wrapper .bussiness li img {
		width: 18vw;
		height: 18vw;
	}

	.wrapper .bussiness li .bussiness-info {
		width: 100%;
		box-sizing: border-box;
		padding-left: 3vw;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-h {
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin-bottom: 2vw;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-h h3 {
		font-size: 4vw;
		color: #333;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-h .bussiness-info-like {
		width: 1.6vw;
		height: 3.4vw;
		background-color: #666;
		color: #fff;
		font-size: 4vw;
		margin-right: 4vw;

		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-star {
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin-bottom: 2vw;
		font-size: 3.1vw;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-star .bussiness-info-star-left {
		display: flex;
		align-items: center;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-star .bussiness-info-star-left .fa-star {
		color: #FEC80E;
		margin-right: 0.5vw;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-star .bussiness-info-star-left p {
		color: #666;
		margin-left: 1vw;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-star .bussiness-info-star-right {
		background-color: #0097FF;
		color: #fff;
		font-size: 2.7vw;
		border-radius: 2px;
		padding: 0 0.6vw;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-delivery {
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin-bottom: 2vw;

		font-size: 3.1vw;
		color: #666;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-explain {
		display: flex;
		align-items: center;
		margin-bottom: 3vw;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-explain div {
		border: solid 1px #DDD;
		font-size: 2.8vw;
		color: #666;
		border-radius: 3px;
		padding: 0 0.1vw;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-promotion {
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin-bottom: 1.8vw;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-promotion .bussiness-info-promotion-left {
		display: flex;
		align-items: center;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-promotion .bussiness-info-promotion-left .bussiness-info-promotion-left-incon {
		width: 4vw;
		height: 4vw;
		background-color: #70BC46;
		border-radius: 3px;
		font-size: 3vw;
		color: #fff;

		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-promotion .bussiness-info-promotion-left p {
		color: #666;
		font-size: 3vw;
		margin-left: 2vw;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-promotion .bussiness-info-promotion-right {
		display: flex;
		align-items: center;
		font-size: 2.5vw;
		color: #999;
	}

	.wrapper .bussiness li .bussiness-info .bussiness-info-promotion .bussiness-info-promotion-right p {
		margin-right: 2vw;
	}

	/***底部菜单部分***/
	/**.wrapper .footer {
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
	}**/
</style>