// 使用代理下载文件（当点击浏览器的刷新建时有异常）
module.exports = {
    publicPath: '/',
    devServer: {
        proxy: {
            '/exceltable': {  // excel模板下载使用
                target: 'http://localhost:8081', // 要跨域的域名  (部署时)
                changeOrigin: true// 是否开启跨域
            },
            '/cresource': {  // 资料下载使用
                target: 'http://localhost:8081', // 要跨域的域名  (部署时)
                changeOrigin: true// 是否开启跨域
            },
            '/job': {  // 资料下载使用
                target: 'http://localhost:8081', // 要跨域的域名  (部署时)
                changeOrigin: true// 是否开启跨域
            }
        }
    }
}
