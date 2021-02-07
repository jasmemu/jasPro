module.exports = {
    publicPath: './',
    devServer: {
        proxy: {
            '/cresource': {  // 资料下载使用
                target: 'http://localhost:8081', // 要跨域的域名  (部署时)
                changeOrigin: true// 是否开启跨域
            }
        }
    }
}
