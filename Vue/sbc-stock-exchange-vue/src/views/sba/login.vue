<template>
  <div>
    <el-container class="main_section">
      <el-row class="main_section_body">
        <el-col :span='24' class="main_section_content">
          <el-header ref="header">{{$t("msg.Navigation.login")}}</el-header>
          <el-main class="main">
            <!-- componment forms -->
            <div class="mainform">
              <loginform ref='loginform'></loginform>
            </div>
          </el-main>
        </el-col>
        <el-col :span="24" class="main_section_button">
          <!-- Submit Button -->
          <el-row class="nextbtn">
            <el-col :span="12" :push="6">
              <el-button style='width:100%' type='danger' round @click='submitForm()'>{{$t("msg.Common.Btn.Login")}}</el-button>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
    </el-container>
  </div>
</template>
  
<script>

import { RequestAllUser } from '@/views/sba/apis/formLoad'
import { login } from '@/views/sba/apis/API'
import loginform from '@/views/sba/components/forms/loginform'
// import Axios from '@/assets/js/AxiosPlugin'
export default {
  components: {
    loginform
  },
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
  methods: {
    getAA () {
      RequestAllUser().then((res) => {
        console.log(res)
      })
    },
    // call the api methods to retrieve data from api or submit data to api
    submitForm () {
      const self = this
      const data = self.$refs['loginform'].getFormModel()
      console.log(data)
      login('loginform', data, self)
    }
  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="css" scoped>
</style>