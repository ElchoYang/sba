'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  API_URL_USER: '"/user"',
  API_URL_COM: '"/com"',
  API_URL_STOCK: '"/stock"',  
})
