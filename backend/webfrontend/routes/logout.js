const router = require("express").Router();

/* GET users listing. */
router.get('/logout', function(req, res) {
  req.session.destroy();
  res.redirect('/login');
});
module.exports = router;
