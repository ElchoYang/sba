import Axios from '@/assets/js/AxiosPlugin'
import {
  BASE_PATH
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
  Axios.post(BASE_PATH + '/stockexchange/' + param + '/submit', data).then(function (res) {
    return res
  }).then(function (res) {
    self.isBtnLoading = false
    if (res.data.Status === 6 || res.data.Status === 3) { // MVC response success or step wrong
      // form08 , docupload 02, trigger sba pageChange
      if (param === 'form08') {
        self.triggerPageChange('/stockexchange/' + res.data.Data.toLowerCase())
      }
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

export const SubmitListForlogin = (data) => {
  return Axios.post(BASE_PATH + '/stockexchange/login/submit', data).then(function (res) {
    return res
  })
}

export const SubmitWithCallBack = (param, data) => {
  return Axios.post(BASE_PATH + '/stockexchange/' + param + '/submit', data).then(function (res) {
    return res
  })
}
export const ToSubmitDoc = () => {
  return Axios.post(BASE_PATH + '/stockexchange/form09/ToSubmitDoc').then(function (res) {
    return res
  })
}

export const SubmitUpload02 = (data) => {
  return Axios.post(BASE_PATH + '/stockexchange/DocUpload/uploadstep02/submit', data).then(function (res) {
    return res
  })
}
