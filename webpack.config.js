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
    path: path.resolve(__dirname, './server/src/main/resources/assets') //overridden in serverSettings
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
      {from: 'node_modules/font-awesome/css', to: 'css/'},
      {from: 'node_modules/font-awesome/fonts', to: 'fonts/'},
    ])
  ],
  mode: 'development'
};

module.exports = config;