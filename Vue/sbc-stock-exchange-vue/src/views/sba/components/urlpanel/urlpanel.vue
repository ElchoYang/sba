<template>
  <div>
    <div v-html="html"></div>
  </div>

</template>

<script>
import Axios from '@/assets/js/AxiosPlugin'
export default {
  props: {
    url: {
      required: true
    }
  },
  data () {
    return {
      html: ''
    }
  },
  watch: {
    url (value) {
      this.load(value)
    }
  },
  mounted () {
    this.load(this.url)
  },
  created () {
  },
  methods: {
    load (url) {
      if (url && url.length > 0) {
        // this.loading = true
        let param = {
          // headers:
          accept: 'text/html,text/plain'
        }
        Axios.post(url, param).then((response) => {
          // this.loading = false
          console.log(response.data)
          this.html = response.data
        }).catch(() => {
          // this.loading = false
          this.html = 'loading fail'
        })
      }
    }
  }
}
</script>


<style lang="less" scoped>
</style>
<!-- Add "scoped" attribute to limit CSS to this component only -->
