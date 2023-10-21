const router = require("express").Router();
const db=require('../database');

/* GET users listing. */
router.get('/login', function(req, res, next) {
  res.render('login-form');
});

router.post('/login', function(req, res){
    var emailAddress = req.body.email_address;
    var password = req.body.password;
    var sql='SELECT * FROM user WHERE email_address =? AND password =?';
    db.query(sql, [emailAddress, password], function (err, data, fields) {
        if(err) throw err
        if(data.length == 1){
            req.session.loggedinUser= true;
            req.session.emailAddress= emailAddress;
            res.redirect('/app');
        }else{
            res.render('login-form',{alertMsg:"Benutzername oder Passwort fehlerhaft."});
        }
    })
})
module.exports = router;
