// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import 'babel-polyfill'
import ElementUI from 'element-ui'
import ElementLocale from 'element-ui/lib/locale'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import router from './routers'
import VueI18n from 'vue-i18n'
import enLocale from 'element-ui/lib/locale/lang/en'
import zhLocale from 'element-ui/lib/locale/lang/zh-TW'
// import store from './stores'
import base from '@/views/sba/assets/js/base'

// import { getPermission } from '@/assets/js/util.js'

// 使用mock时，将引入mock，取消注释
// import './mocks/mock'

// mock 启动

Vue.config.productionTip = false

// axios 统一配置
Vue.use(ElementUI)
Vue.use(base)
Vue.use(VueI18n)

// 判断是否有token,没有则登陆

router.beforeEach((to, from, next) => {
  next()
})

router.afterEach((to, from) => {
  window.scrollTo(0, 0)
})

const mergeEN = Object.assign(require('@/views/sba/assets/lang/en.js'), enLocale)
const mergeZH = Object.assign(require('@/views/sba/assets/lang/zn.js'), zhLocale)
const i18n = new VueI18n({
  locale: 'EN-US',
  fallbackLocale: 'EN-US',
  messages: {
    'EN-US': mergeEN,
    'ZH-HK': mergeZH
  }
})
ElementLocale.i18n((key, value) => i18n.t(key, value))

// Vue.use(ElementUI, {
//   i18n: (key, value) => i18n.t(key, value)
// })
/* eslint-disable no-new */
new Vue({
  // store,
  el: '#app',
  router,
  i18n,
  template: '<App/>',
  components: { App }
})
