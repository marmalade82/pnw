const path = require('path');
const glob = require('glob');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const OptimizeCSSAssetsPlugin = require('optimize-css-assets-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');

module.exports = (env, options) => ({
  optimization: {
    minimizer: [
      new UglifyJsPlugin({ cache: true, parallel: true, sourceMap: false }),
      new OptimizeCSSAssetsPlugin({})
    ]
  },
  entry: {
      app: ['./js/app.js'].concat(glob.sync('./vendor/**/*.js')),
  },
  output: {
    filename: '[name].js',
    path: path.resolve(__dirname, '../priv/static/js')
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader'
        }
      },
      {
        test: /\.css$/,
        use: [MiniCssExtractPlugin.loader, 'css-loader']
      },
      { 
        test: /\.scss$/i,
        use: [
          {
            loader: 'file-loader',
            options: {
              outputPath: '../css/',
              name: '[name].css'
            }
          },
          { loader: 'sass-loader',
            options: {
              webpackImporter: false,
              sassOptions: {
                includePaths: [
                  ".",
                  "../cljs/front-end/public/sass"
                ]
              }
            }

          }
        ]
      }

    ]
  },
  plugins: [
    new MiniCssExtractPlugin(
        { filename: '../css/[name].css' }
    ),
    new CopyWebpackPlugin([
      { from: 'static/', to: '../' },
      { from: './js/reagent.js', to: '.'},
    ])
  ]
});
