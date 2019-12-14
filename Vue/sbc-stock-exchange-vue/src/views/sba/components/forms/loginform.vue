<template>
    <el-form label-position="top" :model='formModel' :rules='rules' ref='form'>
      
        <el-form-item prop='UserName' :label='$t("msg.Forms.username")'>
            <el-input v-model='formModel.UserName' :placeholder='$t("msg.Common.PlcH.Enter")' :maxlength="25"></el-input>
        </el-form-item>
        <el-form-item prop='Password' :label='$t("msg.Forms.password")'>
            <el-input type="password" v-model='formModel.Password' :placeholder='$t("msg.Common.PlcH.Enter")' :maxlength="25"></el-input>
        </el-form-item>
    </el-form>
</template>

<script>
export default {
  data () {
    return {
      formModel: {
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
  }
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="css" scoped>
</style>