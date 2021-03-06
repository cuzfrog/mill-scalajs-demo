const path = require('path');
const webpack = require('webpack');
const CopyWebpackPlugin = require('copy-webpack-plugin');

const config = {
  entry: {
    main: './out/client/fastOpt/dest/out.js',
    vendor: ['react', 'react-dom']
  },
  output: {
    filename: '[name].bundle.js',
    //path: path.resolve(__dirname, './client/test/resources/assets')
    path: path.resolve(__dirname, './server/resources/assets')
  },
  module: {
    rules: [
      {test: /\.css$/, use: 'css-loader'}
      //{test: /\.js$/, use: ["source-map-loader"], exclude:['https:', './https']}
    ]
  },
  plugins: [
    new CopyWebpackPlugin([
      {from: 'node_modules/bulma/css', to: 'css/'},
      {from: 'node_modules/bulma/sass', to: 'sass/'},
      {from: 'node_modules/bulma/bulma.sass', to: 'bulma.sass'},
      {from: 'node_modules/font-awesome/css', to: 'css/'},
      {from: 'node_modules/font-awesome/fonts', to: 'fonts/'},
      {from: 'client/resources/public/', to: ''}
    ])
  ],
  mode: 'development',
  devServer: {
      contentBase: "./client/test/resources/",
      watchContentBase: true
  },
};

module.exports = config;