<template id="fileUpload">
  <div class="fileUpload">
    <div class="selectArea">
      <span class="btnUpload" @click="btnUpload_click($event)">{{$t("msg.Common.Btn.Upload")}}</span>
    </div>
    <div class="uploadGroup">
      <div v-for="(item ,index) in allFileList" :key='index'>
        <div class="selectText">{{$t(item.id)}}</div>
        <transition-group tag="ul" :class="[ 'el-upload-list', 'el-upload-list--picture-card']" name="el-list">
          <li v-for="(file,index2) in item.files" :key='index2' :class="['el-upload-list__item']">
            <img class="el-upload-list__item-thumbnail img-opacity" :src="file.url" :alt='file.name'>
           
            <i class="el-icon-circle-close btn-close" @click="close()"></i>
            <el-progress v-if="file.status === 'uploading'" type="circle" :stroke-width="6" :percentage="parsePercentage(file.percentage)">
            </el-progress>
          </li>
        </transition-group>
      </div>
    </div>
    <el-upload ref="elUpload" action="#" list-type="picture-card" name="file" :on-preview="handlePictureCardPreview" :on-change="handleChange" :accept="acceptList" :limit="6" :auto-upload="false" :show-file-list="false">
      <i class="el-icon-plus"></i>
    </el-upload>
  </div>
</template>

<script type="text/javascript">
import sbaGeneralJs from './../../assets/js/sba-general.js'
import { uploadFile } from '@/views/sba/apis/API'
export default {
  template: '#fileUpload',
  components: {},
  data: () => ({
    ddlSelectedText: '',
    ddlSelectedValue: '',
    acceptList: '.XLSX,.XLS',
    allFileList: [],
    uploadInput: {}
  }),
  props: {
  },
  methods: {
    parsePercentage (val) {
      return parseInt(val, 10)
    },
    handlePictureCardPreview (file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handleChange (file, fileList) {
      const self = this
      console.log('handleChange:')
      console.log(file, fileList)
      const extension = file.name.substring(file.name.lastIndexOf('.')).toUpperCase()
      const extensions = self.acceptList.split(',')
      console.log('extersion: ' + extension)
      console.log('extersion: ' + extensions)
      const isValidType = extensions.indexOf(extension) >= 0
      if (!isValidType) {
        self.$message({
          message: self.$i18n.t('msg.component.FileUpload.InvalidFileFormat'),
          type: 'warning',
          center: true
        })
        self.$refs['elUpload'].uploadFiles = []
        return
      }

      if (file.size <= 0) {
        self.$message({
          message: self.$i18n.t('msg.component.FileUpload.FileSizeZero'),
          type: 'warning',
          center: true
        })
        self.$refs['elUpload'].uploadFiles = []
        return
      }

      self.allFileList = []
      self.allFileList.push({
        id: file.name,
        files: [file]
      })

      file.status = 'uploading'
      let config = {
        disableLoading: true,
        headers: { 'Content-Type': 'multipart/form-data' },
        onUploadProgress: function (e) {
          if (e.total > 0) {
            file.percentage = Math.round(e.loaded / e.total * 100)
          }
        }
      }
      let formData = new FormData()
      formData.append('file', file.raw, file.name)
      formData.append('uid', file.uid)
      uploadFile(formData, config).then((res) => {
        console.log(res)
        if (res.data.Status === 0) {
          file.Status = 'ready'
        } else {
          self.showMessage(self, res.data)
        }
      }).catch((error) => {
        console.log(error.message)
      })

      self.$refs['elUpload'].uploadFiles = []
    },
    btnUpload_click (event) {
      const self = this
      let me = event.target
      let myParent = sbaGeneralJs.methods.findParentByClassName(me, 'fileUpload')
      self.uploadInput = myParent.getElementsByClassName('el-upload__input')[0]
      self.uploadInput.click()
    },
    close () {
      this.allFileList = []
    }
  },
  mounted: function () {
  }
}
</script>

<style lang="less" >
@btnHeight: 32px;
@btnBackColor: #ff3a30;
@picBorderColor: #c7cfd5;

.fileUpload .selectArea {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.fileUpload span.btnUpload {
  width: 88px;
  height: @btnHeight;
  line-height: @btnHeight;
  border-radius: 20px;
  background-color: @btnBackColor;
  color: #fff;
  text-align: center;
  margin-left: 16px;
  margin-top: 8px;
  font-size: 1rem;
}

.fileUpload span.btnUpload:hover,
.fileUpload span.btnUpload:focus {
  cursor: pointer;
}

.uploadGroup {
  background-color: #f3f3f3;
}
.selectText {
  display: block;
  width: 100%;
  margin: 8px 0;
}

.fileUpload .el-upload--picture-card {
  display: none;
}

// -- picture area ---------------------------------------------------
.fileUpload li.el-upload-list__item.is-success {
  width: 152px;
  height: 152px;
  border-radius: 4px;
  border: solid 1px @picBorderColor;
}
.btn-close {
  position: absolute;
  top: 0;
  right: 0;
  font-size: 24px;
  cursor: pointer;
  opacity: 0.5;
  color: #000000;
}
.img-opacity {
  opacity: 0.75;
}
.mark {
  position: absolute;
  display: inline-block;
  width: 100%;
  height: 36px;
  bottom: 0;
  right: 0;
  font-size: 14px;
  opacity: 0.7;
  background-color: #ff3e3e;
  text-align: center;
  line-height: 36px;
  color: #ffffff;
}
.el-select{
  margin:0;
}
</style>
