var express = require('express');
var router = express.Router();
var db=require('../database');
// to display registration form
router.get('/register', function(req, res, next) {
  res.render('registration-form');
});
// to store user input detail on post request
router.post('/register', function(req, res, next) {
  console.log(req.body);
  inputData ={
    first_name: req.body.first_name,
    last_name: req.body.last_name,
    email_address: req.body.email_address,
    password: req.body.password,
    confirm_password: req.body.confirm_password
  }

  // check unique email address
  var sql='SELECT * FROM user WHERE email_address =?';
  db.query(sql, [inputData.email_address] ,function (err, data, fields) {
    if(err) throw err
    if(data.length>0){
      var msg = "Die Emailadresse " + inputData.email_address+ " ist bereits bei flotteLotte registriert. ";
      res.render('registration-form',{alertMsg:msg});
    } else if(inputData.confirm_password != inputData.password){
      var msg ="Passwort stimmt nicht überein.";
      res.render('registration-form',{alertMsg:msg});
    } else {
      // save users data into database
      var sql = 'INSERT INTO user SET ?';

      // remove confirm_password
      delete inputData.confirm_password
      db.query(sql, inputData, function (err, data) {
        if (err) throw err;
      });
      var msg ="Die Registration wurde erfolgreich abgeschlossen.";
      res.render('registration-complete',{alertMsg:msg});
    }
  })
});
module.exports = router;
