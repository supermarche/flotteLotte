const mysql = require('mysql');
const conn = mysql.createConnection({
  host: 'mysql',                     // host name
  user: 'root',                      // database username
  password: 'flotteLotteUnterwegs',  // database password
  database: 'flotteLotte'            // database Name
}); 

conn.connect(function(err) {
  if (err) throw err;
  console.log('Connection to database successfully established!');
});

module.exports = conn;
