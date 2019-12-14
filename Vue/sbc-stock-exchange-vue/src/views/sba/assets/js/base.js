export const pages = {
  adminBar: [
    {
      title: 'msg.Navigation.importdata.title',
      form: 'importdata'
    },
    {
      title: 'msg.Navigation.manageCompany.title',
      form: 'manageCompany'
    },
    {
      title: 'msg.Navigation.manageExchange.title',
      form: 'manageExchange'
    },
    {
      title: 'msg.Navigation.updateIpoDetails.title',
      form: 'updateipo'
    }
  ],
  userBar: [
    {
      title: 'msg.Navigation.IPOs.title',
      form: 'ipo'
    },
    {
      title: 'msg.Navigation.compareCompany.title',
      form: 'compareCompany'
    },
    {
      title: 'msg.Navigation.compareSectors.title',
      form: 'compareSectors'
    },
    {
      title: 'msg.Navigation.other.title',
      form: 'other'
    }
  ]
}

export default function install (Vue, options) {
  // get parameter from url
  Vue.prototype.getUrlParam = (param) => {
    var reg = new RegExp('(^|&)' + param + '=([^&]*)(&|$)')
    var paramVal = window.location.search.substr(1).toLowerCase().match(reg)
    if (paramVal != null) {
      return paramVal[2]
    } else {
      return null
    }
  }
  Vue.prototype.parseResponse = (res) => {
    let msg = ''
    switch (res.data.Status) {
      case 0: // Success
        // do nothing
        break
      case 1: // Fail
        msg = 'Fail'
        break
      case 2: // DBFail
        msg = 'DBFail'
        break
      case 4: // Timeout
        msg = 'Timeout'
        break
      case 9: // ServerError
        msg = 'ServerError'
        break
    }
    // success
    if (res.data.Status === 0 || res.data.Status === 3 || res.data.Status === 10) {
      return res.data.Data
    } else {
      Vue.prototype.$message({
        message: res.data.Message || msg,
        type: 'error'
      })
      return null
    }
  }
  Vue.prototype.currentForm = (res) => {
    let path = window.location.pathname.split('/')
    return path[path.length - 1].toLowerCase()
  }
  Vue.prototype.adminNav = function () {
    return pages.adminBar
  }
  Vue.prototype.userNav = function () {
    return pages.userBar
  }
}
