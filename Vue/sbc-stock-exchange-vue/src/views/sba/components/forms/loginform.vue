<template>
    <el-form label-position="top" :model='formModel' ref='form'>
      
        <el-form-item prop='userName' :label='$t("msg.Forms.username")'>
            <el-input v-model='formModel.userName' :placeholder='$t("msg.Common.PlcH.Enter")' :maxlength="25"></el-input>
        </el-form-item>
        <el-form-item prop='password' :label='$t("msg.Forms.password")'>
            <el-input type="password" v-model='formModel.password' :placeholder='$t("msg.Common.PlcH.Enter")' :maxlength="25"></el-input>
        </el-form-item>
    </el-form>
</template>

<script>
export default {
  data () {
    return {
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
  mounted () {
  },
  watch: {
  },
  methods: {
    getFormModel () {
      return this.formModel
    }
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="css" scoped>
</style>