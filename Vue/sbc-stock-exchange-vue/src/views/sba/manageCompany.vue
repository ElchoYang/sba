<template>
  <div>
    <el-container class="main_section">
      <el-row class="main_section_body">
        <el-col :span='24' class="main_section_content">
          <el-main class="main">
            <!-- componment forms -->
            <div class="mainform">
              <myForm v-if="create" ref='form'></myForm>
              <companyList v-else ref='list'></companyList>
            </div>
          </el-main>
        </el-col>
        <el-col :span="24" class="main_section_button">
          <!-- Create -->         
          <el-row class="nextbtn">
            <el-col :span="24">
              <el-button v-if="!create" style='width:100%' type='danger' round @click='create=true'>{{$t("msg.Navigation.manageCompany.create")}}</el-button>
            </el-col>
          </el-row>          
          <!-- Submit Button -->
          <el-row class="nextbtn">
            <el-col :span="24" >
              <el-button v-if="create"  style='width:100%' type='danger' round @click='submitForm()'>{{$t("msg.Common.Btn.Save")}}</el-button>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
    </el-container>
  </div>
</template>



<script>

import { createComany } from '@/views/sba/apis/API'
import myForm from '@/views/sba/components/forms/companyform'
import companyList from '@/views/sba/components/company/list'

// import Axios from '@/assets/js/AxiosPlugin'
export default {
  components: {
    myForm,
    companyList
  },
  data () {
    return {
      formModel: {
      },
      create: false,
      showList: true
    }
  },
  created () {
  },
  mounted () {
  },
  methods: {
    // call the api methods to retrieve data from api or submit data to api
    submitForm () {
      const self = this
      const data = self.$refs['form'].getFormModel()
      console.log(data)
      createComany('form', data, self).then(function (res) {
        console.log(res)
        if (res.data === 0) { // response success
          self.$router.push({
            name: 'managecompany',
            query: {...self.$route.query}
          })
          self.showList = true
          self.create = false
        } else {
          self.showMessage(self, res)
        }
      })
        .catch(function (error) {
          self.isBtnLoading = false
          console.log(error.message)
        })
    }
  }
}
</script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="css" scoped>
</style>