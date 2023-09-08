const { defineConfig } = require('@vue/cli-service');

module.exports = defineConfig({
  transpileDependencies: true,
  devServer :{
		port: 8081,
		proxy: {
		  '/api': {
		    target: 'http://62.234.21.39:8080/elm/', // 修改为你实际的后端接口地址
		    changeOrigin: true
		  }
		}
	}
});
