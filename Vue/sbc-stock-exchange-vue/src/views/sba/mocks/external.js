import Mock from 'mockjs'

Mock.mock('/stockexchange/Externalization/GetRemark', function () {
  console.log('GetRemark')
  return 'remark string'
})
