<template>
  <div id="app">
    <!-- For Browser-->
    <div class="bros">
      <section class="header">
        <nav>
          <el-row class="topbar">
            <!-- Desktop browser TOP BAR -->
            <div>
              <el-col :span="18" :push="3" class="logo logo_PC">
                <div class="logo_main">
                  <span class="logo_text_card">{{$t("msg.Common.PageTitle")}}</span>
                  <span class="login">
                      <a @click="go('signup')" id="signup">{{$t("msg.Navigation.signup")}}</a>
                      <a @click="go('login')" id="login"> | {{$t("msg.Navigation.login")}}</a>
                      <a @click="logout()" v-show="false" id="logout"> | {{$t("msg.Navigation.logout")}}</a>
                  </span>
                </div>
              </el-col>
            </div>
          </el-row>
          <el-row>
            <el-col class="nav">
              <!-- Admin -->
              <ul id="adminNav" ref="adminNav" v-show="false">
                <li @click="go(item.form.toLowerCase())" v-for="(item, index) in adminNav()" :key="index" :class="{'progress': item.form.toLowerCase().indexOf(currentForm().toLowerCase()) > -1}">
                  {{$t(item.title)}}
              </li> 
             </ul>
             <!-- User -->
              <ul id="userNav" ref="userNav" v-show="false">
                <li @click="go(item.form.toLowerCase())" v-for="(item, index) in userNav()" :key="index" :class="{'progress': item.form.toLowerCase().indexOf(currentForm().toLowerCase()) > -1}">
                  {{$t(item.title)}}
              </li> 
             </ul>
            </el-col>
          </el-row>
        </nav>
      </section>
      <section class="section_body">
        <div class="">
          <router-view />
        </div>
      </section>
    </div>
     
  </div>
</template>


<script>
import { myTestSubmit } from '@/views/sba/apis/formPost'
import { logout } from '@/views/sba/apis/API'

export default {
  name: 'app',
  data () {
    return {
      isBtnLoading: false,
      percent: 0,
      navigation: [],
      formTitle: ''
    }
  },
  created () {
  },
  mounted () {
    window.onpopstate = () => {
      history.go(1)
    }
  },
  methods: {
    go (page, isaddon) {
      const self = this
      console.log(page)
      myTestSubmit(page, self)
    },
    logout () {
      const self = this
      console.log('logout')
      logout(self)
    }
  }
}
</script>
 
<style lang="less">
@import "views/sba/assets/css/common.less";
@import "views/sba/assets/css/main.less";
.section_body .main_section{
  justify-content: center;
}
 
.logo_PC {
  padding: 0 0 1rem;
  .logo_main {
    background-size: 1.3rem;
    line-height: 1.3rem;
    .logo_text_dbs {
      font-weight: 700;
      padding-left: 1.4rem;
      font-size: 1.5rem;
    }
    .logo_text_card {
      padding-left: 0.5rem;
      font-size: 1.4rem;
    }
    .login {
      padding: 0.5rem 1.2rem;
      position: absolute;
      right: 0;
      top: -0.3rem;
      a{
       cursor: pointer;
      }
    }
  }
}
   
.nav {
  margin-top: 1.2rem;
  padding-right: 1rem;
  ul {
    display: flex;
    justify-content: space-around;
    font-size: 14px;
    padding: 0;
    margin: 0;
    list-style: none;
    line-height: 14px;
    font-family: Helvetica;
  }
  li {
    border-bottom: 1px solid #ccc;
    color: #ccc;
    background-size: 2rem;
    -moz-background-size: 2rem;
    background-repeat: no-repeat;
    background-position: 97% 0.75rem;
    cursor: pointer;
  }
  .progress {
    color: #000;
    border-bottom-color: #000;
  }
}
.pc {
  .main_section{
    justify-content: center;
  }
  section.main_section {
    .main_section_body {
      margin: 0;
      width: 100%;
    }
    .main_section_content {
      padding: 1rem 2rem 0;
      background-color: white;
    }
  }
  .el-header {
    margin: 0;
    padding: 1rem 0;
    text-align: left;
    font-size: 1.125rem;
    height: auto !important;
  }
  .el-main {
    padding: 0;
  }
  .footer_row {
    background-color: @MobilebgColor;
    margin: 0;
    padding: 30px 0;
  }
}
</style>


