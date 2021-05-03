module.exports = {
  transpileDependencies: ['vuetify'],
  lintOnSave: false,
  productionSourceMap: false, //빌드 시에 webpack 내의 코드가 보이지 않도록 설정
  filenameHashing: false, //빌드 시에 파일 이름에 해쉬값이 없도록 함
  publicPath: '', // 디폴트가 '/' 인데, root 호스트 중심으로 탐색하므로 상대경로로 갈 수 있도록 publicPath를 공백으로 설정
  // 개발 서버 설정
  devServer: {
    hot: true, // 바뀐 모듈만 리로드
    // 프록시 설정
    proxy: {
      // 프록시 요청을 보낼 api의 시작 부분
      '/auth': {
        // 프록시 요청을 보낼 서버의 주소
        target: 'http://localhost:9999',
      },
    },
  },
  css: {
    loaderOptions: {
      css: {
        sourceMap: process.env.NODE_ENV !== 'production' ? true : false,
      },
    },
  },
};
