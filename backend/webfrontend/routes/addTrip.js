var express = require('express');
var router = express.Router();
var db=require('../database');
// register hook
router.get('/addTrip', function(req, res, next) {
  if(req.session.loggedinUser){
    res.render('addTrip-form', {email: req.session.emailAddress});
  } else {
    res.redirect('/login');
  }
});
// to store user input detail on post request
router.post('/addTrip', function(req, res, next) {
  console.log(req.body);
  inputData ={
    userid: req.session.userid,
    point_of_departure: req.body.point_of_departure,
    destination: req.body.destination,
    trip_start: Date.parse(req.body.trip_start),
    trip_finish: Date.parse(req.body.trip_finish)
  }

  // check unique email address
  var sql='INSERT INTO trips SET ?';
  db.query(sql, inputData, function (err, data) {
      var msg ="Die Fahrt wurde registriert.";
      if (err) {
        msg = "Beim Registrieren der Fahrt ist ein Fehler aufgetreten"
        throw err
      }
      res.render('addTrip-complete',{alertMsg:msg, email: req.session.emailAddress});
  });
});
module.exports = router;
