FROM node:14 as builder

WORKDIR /app

COPY ./Front/package*.json ./

RUN npm i

COPY ./Front .

RUN npm run build

FROM nginx:alpine

COPY --from=builder /app/dist/front /usr/share/nginx/html

RUN rm /etc/nginx/conf.d/default.conf

CMD ["nginx", "-g", "daemon off;"]
