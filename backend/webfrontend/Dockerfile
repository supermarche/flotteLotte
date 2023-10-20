FROM node:18-bookworm

RUN apt-get update; apt-get install --no-install-recommends dumb-init

USER node
WORKDIR /webfrontend/
COPY --chown=node:node package.json package-lock.json server.js app.js index.html .

RUN npm ci

RUN mkdir greenlock.d
RUN echo '{ "sites": [{ "subject": "flottelotte.mooo.com" }] }' > greenlock.d/config.json
CMD [ "dumb-init", "node", "server.js"]
