FROM node:lts-alpine as angular

# cria o workspace de trabalho do docker
WORKDIR /app

# copia o package.json para o workspace (/app) e executa o comando para instalar as dependencias
COPY package.json /app
#RUN npm install
#RUN npm install
RUN npm install -g @angular/cli && npm install

# depois da instalação das dependências, realiza a copia de todos os arquivos do projeto para a imagem docker
COPY . .

# executa o comando de build de producao
#RUN ng build --configuration production
RUN ng build --configuration docker

#============================================================================================================
# Servidor Web
FROM nginx:alpine

# Volume para os arquivos no nginx
VOLUME /var/cache/nginx

# Copia o conteudo da pasta /dist do projeto para o diretorio html do nginx
COPY --from=angular app/dist/frontend /usr/share/nginx/html

# Copia o arquivo de configuracao do nginx para a pasta de configuracao do servidor
COPY ./config/nginx.conf /etc/nginx/conf.d/default.conf


# comandos:
# criacao da imagem: docker build -t cadpessoa-frontend .
# criacao do container: docker run -d -p 8081:80 cadpessoa-frontend