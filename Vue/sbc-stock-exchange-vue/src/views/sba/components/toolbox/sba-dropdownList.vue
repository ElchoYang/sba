<template id="sba-select" class="sba-select">
  <div class="dropdownlist" >
    <div class="selectArea">
      <select class="realSelect" :id="ddl.id" :name="ddl.name" @change.stop="ddl_change($event)">
        <option v-for="opt in ddl.options" :key="opt.value" :value.sync="opt.value">{{opt.text}}</option>
      </select>
      <div class="fakeBorder">
        <span id="" class="lblfake txtfake chkMandatory">{{ddl.defaultText}}</span>
        <div class="arrow"></div>
      </div>
    </div>
  </div>
</template>

<script type="text/javascript">

export default {
  name: 'dbsSelect',
  template: '#sba-select',
  data: () => ({
    ddlText: 'Please select'
  }),
  props: {
    ddl: {
      type: Object,
      default: () => {
        return {
          id: 'mySelect',
          name: 'mySelect',
          defaultText: 'Please select 111',
          options: {
            type: Array,
            default: () => {
              return [{ text: 'Please select', value: 'default' }]
            }
          }
        }
      }
    }
  },
  methods: {
    ddl_change (event) {
      // debugger
      var me = this
      var select = event.target
      me.$data.ddlText = me.$props.ddl.options[select.selectedIndex].text
    }
  }
}
</script>

<style lang="less" scoped>
@import (once) "./../../assets/css/sba-general.less";

@left-width: 92%;

.dropdownlist {
  position: relative;
  width: @control-width;
  height: @control-height;
}

.dropdownlist .selectArea {
  width: 100%;
  height: 100%;
}

.dropdownlist .fakeBorder {
  border-radius: @border-radius;
  border: @border-size solid @grey;
  position: relative;
  width: 100%;
  height: 100%;
}

.dropdownlist .txtfake,
.dropdownlist .lblfake {
  display: block;
  min-width: @border-radius;
  height: 100%;
  line-height: @control-height - @border-size;
  padding-left: @left-padding;
  border: 0;
  background-color: transparent;
  white-space: nowrap;
  /* word-wrap: break-word; */
  overflow: hidden;
  /* text-overflow: ellipsis; */
  text-overflow: clip;
}

.dropdownlist .arrow {
  position: absolute;
  margin-right: 1rem;
  right: 0;
  top: 50%;
  width: 0.8rem;
  height: 0.8rem;
  border: 0.2rem solid transparent;
  border-bottom-color: @red;
  border-right-color: @red;
  transform-origin: 50% 50%;
  _transform-origin: 50% 50%;
  -webkit-transform-origin: 50% 50%;
  -moz-transform-origin: 50% 50%;
  transform: rotate(45deg) translateY(-50%);
  _transform: rotate(45deg) translateY(-50%);
  -webkit-transform: rotate(45deg) translateY(-50%);
  -moz-transform: rotate(45deg) translateY(-50%);
  margin-top: -0.5rem;
  /* margin-right: 0.5rem; */
  cursor: pointer;
}

.dropdownlist select.realSelect {
  position: absolute;
  left: 0;
  z-index: 1;
  width: 100%;
  height: 100%;
  -webkit-border-radius: @border-radius;
  -moz-border-radius: @border-radius;
  -ms-border-radius: @border-radius;
  border-radius: @border-radius;
  filter: alpha(opacity=0);
  -moz-opacity: 0;
  -khtml-opacity: 0;
  opacity: 0;
  color: transparent;
  background-color: transparent;
}

.dropdownlist select.realSelect:hover ~ .fakeBorder,
.dropdownlist select.realSelect:focus ~ .fakeBorder {
  box-shadow: @blue-shadow;
  border-color: @blue;
}

.dropdownlist select.realSelect:hover,
.dropdownlist select.realSelect:focus {
  cursor: pointer;
}

</style>

