import Axios from '@/assets/js/AxiosPlugin'
import {
  API_URL_USER
} from '@/views/sba/apis/baseSetup'

export const loadPageData = (param) => {
  return Axios.post(API_URL_USER + '/stockexchange/' + param + '/LoadPageData').then(function (res) {
    return res
  })
}

export const RequestAllUser = (data) => {
  return Axios.get(API_URL_USER + '/stockexchange/user/all').then(function (res) {
    console.log(res)
    return res
  })
}

export const Login = (data) => {
  return Axios.get(API_URL_USER + '/stockexchange/user/login').then(function (res) {
    console.log(res)
    return res
  })
}

export const loaduploadStep01Data = () => {
  return Axios.post(API_URL_USER + '/stockexchange/DocUpload/uploadstep01/LoadPageData').then(function (res) {
    return res
  })
}
export const loaduploadStep02Data = () => {
  return Axios.post(API_URL_USER + '/stockexchange/DocUpload/uploadstep02/LoadPageData').then(function (res) {
    return res
  })
}
export const loaduploadStep03Data = () => {
  return Axios.post(API_URL_USER + '/stockexchange/DocUpload/uploadstep03/LoadPageData').then(function (res) {
    return res
  })
}
