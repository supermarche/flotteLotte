FROM node:18-bookworm

RUN apt-get update; apt-get install --no-install-recommends dumb-init

USER node
WORKDIR /webfrontend/
COPY --chown=node:node . .

RUN npm ci

RUN mkdir greenlock.d && mkdir public
COPY --chown=node:node index.html public/
RUN echo '{ "sites": [{ "subject": "flottelotte.mooo.com" }] }' > greenlock.d/config.json
CMD [ "dumb-init", "node", "server.js"]
