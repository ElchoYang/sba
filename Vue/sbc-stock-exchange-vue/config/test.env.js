'use strict'
const merge = require('webpack-merge')
const devEnv = require('./dev.env')

module.exports = merge(devEnv, {
  NODE_ENV: '"testing"',
  API_URL_USER: '"/user"',
  API_URL_COM: '"/com"',
  API_URL_STOCK: '"/stock"',  
})
