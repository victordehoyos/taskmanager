FROM node:18 AS build
WORKDIR /app

COPY package*.json /app
RUN npm install --legacy-peer-deps

COPY . /app

EXPOSE 4200
 
CMD ["npm", "run", "start", "--", "--host", "0.0.0.0"]