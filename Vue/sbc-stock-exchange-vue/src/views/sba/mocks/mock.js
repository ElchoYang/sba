import Mock from 'mockjs'
import '@/views/sba/mocks/formPost'
import '@/views/sba/mocks/formLoad'
import '@/views/sba/mocks/getResources'
import '@/views/sba/mocks/external'

Mock.mock('/stockexchange/login/GeneralValidCode', function () {
  console.log('GeneralValidCode')
  return {
    Status: 0,
    Data: '2345'
  }
})
