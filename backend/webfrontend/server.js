const app = require('./app.js');

// Configure & Run the http server
require('greenlock-express').init({
        packageRoot: __dirname,
        configDir: "./greenlock.d",
 
        // contact for security and critical bug notices
        maintainerEmail: "marc.hoeppner@mailbox.org",
 
        // whether or not to run at cloudscale
        cluster: false
    }
).serve(app);
