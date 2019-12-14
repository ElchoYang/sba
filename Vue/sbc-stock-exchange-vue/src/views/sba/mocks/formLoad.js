import Mock from 'mockjs'

Mock.mock('/stockexchange/login/LoadPageData', function (param) {
  console.log('login LoadPageData')
  return {
    Status: 0,
    Data: {
      IdentityInfo:
      {
        IDType: 'HKID',
        HKIDNo: 'O0000001',
        Passport: '999999',
        Nationality: 'China',
        Surname: 'sba',
        GivenName: 'Iconic',
        DateOfBirth: '/date(5555555555)/',
        Gender: '2'
      },
      Language: 'ZH-HK'
    },
    Message: ''
  }
})
