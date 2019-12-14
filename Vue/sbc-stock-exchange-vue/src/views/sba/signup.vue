<template>
  <div>
    <el-container class="main_section">
      <el-row class="main_section_body">
        <el-col :span='24' class="main_section_content">
          <el-header ref="header" v-if="!this.isOmni()">{{$t("msg.Forms.Signup.Title")}}</el-header>
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
            <el-col :span="6" :push="18">
              <el-button style="width:100%" type="danger" round @click="submitForm()" :loading="isBtnLoading">{{$t("msg.Common.Btn.Next")}}</el-button>
            </el-col>
          </el-row>
        </el-col>
      </el-row>

    </el-container>
  </div>
</template>

<script>
import { SubmitList } from '@/views/sba/apis/formPost'
import { loadPageData } from '@/views/sba/apis/formLoad'
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
    const self = this
    loadPageData('Signup').then((res) => {
      const result = self.parseResponse(res)
      if (result != null) {
        const element = 'signup'
        self.$nextTick(() => {
          self.$refs[element].initFormModel(result || {})
        })
      }
    }).catch((error) => {
      console.log(error.message)
    })
  },
  computed: {
  },
  mounted () {
  },
  methods: {
    // call the api methods to retrieve data from api or submit data to api
    submitForm () {
      const self = this
      const element = 'signup'
      this.$refs[element].formValided().then((valid) => {
        if (valid) {
          self.isBtnLoading = true
          const param = self.$refs[element].getFormModel()
          SubmitList('Signup', param, self)
        } else {
          return false
        }
      }).catch(() => {
        console.log('validation fail')
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
 <style scoped>
</style>