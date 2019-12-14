<template>
  <div>
    <el-form label-position="top" :model="formModel" :rules="rules" ref="form">
      <el-form-item class="SubDes">
        <label class="el-form-item__label">{{$t("msg.Common.PlcH.Input")}}</label>
      </el-form-item>

      <el-form-item prop="MobileTel" :label='$t("msg.component.signup.MobileTel")'>
        <el-popover ref="pop" :visible-arrow="true" placement="top" width="200" trigger="focus" :content='$t("msg.component.signup.MobRemark")'></el-popover>
        <el-input v-popover:pop v-model="formModel.MobileTel" :placeholder='$t("msg.Common.PlcH.Enter")' @blur="checkfield('MobileTel')" :maxlength="8" type="tel"></el-input>
      </el-form-item>

      <el-form-item prop="Email" :label='$t("msg.component.signup.Email")'>
        <el-popover ref="popover" :visible-arrow="true" placement="top" width="200" trigger="manual" :content='$t("msg.component.signup.EmailRemark")' v-model="visible"></el-popover>
        <el-autocomplete v-popover:popover class="email" :fetch-suggestions="querySearch" @focus="visible = true" @blur="checkfield('Email')" v-model="formModel.Email" :placeholder='$t("msg.Common.PlcH.email")' :trigger-on-focus="false" :maxlength='40' @select="handleSelect">
        </el-autocomplete>
      </el-form-item>

    </el-form>
  </div>
</template>

<script>

export default {
  components: {
  },
  props: {
  },
  data () {
    return {
      visible: false,
      formModel: {
        MobileTel: '',
        Email: ''
      }
    }
  },
  created () {
  },
  mounted () {},
  methods: {
    initFormModel (model) {
      this.copyValue(model.OMNIModel.PersonalInfo, this.formModel)
    },
    getFormModel () {
      return this.formModel
    }
  }
}
</script>



<style lang="css" scoped>
.email {
  width: 100%;
}
</style>