module.exports = {
  configureWebpack: {
    devtool: 'source-map',
  },
  lintOnSave: false,
  devServer: {
    disableHostCheck: true,
    proxy: 'https://wsx6y0dvog.execute-api.us-east-1.amazonaws.com/dev/',
  },
  transpileDependencies: [
    'vuetify'
  ]
}
