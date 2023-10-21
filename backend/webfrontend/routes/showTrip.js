var express = require('express');
var router = express.Router();
const db=require('../database');

/* GET users listing. */
router.get('/showTrip', function(req, res, next) {
  if(req.session.loggedinUser){
    var sql='SELECT * FROM trips WHERE userid =? and id =?';
    var trips;
    db.query(sql, [req.session.userid, req.query.tripID], function (err, data, fields) {
      if(err) throw err
      console.log(data);
      res.render('showTrip', {email:req.session.emailAddress, trip: data[0] })
    });
  } else {
    res.redirect('/login');
  }
});
module.exports = router;
