'use strict';

var path = require('path');
var express = require('express');
var app = express();

app.get('/', express.static(path.join(__dirname, "public")));

module.exports = app;

// for development and debugging
if (require.main === module) {
    require('http').createServer(app).listen(3000, function () {
        console.info("Listening for HTTP on", this.address());
    });
}
