<template id="fileUpload">
  <div class="fileUpload">
    <div class="selectArea">
      <el-select style='width:100%' v-model="ddlSelectedValue" @change="getSelectText" :placeholder='$t("msg.Common.PlcH.Select")'>
        <el-option v-for='(item, index) in ddl.options' :key='index' :label='$t(item.Text)' :value='item.Value'>
        </el-option>
      </el-select>
      <span class="btnUpload" @click="btnUpload_click($event)">{{$t("msg.Common.Btn.Upload")}}</span>
    </div>
    <label v-if="tips!=''">{{tips}}</label>
    <div class="uploadGroup">
      <div v-for="(item ,index) in allFileList" :key='index'>
        <div class="selectText">{{$t(item.id)}}</div>
        <transition-group tag="ul" :class="[ 'el-upload-list', 'el-upload-list--picture-card']" name="el-list">
          <li v-for="(file,index2) in item.files" :key='index2' :class="['el-upload-list__item']">
            <img class="el-upload-list__item-thumbnail img-opacity" :src="file.url" :alt='file.name'>
            <!-- <embed type="application/pdf" class="el-upload-list__item-thumbnail" :src="file.url" :alt='file.name'> -->
            <span class="mark">{{$t("msg.component.FileUpload.AppOnly")}}</span>
            <i class="el-icon-circle-close btn-close" @click="handleRemove (item.id, file)"></i>
            <el-progress v-if="file.status === 'uploading'" type="circle" :stroke-width="6" :percentage="parsePercentage(file.percentage)">
            </el-progress>
          </li>
        </transition-group>
      </div>
    </div>
    <el-upload ref="elUpload" action="#" list-type="picture-card" :name="ddlSelectedValue" :on-preview="handlePictureCardPreview" :on-change="handleChange" :accept="acceptList" :limit="6" :auto-upload="false" :show-file-list="false">
      <i class="el-icon-plus"></i>
    </el-upload>
  </div>
</template>

<script type="text/javascript">
import sbaGeneralJs from './../../assets/js/sba-general.js'
import { uploadFile, removeUploadFile } from '@/views/sba/apis/index'
export default {
  template: '#fileUpload',
  components: {},
  data: () => ({
    ddlSelectedText: '',
    ddlSelectedValue: '',
    acceptList: '.GIF,.JPG,.JPEG,.TIF,.TIFF,.BMP,.DOC,.DOCX,.PPT,.PPTX,.MDI,.PNG,.PDF',
    allFileList: [],
    uploadInput: {}
  }),
  props: {
    ddl: {
      type: Object,
      default: () => {
        return {
          id: 'myFileUpload',
          name: 'myFileUpload',
          options: [{ Text: '', Value: '' }]
        }
      }
    },
    tips: {
      type: String,
      default: ''
    },
    fileLimit: {
      maxFileSize: 0,
      maxFileCount: 0,
      currentCount: 0
    }
  },
  methods: {
    parsePercentage (val) {
      return parseInt(val, 10)
    },
    handleRemove (id, file) {
      console.log('handleRemove')
      console.log(id, file)
      const self = this
      removeUploadFile(file.uid).then(res => {
        let index = self.allFileList.findIndex(x => {
          return x.id === id
        })

        if (index >= 0) {
          let index2 = self.allFileList[index].files.findIndex(x => {
            return x.uid === file.uid
          })
          if (index2 >= 0) {
            self.allFileList[index].files.splice(index2, 1)
            self.fileLimit.currentCount--
          }
          if (self.allFileList[index].files.length === 0) {
            self.allFileList.splice(index, 1)
            self.fileLimit.currentCount--
          }
        }
      }).catch((error) => {
        console.log(error.message)
      })
    },
    handlePictureCardPreview (file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handleChange (file, fileList) {
      const self = this
      let index = 0
      let index2 = 0
      console.log('handleChange:')
      console.log(file, fileList)
      self.fileLimit.currentCount++
      if (self.fileLimit.currentCount > self.fileLimit.maxFileCount) {
        self.$message({
          message: self.$t('msg.component.FileUpload.FileCount').replace('##', self.fileLimit.maxFileCount),
          type: 'warning',
          center: true
        })
        self.$refs['elUpload'].uploadFiles = []
        self.fileLimit.currentCount--
        return
      }
      const extension = file.name.substring(file.name.lastIndexOf('.')).toUpperCase()
      const extensions = self.acceptList.split(',')
      const isValidType = extensions.indexOf(extension) >= 0
      if (!isValidType) {
        self.$message({
          message: self.$i18n.t('msg.component.FileUpload.InvalidFileFormat'),
          type: 'warning',
          center: true
        })
        self.$refs['elUpload'].uploadFiles = []
        self.fileLimit.currentCount--
        return
      }

      if (file.size <= 0) {
        self.$message({
          message: self.$i18n.t('msg.component.FileUpload.FileSizeZero'),
          type: 'warning',
          center: true
        })
        self.$refs['elUpload'].uploadFiles = []
        self.fileLimit.currentCount--
        return
      }

      const isLstSize = file.size <= self.fileLimit.maxFileSize
      if (!isLstSize) {
        self.$message({
          message: self.$t('msg.component.FileUpload.FileSizeExceed').replace('##', self.fileLimit.maxFileSize / 1024 / 1024),
          type: 'warning',
          center: true
        })
        self.$refs['elUpload'].uploadFiles = []
        self.fileLimit.currentCount--
        return
      }

      index = self.allFileList.findIndex(x => {
        return x.id === self.ddlSelectedText
      })

      if (index >= 0) {
        self.allFileList[index].files.push(file)
      } else {
        index = self.allFileList.length
        self.allFileList.push({
          id: self.ddlSelectedText,
          files: [file]
        })
      }
      index2 = self.allFileList[index].files.length - 1

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
      formData.append(self.ddlSelectedValue, file.raw, file.name)
      formData.append('uid', file.uid)
      uploadFile(formData, config).then((res) => {
        console.log(res)
        if (res.data.Status === 0) {
          file.status = 'ready'
        } else {
          self.fileLimit.currentCount--
          if (index2 === 0) {
            self.allFileList.splice(index, 1)
          } else {
            self.allFileList[index].files.splice(index2, 1)
          }
          self.showMessage(self, res.data)
        }
      }).catch((error) => {
        console.log(error.message)
        self.fileLimit.currentCount--
        if (index2 === 0) {
          self.allFileList.splice(index, 1)
        } else {
          self.allFileList[index].files.splice(index2, 1)
        }
      })

      self.$refs['elUpload'].uploadFiles = []
    },
    btnUpload_click (event) {
      const self = this
      if (!self.ddlSelectedValue) {
        self.$message({
          message: self.$i18n.t('msg.component.FileUpload.PselectFile'),
          type: 'warning',
          center: true
        })
        return
      }
      let me = event.target
      let myParent = sbaGeneralJs.methods.findParentByClassName(me, 'fileUpload')
      self.uploadInput = myParent.getElementsByClassName('el-upload__input')[0]
      self.uploadInput.click()
    },
    getSelectText (value) {
      let self = this
      self.ddlSelectedText = self.ddl.options.filter((currentOption, index) => {
        return currentOption.Value === value
      })[0].Text
    }
  },
  mounted: function () {
    const self = this
    self.ddlSelectedValue = self.ddl.options[0].Value
    self.ddlSelectedText = self.ddl.options[0].Text
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
