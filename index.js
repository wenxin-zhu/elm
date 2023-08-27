import { createApp } from 'vue'
//import { createRouter, createWebHistory } from 'vue-router'
import { createRouter, createWebHistory, useRoute } from 'vue-router'
import Index from '../views/Index.vue'
import BusinessList from '../views/BusinessList.vue'
//import BusinessInfo from '../views/BusinessInfo.vue'


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
}/*, {
	path: '/businessInfo',
	name: 'BusinessInfo',
	component: BusinessInfo
}*/ ]

const router = createRouter({  // 使用 createRouter 而不是 Router
  history: createWebHistory(),
  routes
});

//解决重复路由报异常问题
const originalPush = router.push;
router.push = function push(location) {
  return originalPush.call(this, location).catch(err => err);
};

createApp(useRoute) // 使用 useRoute
createApp(router).mount('#app')

export default router; 
