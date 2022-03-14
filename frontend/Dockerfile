FROM node:16.13.1 as builder

ENV API_URL `cat /app/config/backend-url`

WORKDIR /app

COPY package.json ./

RUN npm install

COPY . ./

EXPOSE 3000

CMD [ "npm", "start" ]
