<template>
  <el-container class="main_section">
    <el-row class="main_section_body">
      <el-col :span='24' class="main_section_content">
        <el-header ref="header" v-if="!sba">{{$t("msg.Forms.UploadStep02.Title")}}</el-header>
        <el-main class="main">
          <!-- docUpload Component -->
          <doc-upload :formModel="formModel" ref="docUploadForm"></doc-upload>
        </el-main>
      </el-col>

      <el-col :span="24" class="main_section_button">
        <!-- Submit Button -->
        <el-row class="nextbtn">
          <el-col :span="6" :push="18">
            <el-button style='width:100%' type='danger' @click='submitForm' round :loading='isBtnLoading'>{{$t("msg.Common.Btn.Next")}}</el-button>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </el-container>
</template>

<script>
import docUpload from '@/views/sba/components/documentUpload/fFileUpload'
import { SubmitUpload02 } from '@/views/sba/apis/formPost'
import { loaduploadStep02Data } from '@/views/sba/apis/formLoad'

export default {
  components: {
    docUpload
  },
  data () {
    return {
      showExit: false,
      isBtnLoading: false,
      formModel: {},
      sba: false
    }
  },
  created () {
    const self = this
    loaduploadStep02Data().then((res) => {
      const result = self.parseResponse(res)
      if (result != null) {
        // Language Setting
        self.initLanguage(result.DocUploadModel.Language)
        self.$nextTick(() => {
          self.$refs['docUploadForm'].initFormModel(result)
        })
      }
    }).catch((error) => {
      console.log(error.message)
    })
  },
  mounted () {
    // set header
    this.initOmniUploadStep2(false)
    const self = this
    this.sba = this.isOmni()
    // set header
    // this.initOmniUploadStep2(false)

    window['closewebview'] = function () {
      self.showExit = true
    }
    // AA Tagging
    window.digitalData = this.mydigitalData()
    window.AAPageBottom()
  },
  methods: {
    onomniclose () {
      this.closewebview()
    },
    submitForm () {
      const self = this
      self.isBtnLoading = true
      const param = self.formModel
      SubmitUpload02(param).then((res) => {
        console.log(res)
        self.isBtnLoading = false
        if (res.data.Status === 6) {
          if (res.data.Data.toLowerCase() === 'uploadstep03') {
            self.triggerPageChange('/stockexchange/docupload/uploadstep03')
          } else if (res.data.Data.toLowerCase() === 'form10') {
            self.triggerPageChange('/stockexchange/form10')
          }
          self.$router.push({
            name: res.data.Data.toLowerCase(),
            query: { ...self.$route.query }
          })
        } else if (res.data.Status === 0) {
          return true
        } else {
          self.showMessage(self, res.data)
        }
      }).catch((error) => {
        self.isBtnLoading = false
        console.log(error)
      })
    }
  }
}
</script>

<style lang="less" scoped>
</style>