const {HelloRequest, HelloReply} = require('./helloworld_pb.js');
const {GreeterClient} = require('./helloworld_grpc_web_pb.js');

var client = new GreeterClient('http://192.168.99.100:8080');

var request = new HelloRequest();
request.setName('World');

/*
client.sayHello(request, {'Access-Control-Allow-Origin': '*'}, (err, response) => {
    console.log('response:', response.getMessage());
});
*/


client.sayHello(request, {}, (err, response) => {
  console.log(response.getMessage());
});
