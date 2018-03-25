# mill scalajs demo

This project is a proof of concept simple BS ReactJs application built by [lihaoyi/mill](http://www.lihaoyi.com/mill).
Which is to illustrate several idioms. 

### Building blocks:

* Mill - scala build tool.
* Scala.js - the essential compiler that aids cross compilation.
* ReactJs - client view framework.
* Diode - state flow control, the scala version of Redux.
* Simple-sri - scala.js to ReactJs binding.
* Monocle - optical library.

### Terms:

* **Code sharing** - source code reused by client and server at the same time.
* **Store, Action, Reducer** - Redux terms.
* **Double validation** - Same validation applies twice on client and server side.
* **Two way serialization** - Same model is serialized and de-serialized both on client and server side.
* **Remote data binding** - Data on client view is bind transparently with a data on server.
* **Remote Action flow** - An Action can be passed across remote boundary to the server and return to the client.

### Idioms:

* **Self contracting** - 
By sharing models and logics between client and server, the communication contract is the code itself.
Changing shared code, client/server contract changes simultaneously.

* **Logic balance** - 
Application logic could be implemented on client or/and server side, and moved from on side to the other with fewer constrains.

* **Transparent state** -
Any part of client state can be "ported" to server side, and vice versa.

### How to run this application locally.

1. Install mill: http://www.lihaoyi.com/mill
2. `git clone` this project
    * run `npm install`
    * run `mill client.fastOpt && npm run build && mill server.run`
