var express = require('express');
var router = express.Router();
const db=require('../database');

/* GET users listing. */
router.get('/', function(req, res, next) {
  if(req.session.loggedinUser){
    var messages = [];
    var sql='SELECT * FROM trips WHERE userid =?';
    db.query(sql, [req.session.userid], function (err, data, fields) {
      if(err) throw err

      data.forEach(each => {
        each["match"] = false;
        if((each.id == 1) || (each.id == 4)) {
          each["match"] = true;
        }
      });
      const sql_getMessages = 'SELECT * FROM messages WHERE receiver =?';
      db.query(sql_getMessages, [req.session.userid], function (err, data2, fields) {
        if(err) throw err;
        if(data2 && data2.length > 0) {
          data2.forEach(each => messages.push(each.message));
        }
        res.render('app', {email:req.session.emailAddress, messages: messages, trips: data })
      });
    });
  } else {
    res.redirect('/login');
  }
});
module.exports = router;
