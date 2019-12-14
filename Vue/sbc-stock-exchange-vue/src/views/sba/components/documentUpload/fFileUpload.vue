<template>
  <el-form label-position="top" ref='form'>
    <el-form-item>
      <div id="fFileUpload">
        <!-- Identification Proof ################################################# -->
        <div class="field">
          <div v-if="ismissinghkid">
            <span class="fieldName">{{$t("msg.component.FileUpload.IdentityProof")}}
              <span class="imageIcon" @click.stop.self="showIdentity=true"></span>
              <!-- <span class="linkText" @click.stop.self="showIdentity=true">{{$t("msg.Common.LinkText.LearnMore")}}</span> -->
            </span>
            <sba-file-upload :ddl="idProof" :fileLimit="fileLimit" :tips='$t("msg.component.FileUpload.IdentityTips")'></sba-file-upload>
          </div>
          <div v-else>
            <span class="fieldName">{{$t("msg.component.FileUpload.IdentityProof")}}
              <el-button class="logoIcon" type="info" circle></el-button>
            </span>
          </div>
        </div>
      </div>
    </el-form-item>

    <!-- IdentityProof Dialog -->
    <el-dialog :visible.sync="showIdentity" class="saveDialog showInfo" center>
      <div slot="title" class="dialog-title">
        {{$t("msg.component.FileUpload.IdentityProof")}}
      </div>
      <div class="dialog-body">
        <el-row class="dialogInner-content">
          <el-col>
            <p>
              {{$t("msg.component.FileUpload.IdentityRemark")}}
            </p>
          </el-col>
        </el-row>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-row :gutter="20">
          <el-col :span="24" class="dialog-button">
            <el-button style="width:100%" type="danger" round @click.stop.self="showIdentity=false">{{$t("msg.Common.Btn.Confirm")}}</el-button>
          </el-col>
        </el-row>
      </div>
    </el-dialog>

 

  </el-form>
</template>

<script type="text/javascript">
import dbsFileUpload from '@/views/sba/components/toolbox/sba-fileUpload'

export default {
  components: {
    dbsFileUpload: dbsFileUpload
  },
  data () {
    return {
      fileLimit: {
        maxFileSize: 0,
        maxFileCount: 0,
        currentCount: 0
      },
      showIdentity: false,
      showHomeAddr: false,
      showIncome: false,
      showOthers: false,
      ismissinghkid: false,
      ismissingaddress: false,
      ismissingincome: false,
      idProof: {},
      homeAddr: {},
      incomeProof: {},
      others: {}
    }
  },
  created () {
    const self = this
    self.UrlHanding()

    // ## const Start #########################################################
    self.idProof = {
      id: 'idProof',
      name: 'msg.component.FileUpload.IdentityProof',
      options: [
        { Text: 'msg.component.FileUpload.HKIDp', Value: '1HKID' },
        { Text: 'msg.component.FileUpload.Passportp', Value: '1Passport' }
      ]
    }
    self.homeAddr = {
      id: 'homeAddr',
      name: 'msg.component.FileUpload.HomeAddrProof',
      options: [
        { Text: 'msg.component.FileUpload.Homep', Value: '2RAP1' },
        { Text: 'msg.component.FileUpload.Permanentp', Value: '2RAP2' }
      ]
    }
    self.incomeProof = {
      id: 'incomeProof',
      name: 'msg.component.FileUpload.IncomeProof',
      options: [
        { Text: 'msg.component.FileUpload.Incomep', Value: '3IP' },
        { Text: 'msg.component.FileUpload.Assetp', Value: '3AP' }
      ]
    }
    self.others = {
      id: 'othersProof',
      name: 'msg.component.FileUpload.Others',
      options: [
        { Text: 'msg.component.FileUpload.Pleasep', Value: '' },
        { Text: 'msg.component.FileUpload.HKIDp', Value: '4Others1' },
        { Text: 'msg.component.FileUpload.Passportp', Value: '4Others2' },
        { Text: 'msg.component.FileUpload.Homep', Value: '4Others3' },
        { Text: 'msg.component.FileUpload.Permanentp', Value: '4Others4' },
        { Text: 'msg.component.FileUpload.Incomep', Value: '4Others5' },
        { Text: 'msg.component.FileUpload.Assetp', Value: '4Others6' },
        { Text: 'msg.component.FileUpload.Studentp', Value: '4Std' },
        { Text: 'msg.component.FileUpload.Directp', Value: '4DSD' },
        { Text: 'msg.component.FileUpload.Others', Value: '4Others7' }
      ]
    }
    // ## const End #########################################################
  },
  mounted () {
  },
  methods: {
    initFormModel (model) {
      this.fileLimit.maxFileSize = model.MaxFileSize
      this.fileLimit.maxFileCount = model.MaxFileCount
    },
    UrlHanding () {
      const self = this
      const y = 'y'
      let missinghkid = self.getUrlParam('missinghkid')
      let missingaddress = self.getUrlParam('missingaddress')
      let missingincome = self.getUrlParam('missingincome')

      if (missinghkid === null && missingaddress === null && missingincome === null) {
        self.ismissinghkid = true
        self.ismissingaddress = true
        self.ismissingincome = true
      } else {
        self.ismissinghkid = missinghkid === y
        self.ismissingaddress = missingaddress === y
        self.ismissingincome = missingincome === y
      }
      // if (missinghkid === n && missingaddress === n && missingincome === n) {
      //   self.ismissinghkid = false
      //   self.ismissingaddress = false
      //   self.ismissingincome = false
      // } else if (missinghkid !== y && missingaddress !== y && missingincome !== y) {
      //   self.ismissinghkid = true
      //   self.ismissingaddress = true
      //   self.ismissingincome = true
      // } else if (missinghkid === y && missingaddress === y) {
      //   self.ismissinghkid = true
      //   self.ismissingaddress = true
      //   self.ismissingincome = false
      // } else if (missinghkid === y && missingincome === y) {
      //   self.ismissinghkid = true
      //   self.ismissingaddress = false
      //   self.ismissingincome = true
      // } else if (missingaddress === y && missingincome === y) {
      //   self.ismissinghkid = false
      //   self.ismissingaddress = true
      //   self.ismissingincome = true
      // } else if (missinghkid === y) {
      //   self.ismissinghkid = true
      //   self.ismissingaddress = false
      //   self.ismissingincome = false
      // } else if (missingaddress === y) {
      //   self.ismissinghkid = false
      //   self.ismissingaddress = true
      //   self.ismissingincome = false
      // } else if (missingincome === y) {
      //   self.ismissinghkid = false
      //   self.ismissingaddress = false
      //   self.ismissingincome = true
      // }
    }
  }
}
</script>

<style lang="less" scoped>

@greyText: #656565;
#fFileUpload {
  padding: 0;
}
.logoIcon {
  cursor: auto;
  border: none;
  float: right;
}
#fFileUpload ul.subtitle {
  padding: 0;
  line-height: 1rem;
  list-style: none;
  margin:0;
}

#fFileUpload li.desc {
  color: @greyText;
  font-size: 12px;
}

#fFileUpload div.field {
  margin-top: 24px;
}

#fFileUpload .fieldName {
  font-size: 1rem;
  line-height: 1rem;
  font-weight: bold;
  display: inline-block;
  width: 100%;
}
</style>
