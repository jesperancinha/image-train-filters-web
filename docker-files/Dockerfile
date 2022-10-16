FROM nginx

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

COPY nginx/default.conf /etc/nginx/conf.d/default.conf

COPY nginx/nginx.conf /etc/nginx/nginx.conf

#COPY dist /usr/share/nginx/html

COPY entrypoint.sh ${runningFolder}

EXPOSE 8080

ENTRYPOINT ["entrypoint.sh"]
