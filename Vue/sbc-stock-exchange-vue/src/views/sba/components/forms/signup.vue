<template>
  <div>
    <el-form label-position="top" :model="formModel" ref="form">
        
        <el-form-item prop='userName' :label='$t("msg.Forms.username")'>
            <el-input v-model='formModel.userName' :placeholder='$t("msg.Common.PlcH.Enter")' :maxlength="25"></el-input>
        </el-form-item>
        <el-form-item prop='password' :label='$t("msg.Forms.password")'>
            <el-input type="password" v-model='formModel.password' :placeholder='$t("msg.Common.PlcH.Enter")' :maxlength="25"></el-input>
        </el-form-item>

      <el-form-item prop="mobile" :label='$t("msg.Forms.mobile")'>
        <el-input v-model="formModel.mobile" :placeholder='$t("msg.Common.PlcH.Enter")' :maxlength="8" type="tel"></el-input>
      </el-form-item>

      <el-form-item prop="email" :label='$t("msg.Forms.email")'>
        <el-input v-model="formModel.email" :placeholder='$t("msg.Common.PlcH.Enter")' :maxlength="40"></el-input>
      </el-form-item>

    </el-form>
  </div>
</template>

<script>

export default {
  components: {
  },
  props: {
  },
  data () {
    return {
      visible: false,
      formModel: {
        userName: '',
        password: '',
        mobile: '',
        email: ''
      }
    }
  },
  created () {
  },
  mounted () {},
  methods: {
    getFormModel () {
      return this.formModel
    }
  }
}
</script>



<style lang="css" scoped>
.email {
  width: 100%;
}
</style>