FROM node:18 AS build
WORKDIR /app

COPY package*.json /app
RUN npm install --legacy-peer-deps
RUN npm install --legacy-peer-deps sockjs-client @stomp/stompjs -save
RUN npm list @stomp/stompjs sockjs-client 
COPY . /app

EXPOSE 4200
 
CMD ["npm", "run", "start", "--", "--host", "0.0.0.0"]