import Axios from '@/assets/js/AxiosPlugin'
import {BASE_PATH} from '@/views/sba/apis/baseSetup'

export const signUpForm = (param, data, self) => {
  Axios.post(BASE_PATH + '/stockexchange/user/signup', data).then(function (res) {
    return res
  }).then(function (res) {
    console.log(res)
    if (res.data === 0) { // response success
      self.$router.push({
        name: 'login',
        query: {...self.$route.query}
      })
    } else {
      self.showMessage(self, res)
    }
  }).catch(function (error) {
    self.isBtnLoading = false
    console.log(error.message)
  })
}
export const login = (param, data, self) => {
  Axios.post(BASE_PATH + '/stockexchange/user/login', data).then(function (res) {
    return res
  }).then(function (res) {
    console.log(res)
    if (res.data.userType === 'user') {
      document.getElementById('userNav').style.display = 'flex'
      document.getElementById('login').style.display = 'none'
      document.getElementById('logout').style.display = 'block'
      document.getElementById('signup').style.display = 'none'
      self.$router.push({
        name: 'compareCompany',
        query: {...self.$route.query}
      })
    } else if (res.data.userType === 'Admin') {
      document.getElementById('adminNav').style.display = 'flex'
      document.getElementById('login').style.display = 'none'
      document.getElementById('logout').style.display = 'block'
      document.getElementById('signup').style.display = 'none'
      self.$router.push({
        name: 'docupload',
        query: {...self.$route.query}
      })
    } else {
      self.showMessage(self, res)
    }
  }).catch(function (error) {
    self.isBtnLoading = false
    console.log(error.message)
  })
}

export const logout = (self) => {
  Axios.post(BASE_PATH + '/stockexchange/user/logout').then(function (res) {
    return res
  }).then(function (res) {
    console.log(res)
    self.$router.push({
      name: 'logout'
    })
    document.getElementById('userNav').style.display = 'none'
    document.getElementById('adminNav').style.display = 'none'
  }).catch(function (error) {
    self.isBtnLoading = false
    console.log(error.message)
  })
}

// Document Upload
export const uploadFile = (data, config) => {
  return Axios.post(BASE_PATH + '/stockexchange/stockPrice/UploadFile', data, config).then(function (res) {
    return res
  })
}

// Download Upload
export const downloadFile = (data) => {
  return Axios.post(BASE_PATH + '/stockexchange/stockPrice/download', { 'id': data }).then(function (res) {
    return res
  })
}
