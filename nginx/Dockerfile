FROM nginx:1.19.0-alpine

RUN rm /var/log/nginx/*

# replace conf
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx.conf /etc/nginx/conf.d/
