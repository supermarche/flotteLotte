var express = require('express');
var router = express.Router();
const db=require('../database');

/* GET users listing. */
router.get('/showTrip', function(req, res, next) {
  if(req.session.loggedinUser){
    var sql='SELECT * FROM trips WHERE id =?';
    var trips;
    if (req.query.tripID == 1) {
      var sql2='INSERT INTO messages SET ?';
      db.query(sql2, {receiver: '2', message: 'Fahrt startet.'}, function(err, data2, fields) {
        if(err) throw err;
      });
    }
    db.query(sql, [req.query.tripID], function (err, data, fields) {
      if(err) throw err
      console.log(data);
      res.render('showTrip', {email:req.session.emailAddress, trip: data[0] })
    });
  } else {
    res.redirect('/login');
  }
});

router.post('/showTrip', function(req, res, next) {
  if(req.session.loggedinUser){
    var sql='SELECT * FROM trips WHERE id =?';
    var trips;
    db.query(sql, [req.query.tripID], function (err, data, fields) {
      if(err) throw err;
      res.render('showTrip', {email:req.session.emailAddress, trip: data[0] })
    });
  } else {
    res.redirect('/login');
  }
});
module.exports = router;
