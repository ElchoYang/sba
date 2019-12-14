import Axios from '@/assets/js/AxiosPlugin'
import {BASE_PATH} from '@/views/sba/apis/baseSetup'

export const getSignatureImage = () => {
  return Axios.post(BASE_PATH + '/stockexchange/form07/getSignatureImage').then(function (res) {
    return res
  })
}
