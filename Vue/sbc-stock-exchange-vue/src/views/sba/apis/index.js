import Axios from '@/assets/js/AxiosPlugin'
import { BASE_PATH } from '@/views/sba/apis/baseSetup'

// api path

// let contextPath = '/demo'
export const GeneralValidCode = () => {
  return Axios.post(BASE_PATH + '/stockexchange/common/GeneralValidCode').then(function (res) {
    return res
  })
}

export const getLang = () => {
  return Axios.post(BASE_PATH + '/stockexchange/Common/GetLang').then(function (res) {
    return res
  })
}

export const uploadFile = (data, config) => {
  return Axios.post(BASE_PATH + '/stockexchange/DocUpload/uploadstep02/UploadFile', data, config).then(function (res) {
    return res
  })
}
export const removeUploadFile = (data) => {
  return Axios.post(BASE_PATH + '/stockexchange/DocUpload/uploadstep02/RemoveUploadFile', { 'uid': data }).then(function (res) {
    return res
  })
}
export const verifyValidCode = (data) => {
  return Axios.post(BASE_PATH + '/stockexchange/Common/VerifyValidCode', { 'ValidCode': data }).then(function (res) {
    return res
  })
}
