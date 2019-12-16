/**
 * ajax 模块，可以将 axios 替换成 $.ajax 等
 */
import axios from 'axios'
import {
  catchError
} from '@/assets/js/util.js'
import {
  Loading
} from 'element-ui'
// import router from '@/routers'

let loading
let needLoadingRequestCount = 0

function startLoading () {
  loading = Loading.service({
    lock: true,
    text: '',
    background: 'rgba(255, 255, 255, 0.7)'
  })
}

function endLoading () {
  loading.close()
}

export function showFullScreenLoading () {
  if (needLoadingRequestCount === 0) {
    startLoading()
  }
  needLoadingRequestCount++
}

export function tryHideFullScreenLoading () {
  if (needLoadingRequestCount <= 0) return
  needLoadingRequestCount--
  if (needLoadingRequestCount === 0) {
    endLoading()
  }
}

const Axios = axios.create({
  timeout: 150000
})

// 暂时不启用过滤
// 添加请求拦截器
Axios.defaults.headers['Cache-Control'] = 'no-cache'
Axios.defaults.headers['Pragma'] = 'no-cache'
// Axios.defaults.headers['rejectUnauthorized'] = 'false'

Axios.interceptors.request.use(config => {
  // 用户登录后,存储token，发送请求时，设置token sessionStorage.token
  if (sessionStorage.token) {
    config.headers.Authorization = sessionStorage.token
  }
  // if (!config.disableLoading) {
  //   showFullScreenLoading()
  // }
  return config
}, error => {
  return Promise.reject(error)
})

// 返回状态判断(添加响应拦截器)
// Add a response interceptor
Axios.interceptors.response.use(response => {
  try {
    console.log('request : ' + response.request.responseURL)
    console.log(response.data)
  } catch (error) {
    console.log(error)
    console.log(response)
  }
  tryHideFullScreenLoading()

  return response
}, function (error) {
  tryHideFullScreenLoading()
  console.log(error)
  return catchError(error)
})

export default Axios
