var express = require('express');
var router = express.Router();
const db=require('../database');

/* GET users listing. */
router.get('/', function(req, res, next) {
  if(req.session.loggedinUser){
    var sql='SELECT * FROM trips WHERE userid =?';
    db.query(sql, [req.session.userid], function (err, data, fields) {
      if(err) throw err

      data.forEach(each => {
        each["match"] = false;
        if((each.id == 1) || (each.id == 4)) {
          each["match"] = true;
        }
      });
      res.render('app', {email:req.session.emailAddress, messages: "", trips: data,  })
    });
  } else {
    res.redirect('/login');
  }
});
module.exports = router;
