<template>
  <div>
    <el-container class="main_section">
      <el-row class="main_section_body">
        <el-col :span='24' class="main_section_content">
          <el-header ref="header">{{$t("msg.Navigation.signup")}}</el-header>
          <el-main class="main">
            <!-- componment signup -->
            <div class="mainform">
              <signup ref='signup'></signup>
            </div>

          </el-main>
        </el-col>
        <el-col :span="24" class="main_section_button">
          <!-- Submit Button -->
          <el-row class="nextbtn">
            <el-col :span="12" :push="6">
              <el-button style="width:100%" type="danger" round @click="submitForm()" :loading="isBtnLoading">{{$t("msg.Common.Btn.SignUp")}}</el-button>
            </el-col>
          </el-row>
        </el-col>
      </el-row>

    </el-container>
  </div>
</template>

<script>
import { signUpForm } from '@/views/sba/apis/API'
import signup from '@/views/sba/components/forms/signup'

export default {
  components: {
    signup
  },
  data () {
    return {
      showSaved: false,
      showExit: false,
      isBtnLoading: false,
      isShortForm: false,
      tempData: Object,
      IsExistingEmail: false,
      rules: {}
    }
  },
  created () {
  },
  computed: {
  },
  mounted () {
  },
  methods: {
    // call the api methods to retrieve data from api or submit data to api
    submitForm () {
      const self = this
      const data = self.$refs['signup'].getFormModel()
      console.log(data)
      signUpForm('Signup', data, self)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
 <style scoped>
</style>