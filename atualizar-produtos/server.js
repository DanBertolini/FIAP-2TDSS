var http = require('http');
var express = require('express');
var app = express();
var server = http.createServer(app);
var produtos = require("./assets/produtos.json");

server.get('/produtos', function (request, response) {
    response.writeHead(200, { 'Content-Type': 'application/json' });
    response.end(produtos);
});


var port = 8080;
server.listen(port);

//https://docs.microsoft.com/en-us/azure/app-service/app-service-web-tutorial-rest-api