<template>
  <el-container class="main_section">
    <el-row class="main_section_body">
      <el-col :span='24' class="main_section_content">
        <el-header ref="header" v-if="!isSummary">{{$t("msg.Navigation.importdata.importExcel")}}</el-header>
        <el-header ref="header" v-else>{{$t("msg.Navigation.importdata.summary")}}</el-header>
        <el-main class="main">
          <!-- docUpload Component -->
          <!-- <doc-upload :formModel="formModel" ref="docUploadForm"></doc-upload> -->
          <el-upload 
            class="upload-demo"
            ref="upload"
            :action="uploadURL"
            :on-success="handleSuccess"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">Select File</el-button>
            <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">Upload</el-button>
            <!-- <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
            </el-upload>
              <el-table v-if="isSummary"
              :data="companySummary"
              style="width: 100%"
              >
              <el-table-column
                prop="company"
                label="Company"
                width="180">
              </el-table-column>
              <el-table-column
                prop="stockExchange"
                label="Stock Exchange"
                width="180">
              </el-table-column>
              <el-table-column
                prop="recordNumber"
                label="RecordNumber">
              </el-table-column>            
              <el-table-column
                prop="formDate"
                label="formDate"
                >
              </el-table-column>    
              <el-table-column
                prop="toDate"
                label="toDate">
              </el-table-column>          
            </el-table>
        </el-main>
         <!-- Download Files -->
         
      </el-col>
  
      <!-- Submit Button -->
      <el-col :span="24" class="main_section_button">
        <el-row class="nextbtn">
          <el-col :span="24">
            <div><a class="link" target="_blank" :href="downloadURL">{{$t("msg.Navigation.importdata.click")}}</a></div>
            <!-- <el-button style='width:100%' type='danger' @click='submitForm' round :loading='isBtnLoading'>{{$t("msg.Common.Btn.OK")}}</el-button> -->
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </el-container>
</template>

<script>
import docUpload from '@/views/sba/components/documentUpload/fFileUpload'
// import { downloadFile } from '@/views/sba/apis/API'
import { API_URL_USER } from '@/views/sba/apis/baseSetup'

export default {
  components: {
    docUpload
  },
  data () {
    return {
      isBtnLoading: false,
      formModel: {},
      sba: false,
      uploadURL: '',
      downloadURL: '',
      companySummary: [],
      isSummary: false
    }
  },
  created () {
    this.uploadURL = API_URL_USER + '/stockexchange/stockPrice/UploadFile'
    this.downloadURL = 'http://localhost:8200/stockexchange/stockPrice/download/stock_data.xlsx'
  },
  mounted () {
  },
  methods: {
    submitUpload () {
      this.$refs.upload.submit()
    },
    handleRemove (file, fileList) {
      console.log(file, fileList)
    },
    handlePreview (file) {
      console.log(file)
    },
    handleSuccess (data) {
      console.log(data)
      this.companySummary = data
      this.isSummary = true
      this.$refs.upload.clearFiles()
    },
    dataFormat (row, column, cellValue, index) {
      console.log(row)
      console.log(column)
      console.log(cellValue)
      console.log(index)
    },
    download () {
      window.location.href = API_URL_USER + '/stockexchange/stockPrice/download?id=stock_data.xlsx'
      // downloadFile('stock_data.xlsx').then((res) => {
      //   console.log(res)
      // }).catch((error) => {
      //   console.log(error)
      // })
    }
  }
}
</script>

<style lang="less" scoped>
.link{
  text-decoration: underline;
  cursor: pointer;
}
.main{
  text-align: center;
}
</style>