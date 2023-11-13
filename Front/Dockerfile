# Stage 1: Build the Angular app
FROM node:14 as builder

WORKDIR /app

COPY package*.json ./

RUN npm ci

COPY ./Front .

RUN npm run build

FROM nginx:alpine

COPY --from=builder /app/dist/front /usr/share/nginx/html

RUN rm /etc/nginx/conf.d/default.conf

CMD ["nginx", "-g", "daemon off;"]
