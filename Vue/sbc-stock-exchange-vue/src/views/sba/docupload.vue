<template>
  <el-container class="main_section">
    <el-row class="main_section_body">
      <el-col :span='24' class="main_section_content">
        <el-header ref="header">{{$t("msg.Navigation.importdata.importExcel")}}</el-header>
        <el-main class="main">
          <!-- docUpload Component -->
          <doc-upload :formModel="formModel" ref="docUploadForm"></doc-upload>
          <!-- Download Files -->
          <div><a class="link" @click="download()">{{$t("msg.Navigation.importdata.click")}}</a></div>
        </el-main>
      </el-col>

      <el-col :span="24" class="main_section_button">
        <!-- Submit Button -->
        <el-row class="nextbtn">
          <el-col :span="12" :push="6">
            <el-button style='width:100%' type='danger' @click='submitForm' round :loading='isBtnLoading'>{{$t("msg.Common.Btn.OK")}}</el-button>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </el-container>
</template>

<script>
import docUpload from '@/views/sba/components/documentUpload/fFileUpload'
import { SubmitUpload02 } from '@/views/sba/apis/formPost'
import { downloadFile } from '@/views/sba/apis/API'

export default {
  components: {
    docUpload
  },
  data () {
    return {
      isBtnLoading: false,
      formModel: {},
      sba: false
    }
  },
  created () {
  },
  mounted () {
  },
  methods: {
    submitForm () {
      const self = this
      self.isBtnLoading = true
      const param = self.formModel
      SubmitUpload02(param).then((res) => {
        console.log(res)
        self.isBtnLoading = false
        if (res.data.Status === 6) {
          self.$router.push({
            name: res.data.Data.toLowerCase(),
            query: { ...self.$route.query }
          })
        } else {
          self.showMessage(self, res.data)
        }
      }).catch((error) => {
        self.isBtnLoading = false
        console.log(error)
      })
    },
    download () {
      downloadFile('stock_data.xlsx').then((res) => {
        console.log(res)
      }).catch((error) => {
        console.log(error)
      })
    },
    doChangeDown (data, fileName) {
      var a = document.createElement('a')
      var bstr = atob(data)
      var n = bstr.length
      var u8arr = new Uint8Array(n)
      while (n--) {
        u8arr[n] = bstr.charCodeAt(n)
      }
      var blob = new Blob([u8arr], { type: 'application/octet-stream' })
      console.log('blob')
      console.log(blob)
      var url = window.URL.createObjectURL(blob)
      console.log('URL:' + url)
      a.href = url
      a.download = fileName
      a.click()
      window.URL.revokeObjectURL(url)
    }
  }
}
</script>

<style lang="less" scoped>
.link{
  text-decoration: underline;
  cursor: pointer;
}
</style>