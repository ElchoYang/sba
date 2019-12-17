import Axios from '@/assets/js/AxiosPlugin'
import { API_URL_USER } from '@/views/sba/apis/baseSetup'

// api path

// let contextPath = '/demo'
export const GeneralValidCode = () => {
  return Axios.post(API_URL_USER + '/stockexchange/common/GeneralValidCode').then(function (res) {
    return res
  })
}

export const getLang = () => {
  return Axios.post(API_URL_USER + '/stockexchange/Common/GetLang').then(function (res) {
    return res
  })
}

export const removeUploadFile = (data) => {
  return Axios.post(API_URL_USER + '/stockexchange/DocUpload/uploadstep02/RemoveUploadFile', { 'uid': data }).then(function (res) {
    return res
  })
}
export const verifyValidCode = (data) => {
  return Axios.post(API_URL_USER + '/stockexchange/Common/VerifyValidCode', { 'ValidCode': data }).then(function (res) {
    return res
  })
}
