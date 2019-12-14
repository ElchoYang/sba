// 定义路由路径数组列表
export default [
  {
    path: '/login',
    name: 'login',
    component: resolve => require(['@/views/sba/login.vue'], resolve)
  },
  {
    path: '/logout',
    name: 'logout',
    component: resolve => require(['@/views/sba/logout.vue'], resolve)
  },
  {
    path: '/signup',
    name: 'signup',
    component: resolve => require(['@/views/sba/signup.vue'], resolve)
  },
  {
    path: '/docupload',
    name: 'docupload',
    component: resolve => require(['@/views/sba/docupload.vue'], resolve)
  },
  {
    path: '/importdata',
    name: 'importdata',
    component: resolve => require(['@/views/sba/importdata.vue'], resolve)
  },
  {
    path: '/managecompany',
    name: 'managecompany',
    component: resolve => require(['@/views/sba/manageCompany.vue'], resolve)
  },
  {
    path: '/manageexchange',
    name: 'manageexchange',
    component: resolve => require(['@/views/sba/manageExchange.vue'], resolve)
  },
  {
    path: '/updateIpoDetails',
    name: 'updateipo',
    component: resolve => require(['@/views/sba/updateIpoDetails.vue'], resolve)
  },
  {
    path: '/ipo',
    name: 'ipo',
    component: resolve => require(['@/views/sba/ipo.vue'], resolve)
  },
  {
    path: '/compareCompany',
    name: 'comparecompany',
    component: resolve => require(['@/views/sba/compareCompany.vue'], resolve)
  },
  {
    path: '/compareSectors',
    name: 'comparesectors',
    component: resolve => require(['@/views/sba/compareSectors.vue'], resolve)
  },
  {
    path: '/other',
    name: 'other',
    component: resolve => require(['@/views/sba/other.vue'], resolve)
  }
]
