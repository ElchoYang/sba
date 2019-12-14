export default {
  methods: {
    addClass: function (obj, className) {
      if (obj !== undefined && className !== undefined && className !== '') {
        obj.classList.add(className)
      }
      return obj
    },
    removeClass: function (obj, className) {
      var arr
      if (obj !== undefined && className !== undefined && className !== '') {
        arr = obj.classList
        for (let index in arr) {
          if (arr[index].toUpperCase() === className.toUpperCase()) {
            obj.classList.remove(index)
          }
        }
      }
      return obj
    },
    hasClass: function (obj, className) {
      if (obj !== undefined && className !== undefined && className !== '') {
        if (obj.classList.indexof(className) > -1) {
          return true
        }
      } else {
        return false
      }
    },
    findParentByClassName: function (obj, strClassName) {
      var myParent = obj
      var hasFound = false;

      (function () {
        while (!hasFound) {
          chkParentClassName()
        }
      }())

      function chkParentClassName () {
        myParent = myParent.parentNode
        if (myParent.classList.contains(strClassName) || myParent.nodeName === 'body') {
          hasFound = true
        } else {
          hasFound = false
        }
      }

      return myParent
    }
  }
}
