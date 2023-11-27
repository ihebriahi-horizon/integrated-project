FROM node:16 as builder

WORKDIR /app

COPY ./Front/ ./

RUN npm set strict-ssl false

RUN rm package-lock.json

RUN npm i --legacy-peer-deps

RUN npm run build

FROM nginx:alpine

COPY --from=builder /app/dist/front /usr/share/nginx/html

RUN rm /etc/nginx/conf.d/default.conf

CMD ["nginx", "-g", "daemon off;"]
