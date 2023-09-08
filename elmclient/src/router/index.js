import { createApp } from 'vue'
import { createRouter, createWebHistory, useRoute } from 'vue-router'
import Index from '../views/Index.vue'
import BusinessList from '../views/BusinessList.vue'
import BusinessInfo from '../views/BusinessInfo.vue'
import Login from '../views/Login.vue'
import Orders from '../views/Orders.vue'
import UserAddress from '../views/UserAddress.vue'
import Payment from '../views/Payment.vue'
import OrderList from '../views/OrderList.vue'
import Register from '../views/Register.vue'
import AddUserAddress from '../views/AddUserAddress.vue'
import EditUserAddress from '../views/EditUserAddress.vue'
import ConfirmPayment from '../views/ConfirmPayment.vue'
import MyScore from '../views/MyScore.vue'
import Conversation from '../views/Conversation.vue'
import MyAI from '../views/MyAI.vue'
import MyInfo from '../views/MyInfo.vue'

const routes = [{
	path: '/',
	name: 'Home',
	component: Index  //默认首页为Index
}, {
	path: '/index',
	name: 'Index',
	component: Index
}, {
	path: '/businessList',
	name: 'BusinessList',
	component: BusinessList
}, {
	path: '/businessInfo',
	name: 'BusinessInfo',
	component: BusinessInfo
}, {
	path: '/login',
	name: 'Login',
	component: Login
}, {
	path: '/orders',
	name: 'Orders',
	component: Orders
}, {
	path: '/userAddress',
	name: 'UserAddress',
	component: UserAddress
}, {
	path: '/payment',
	name: 'Payment',
	component: Payment
}, {
	path: '/orderList',
	name: 'OrderList',
	component: OrderList
}, {
	path: '/register',
	name: 'Register',
	component: Register
}, {
	path: '/addUserAddress',
	name: 'AddUserAddress',
	component: AddUserAddress
}, {
	path: '/editUserAddress',
	name: 'EditUserAddress',
	component: EditUserAddress
}, {
	path: '/confirmPayment',
	name: 'ConfirmPayment',
	component: ConfirmPayment
}, {
	path: '/myScore',
	name: 'MyScore',
	component: MyScore
}, {
	path: '/conversation',
	name: 'Conversation',
	component: Conversation
}, {
	path: '/myAI',
	name: 'MyAI',
	component: MyAI
}, {
	path: '/myInfo',
	name: 'MyInfo',
	component: MyInfo
}  ]

//创建Vue Router实例，配置路由的历史模式和路由规则
const router = createRouter({  
  history: createWebHistory(),
  routes
});

//解决重复路由报异常问题
const originalPush = router.push;
router.push = function push(location) {
  return originalPush.call(this, location).catch(err => err);
};

createApp(useRoute)
createApp(router).mount('#app')

//导出之前创建的Vue Router实例，便于其他模块或组件引用
export default router; 
