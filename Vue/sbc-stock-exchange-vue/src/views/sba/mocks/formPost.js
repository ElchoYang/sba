import Mock from 'mockjs'

// submit
Mock.mock('/stockexchange/login/submit', function (param) {
  console.log('mock create Identity')
  return {
    Status: 0,
    Data: {
      MobileTel: '3',
      Email: '3',
      EducationLevel: '3',
      HasDbsBankAccount: '1',
      ResidentialAddress: {
        Room: '3',
        Floor: '3',
        Block: '3',
        Building: '3',
        Street: '3',
        District: '3'
      }
    }
  }
})
