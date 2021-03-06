import Axios from '@/assets/js/AxiosPlugin'
import {
  API_URL_USER
} from '@/views/sba/apis/baseSetup'

// get URL param
function getURLParam (param) {
  var reg = new RegExp('(^|&)' + param + '=([^&]*)(&|$)')
  var paramVal = window.location.search.substr(1).match(reg)
  if (paramVal != null) {
    console.log(paramVal)
    return paramVal[2]
  } else {
    return null
  }
}
export const myTestSubmit = (page, self) => {
  let pageQuery = { // define URL param here
    lang: getURLParam('lang') || 'en-us' // get language
  }
  self.$router.push({
    name: page,
    query: pageQuery
  })
}
export const SubmitList = (param, data, self) => {
  Axios.post(API_URL_USER + '/stockexchange/' + param + '/submit', data).then(function (res) {
    return res
  }).then(function (res) {
    self.isBtnLoading = false
    if (res.data.Status === 6 || res.data.Status === 3) { // response success
      self.$router.push({
        name: res.data.Data.toLowerCase(),
        query: {...self.$route.query}
      })
    } else if (res.data.Status === 0) {
      return true
    } else {
      self.showMessage(self, res.data)
    }
  }).catch(function (error) {
    self.isBtnLoading = false
    console.log(error.message)
  })
}

export const SubmitWithCallBack = (param, data) => {
  return Axios.post(API_URL_USER + '/stockexchange/' + param + '/submit', data).then(function (res) {
    return res
  })
}
export const ToSubmitDoc = () => {
  return Axios.post(API_URL_USER + '/stockexchange/form09/ToSubmitDoc').then(function (res) {
    return res
  })
}

export const SubmitUpload02 = (data) => {
  return Axios.post(API_URL_USER + '/stockexchange/DocUpload/uploadstep02/submit', data).then(function (res) {
    return res
  })
}
