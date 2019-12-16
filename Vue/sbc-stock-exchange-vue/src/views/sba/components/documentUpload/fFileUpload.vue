<template>
  <el-form label-position="top" ref='form'>
    <el-form-item>
      <div id="fFileUpload">
        <div class="field">
          <div>
            <span class="fieldName">{{$t("msg.Navigation.importdata.select")}}
              <span class="imageIcon" @click.stop.self="showIdentity=true"></span>
            </span>
            <sba-file-upload></sba-file-upload>
          </div>
        </div>
      </div>
    </el-form-item>
 
  </el-form>
</template>

<script type="text/javascript">
import sbaFileUpload from '@/views/sba/components/toolbox/sba-fileUpload'

export default {
  components: {
    sbaFileUpload: sbaFileUpload
  },
  data () {
    return {
      fileLimit: {
        maxFileSize: 0,
        maxFileCount: 0,
        currentCount: 0
      }
    }
  },
  created () {
  },
  mounted () {
  },
  methods: {
    initFormModel (model) {
      this.fileLimit.maxFileSize = model.MaxFileSize
      this.fileLimit.maxFileCount = model.MaxFileCount
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
