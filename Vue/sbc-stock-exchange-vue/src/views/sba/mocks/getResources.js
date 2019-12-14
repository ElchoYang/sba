import Mock from 'mockjs'

Mock.mock('/stockexchange/Resources/GetParamResources?type=District', function () {
  console.log('GetParamResources')
  return {
    Data: [{
      Value: '0',
      Text: 'Please select'
    }, {
      Value: '0',
      Text: '=============='
    }, {
      Value: '0',
      Text: 'Hong Kong'
    }, {
      Value: '0',
      Text: '=============='
    }, {
      Value: '1001',
      Text: 'Aberdeen'
    }, {
      Value: '1002',
      Text: 'Admiralty'
    }, {
      Value: '1003',
      Text: 'Ap Lei Chau'
    }],
    Message: '',
    Status: 0
  }
})

Mock.mock('/stockexchange/Resources/GetParamResources?type=Nationality', function () {
  console.log('GetParamResources')
  return {
    Data: [
      {
        Value: 'CN',
        Text: 'China'
      },
      {
        Value: 'HK',
        Text: 'HK'
      }
    ]
  }
})

Mock.mock('/stockexchange/Resources/GetAlertMessages', function (param) {
  console.log('GetAlertMessages')
  return {
    Status: 0,
    Data: [{
      Value: '10701',
      Text: 'Please select your Sex.'
    }, {
      Value: '10702',
      Text: 'Please input your English Name.'
    }, {
      Value: '10703',
      Text: 'Invalid input of your English Name. Please amend your input.'
    }, {
      Value: '10704',
      Text: 'Please input your Mobile Phone Number.'
    }]
  }
})
