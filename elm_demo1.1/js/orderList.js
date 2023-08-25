window.onload = function(){
    //获取显示隐藏订单按钮DOM数组
    let showBtnArr = document.getElementsByClassName('fa-caret-down'); //通过类样式名获取一组按钮
	//获取订单明细DOM数组
	let detailetBoxArr = document.getElementsByClassName('order-detailet'); //通过类样式名获取一组订单明细
	//设置默认订单明细全部隐藏
	for(let i=0; i<detailetBoxArr.length; i++){
		detailetBoxArr[i].style.display='none'; //赋值
	}
	
	for(let i=0; i<showBtnArr.length; i++){
		showBtnArr[i].onclick = function(){
		    if(detailetBoxArr[i].style.display=='none'){
				detailetBoxArr[i].style.display='block';
			}else{
				detailetBoxArr[i].style.display='none';
			}
		}
	}
}