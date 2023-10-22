FLotte Rotte

Hackathon 2023 / Görlitz

# Lokale Entwicklung
diff --git a/backend/docker-compose.yaml b/backend/docker-compose.yaml
index add33f7..2b18d5e 100644
--- a/backend/docker-compose.yaml
+++ b/backend/docker-compose.yaml
@@ -1,10 +1,13 @@
 services:
   webfrontend:
     build: webfrontend/
+    environment:
+      - debug=TRUE
     ports:
       # This is the entrypoint to our app
       - 443:443
       -  80:80
+      - 3000:3000
 
   mysql:


diff --git a/backend/webfrontend/Dockerfile b/backend/webfrontend/Dockerfile
index 559feac..0432c6a 100644
--- a/backend/webfrontend/Dockerfile
+++ b/backend/webfrontend/Dockerfile
@@ -11,4 +11,5 @@ RUN npm ci
 RUN mkdir greenlock.d && mkdir public
 COPY --chown=node:node index.html public/
 RUN echo '{ "sites": [{ "subject": "flottelotte.mooo.com" }] }' > greenlock.d/config.json
-CMD [ "dumb-init", "node", "server.js"]
+CMD ["node", "app.js"]
+#CMD [ "dumb-init", "node", "server.js"]


return ym.circleMarker(latlng, {
					  radius: 0,
					  fillColor: "#990000",
					  color: "#ffffff",
					  weight: 1,
					  opacity: 1,
					  fillOpacity: 0.0
					});

Dann ausführen (im backend Verzeichnis):
docker compose up -d mysql 
# Warten bis die Datenbank initialisiert wurde -- kann man docker compose logs mysql -f überprüft werden
docker compose up webfrontend

Google Drive Austauschordner: https://drive.google.com/drive/folders/1_M8P4LrHKXxCbk9DjGT6FyCleIIEHZHw?usp=sharing
