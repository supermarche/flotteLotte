'use strict';

const express = require('express');
const session = require('express-session')
const app = express();

app.use(session({
  secret: 'r0drU-gmr1j4D,SN?p)xniX@xiCF12:',
  resave: false,
  saveUninitialized: true,
  cookie: { maxAge: 60000 }
}))

// Render ejs files / set view engine
app.set('view engine', 'ejs');

// Support body parsing for forms
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Statically serve content from resources subfolder
app.use("/resources", express.static("resources"));

const registrationRouter = require('./routes/registration');
const loginRouter = require('./routes/login');
const dashboardRouter = require('./routes/app');
const logoutRouter = require('./routes/logout');
const addTripRouter = require('./routes/addTrip');

app.use('/', registrationRouter);
app.use('/', loginRouter);
app.use('/', dashboardRouter);
app.use('/', logoutRouter);
app.use('/', addTripRouter);
app.get("/", )

module.exports = app;

// for development and debugging
if (require.main === module) {
    require('http').createServer(app).listen(3000, function () {
        console.info("Listening for HTTP on", this.address());
    });
}
