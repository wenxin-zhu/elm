/*
使用那个事件 onscroll 移动端与pc端都有效
获取滚动条位置 获取pc端滚动条位置 document.documentElement.scrollTop
移动端 document.body.scrollTop
判断视口宽度 documentElement.clientWidth 移动端pc端同时有效
 */
window.onload = function(){
	document.onscroll = function(){
		//获取滚动条位置
		let s1 = document.documentElement.scrollTop;
		let s2 = document.body.scrollTop;
		let scroll = s1==0?s2:s1;
		//获取视口宽度
		let width = document.documentElement.clientWidth;
		//获取顶部固定块
		let search = document.getElementById('fixedBox');
		//判断滚动条超过视口宽度的12%，搜索块变成固定定位
		if(scroll>width*0.12){
		search.style.position = 'fixed';
		search.style.left = '0';
		search.style.top = '0';
		}else{
			search.style.position = 'static';
		}
	}
}