
'use strict'
// Template version: 1.2.0
// see http://vuejs-templates.github.io/webpack for documentation.

const path = require('path')

module.exports = {
  dev: {

    // Paths
    assetsSubDirectory: 'static',
    assetsPublicPath: '/',
    proxyTable: {
      '/sba': {
        target: '/stockexchange',

        secure: false
      },
      '/user': {  //使用"/api"来代替"http://f.apiplus.c" 
        target: 'http://localhost:8200', //源地址  http://9.112.43.38:8200 http://192.168.31.21:8200
        changeOrigin: true, //改变源 
        pathRewrite: { 
          '^/user': '' //路径重写 
          } 
      },
      '/com': {  //使用"/api"来代替"http://f.apiplus.c" 
        target: 'http://localhost:8001', //源地址  http://9.112.43.38:8001 http://192.168.31.21:8200
        changeOrigin: true, //改变源 
        pathRewrite: { 
          '^/com': '' //路径重写 
        }
      },
      '/stock': {  //使用"/api"来代替"http://f.apiplus.c" 
        target: 'http://localhost:8004', //源地址  http://9.112.43.38:8004 http://192.168.31.21:8200
        changeOrigin: true, //改变源 
        pathRewrite: { 
          '^/stock': '' //路径重写 
        }
      }
    },
    //http://localhost:54122

    // Various Dev Server settings
    host: '127.0.0.1', // can be overwritten by process.env.HOST
    port: 8081, // can be overwritten by process.env.HOST, if port is in use, a free one will be determined
    autoOpenBrowser: true,
    errorOverlay: true,
    notifyOnErrors: true,
    poll: false, // https://webpack.js.org/configuration/dev-server/#devserver-watchoptions-

    headers: {
      'Access-Control-Allow-Origin': '*'
    },
    hotOnly: false,
    disableHostCheck: true,
    // Use Eslint Loader?
    // If true, your code will be linted during bundling and
    // linting errors and warings will be shown in the console.
    useEslint: true,
    // If true, eslint errors and warings will also be shown in the error overlay
    // in the browser.
    showEslintErrorsInOverlay: false,

    /**
     * Source Maps
    */

    // https://webpack.js.org/configuration/devtool/#development
    devtool: 'eval-source-map',

    // If you have problems debugging vue-files in devtools,
    // set this to false - it *may* help
    // https://vue-loader.vuejs.org/en/options.html#cachebusting
    cacheBusting: true,

    // CSS Sourcemaps off by default because relative paths are "buggy"
    // with this option, according to the CSS-Loader README
    // (https://github.com/webpack/css-loader#sourcemaps)
    // In our experience, they generally work as expected,
    // just be aware of this issue when enabling this option.
    cssSourceMap: false,
  },

  build: {
    // Template for index.html
    index: path.resolve(__dirname, '../stockexchange/index.html'),
    // Paths
    assetsRoot: path.resolve(__dirname, '../stockexchange/'),
    assetsSubDirectory: 'static',
    assetsPublicPath: './',
    
    /**
     * SourceMap
    */
    productionSourceMap: false, // Set false can reduce size
    // https://webpack.js.org/configuration/devtool/#production
    devtool: '#source-map',

    // Gzip off by default as many popular static hosts such as
    // Surge or Netlify already gzip all static assets for you.
    // Before setting to `true`, make sure to:
    // npm install --save-dev compression-webpack-plugin
    productionGzip: false,
    productionGzipExtensions: ['js', 'css'],

    // Run the build command with an extra argument to
    // View the bundle analyzer report after build finishes:
    // `npm run build --report`
    // Set to `true` or `false` to always turn it on or off
    bundleAnalyzerReport: process.env.npm_config_report
  }
}
