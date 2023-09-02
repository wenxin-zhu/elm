import { createApp } from 'vue'; // 导入 createApp 函数
import App from './App.vue'; // 导入根组件
import router from './router';

import 'font-awesome/css/font-awesome.min.css';
import axios from 'axios';
import qs from 'qs';
import {
  getCurDate,
  setSessionStorage,
  getSessionStorage,
  removeSessionStorage,
  setLocalStorage,
  getLocalStorage,
  removeLocalStorage
} from './common.js';

const app = createApp(App); // 创建 Vue 应用实例

app.config.productionTip = false;

// 设置 axios 的基础 url 部分
axios.defaults.baseURL = 'http://localhost:8080/elm/';

// 将 axios 挂载到 vue 实例上
app.config.globalProperties.$axios = axios;

// 将 qs 挂载到 vue 实例上
app.config.globalProperties.$qs = qs;

// 将一些方法挂载到 vue 实例上
app.config.globalProperties.$getCurDate = getCurDate;
app.config.globalProperties.$setSessionStorage = setSessionStorage;
app.config.globalProperties.$getSessionStorage = getSessionStorage;
app.config.globalProperties.$removeSessionStorage = removeSessionStorage;
app.config.globalProperties.$setLocalStorage = setLocalStorage;
app.config.globalProperties.$getLocalStorage = getLocalStorage;
app.config.globalProperties.$removeLocalStorage = removeLocalStorage;

// 路由守卫 每次路由之前进行拦截并处理
router.beforeEach((to, from, next) => {
  let user = sessionStorage.getItem('user');
  // 除了登录、注册、首页、商家列表、商家信息之外，都需要判断是否登录
  if (
    !(
      to.path == '/' ||
      to.path == '/index' ||
      to.path == '/businessList' ||
      to.path == '/businessInfo' ||
      to.path == '/login' ||
      to.path == '/register'
    )
  ) {
    if (user == null) {
      router.push('/login'); // 强制登陆
      location.reload(); // 刷新
    }
  }
  next();
});

app.use(router); // 安装路由
app.mount('#app'); // 挂载根组件到 DOM
