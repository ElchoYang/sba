<template id="sba-pwdBox" >
  <div class="wrap sba-pwdBox">
    <span class="lblPwdBoxName">{{prefix}} -</span>
    <!-- tel: on the top and is transparent -->
    <label class="pwd-frame">
      <input ref="txtOTP" type="tel" name="txtOTP" class="chkMandatory" pattern="[0-9]*" inputmode="tel" min="000000" max="999999" maxlength=6 style="-webkit-text-security: disc;" autofocus autocomplete="off" @input.stop="pwd_focus($event)" @blur.stop="pwd_blur()" @focus.stop="pwd_focus($event)" />
      <div class="shape">
        <span v-for="(pwd, index) in passwords" :key="index" :class="['box', {'focus': pwd.isFocus},{'point':pwd.hasValue}]">{{pwd.value}}</span>
      </div>
    </label>
  </div>
</template>

<script type="text/javascript">
export default {
  props: {
    prefix: ''
  },
  name: 'dbspwdbox',
  template: '#sba-pwdBox',
  data: () => ({
    sent: false,
    passwords: [{
      isFocus: false,
      hasValue: false
    }, {
      isFocus: false,
      hasValue: false
    }, {
      isFocus: false,
      hasValue: false
    }, {
      isFocus: false,
      hasValue: false
    }, {
      isFocus: false,
      hasValue: false
    }, {
      isFocus: false,
      hasValue: false
    }]
  }),
  methods: {
    pwd_focus (event) {
      var me = this
      var input = event.target
      var index = input.value.length - 1
      if (index === -1) {
        index = 0
      }

      me.$data.passwords.map((val, id, self) => {
        self[id].isFocus = false
        if (id <= index) {
          self[id].hasValue = true
        } else {
          self[id].hasValue = false
        }
      })

      me.$data.passwords[index].isFocus = true

      if (input.value.length === 0) {
        me.$data.passwords[index].hasValue = false
      } else {
        if (!me.$data.sent) {
          me.pwd_change(event)
        }
      }
    },
    pwd_blur () {
      var me = this
      me.$data.passwords.map((val, id, self) => {
        self[id].isFocus = false
      })
    },
    pwd_change (event) {
      const value = event.target.value
      if (value !== null && typeof value !== 'undefined') {
        if (value.length === 6) {
          console.log('otp is ' + value)
          this.$data.sent = true
          this.$emit('otpVerify', value)
        }
      }
    },
    pwd_clear () {
      this.$data.passwords.map((val, id, self) => {
        self[id].isFocus = false
        self[id].hasValue = false
      })

      this.$data.sent = false
      this.$refs.txtOTP.value = ''
      this.$data.passwords[0].isFocus = true
    }
  }
}
</script>

<style lang="less" scoped>
@import (once) "./../../assets/css/sba-general.less";
@boxWidth: 44px;
@boxHeight: 36.7px;
@border-size: 1px;
.sba-pwdBox {
  position: relative;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.sba-pwdBox label.pwd-frame {
  display: block;
}

.sba-pwdBox input {
  position: absolute;
  left: -10000px;
  border: 0;
  color: transparent;
  background-color: transparent;
}

.sba-pwdBox .shape .box {
  display: inline-block;
  width: @boxWidth;
  height: @boxHeight;
  line-height: @boxHeight - 3px;
  border: @border-size solid @grey;
  margin-left: -@border-size;
  text-align: center;
}

.sba-pwdBox .shape .box:first-child {
  border-top-left-radius: @border-radius;
  border-bottom-left-radius: @border-radius;
  margin-left: 0;
}

.sba-pwdBox .shape .box:last-child {
  border-top-right-radius: @border-radius;
  border-bottom-right-radius: @border-radius;
}

.sba-pwdBox .shape .box.focus {
  border-color: @blue;
  box-shadow: @blue-shadow;
}

.sba-pwdBox .shape .box.point {
  position: relative;
  display: inline-block;
  // position: relaative;
  // display: inline-flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  top:-10.7px;
  //  top: -@border-size;
}

.sba-pwdBox .shape .box.point:before {
  content: "\2022";
  font-size: 2rem;
  // position: absolute;
}

.lblPwdBoxName {
  margin-right: 10px;
}
</style>
