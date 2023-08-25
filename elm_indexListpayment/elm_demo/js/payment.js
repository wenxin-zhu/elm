/***<i class="fa fa-caret-down"></i>***/
window.onload = function(){
	//获取显示隐藏按钮DOM对象
	let showBtn = document.getElementById('showBtn');
	//获取显示隐藏按钮DOM对象
	let detailetBox = document.getElementById('detailetBox');
	//设置默认状态
	detailetBox.style.display='none';
	showBtn.onclick = function(){
		//判断订单明细是否隐藏，如果是显示，否则隐藏
			if(detailetBox.style.display == 'none'){
				detailetBox.style.display='block';
			}else{
				detailetBox.style.display='none';
			}
		}
}