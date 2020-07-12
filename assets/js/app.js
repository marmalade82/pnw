// We need to import the CSS so that webpack will load it.
// The MiniCssExtractPlugin is used to separate it out into
// its own CSS file.
import css from "../css/app.css"
import reagent from "../cljs/front-end/public/sass/index.scss"

// webpack automatically bundles all modules in your
// entry points. Those entry points can be configured
// in "webpack.config.js".
//
// Import dependencies
//
import "phoenix_html"

// Import local files
//
// Local files can be imported directly using relative paths, for example:
// import socket from "./socket"

//global.React = require('react'); 
//global.ReactDOMServer = require('react-dom/server');

// Need to import some specific functions from reagent here. For example,
//import * as Reagent from "./reagent";