import { createApp } from 'vue';
import { createRouter, createWebHistory ,useRoute} from 'vue-router';
import Index from '../views/Index.vue'
import BusinessList from '../views/BusinessList.vue'
import BusinessInfo from '../views/BusinessInfo.vue'
import Login from '../views/Login.vue'
import OrderList from '../views/OrderList.vue'
import Register from '../views/Register.vue'
import Orders from '../views/Orders.vue'
import UserAddress from '../views/UserAddress.vue'
import AddUserAddress from '../views/AddUserAddress.vue'
import EditUserAddress from '../views/EditUserAddress.vue'
import Payment from '../views/Payment.vue'
import ConfirmPayment from '../views/ConfirmPayment.vue'
import MyScore from '../views/MyScore.vue'
const routes = [{
	path: '/',
	name: 'Home',
	component: Index  //默认首页
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
	path: '/orderList',
	name: 'OrderList',
	component: OrderList
}, {
	path: '/register',
	name: 'Register',
	component: Register
}, {
	path: '/orders',
	name: 'Orders',
	component: Orders
}, {
	path: '/userAddress',
	name: 'UserAddress',
	component: UserAddress
}, {
	path: '/addUserAddress',
	name: 'AddUserAddress',
	component: AddUserAddress
}, {
	path: '/editUserAddress',
	name: 'EditUserAddress',
	component: EditUserAddress
}, {
	path: '/payment',
	name: 'Payment',
	component: Payment
}, {
	path: '/confirmPayment',
	name: 'ConfirmPayment',
	component: ConfirmPayment
}, {
	path: '/myScore',
	name: 'MyScore',
	component: MyScore
}]

const router = createRouter({  // 使用 createRouter 而不是 Router
  history: createWebHistory(),
  routes
});

//解决重复路由报异常问题
const originalPush = router.push;
router.push = function push(location) {
  return originalPush.call(this, location).catch(err => err);
};

export default router; 
