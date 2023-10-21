var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/app', function(req, res, next) {
    if(req.session.loggedinUser){
        res.render('app',{email:req.session.emailAddress})
    }else{
        res.redirect('/login');
    }
});
module.exports = router;
