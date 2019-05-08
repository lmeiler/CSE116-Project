import json
import socket
from threading import Thread

from flask import Flask, send_from_directory, request
from flask_socketio import SocketIO

import eventlet

eventlet.monkey_patch()

app = Flask(__name__)
socket_server = SocketIO(app)

# ** Consudnect to Scala TCP socket server **

scala_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
scala_socket.connect(('localhost', 9000))

delimiter = "~"


def listen_to_scala(the_socket):
    buffer = ""
    while True:
        buffer += the_socket.recv(1024).decode()
        while delimiter in buffer:
            message = buffer[:buffer.find(delimiter)]
            buffer = buffer[buffer.find(delimiter) + 1:]
            get_from_scala(message)
            print(message)


def get_from_scala(data):
    print(data)
    socket_server.emit('gameState', data, broadcast=True)


def send_to_scala(data):
    scala_socket.sendall((json.dumps(data) + delimiter).encode())


Thread(target=listen_to_scala, args=(scala_socket,)).start()


# ** Setup and start Python web server **

@socket_server.on('connect')
def got_message():
    print(request.sid + " connected")
    message = {"username": request.sid, "action": "connected"}
    send_to_scala(message)


@socket_server.on('disconnect')
def disconnect():
    print(request.sid + " disconnected")
    message = {"username": request.sid, "action": "disconnected"}
    send_to_scala(message)


@socket_server.on('keyStates')
def key_state(jsonKeyStates):
    key_states = json.loads(jsonKeyStates)
    print(key_state)
    x = 0.0
    if key_states["a"] and not key_states["d"]:
        x = -1.0
    elif not key_states["a"] and key_states["d"]:
        x = 1.0
    y = 0.0
    if key_states["w"] and not key_states["s"]:
        y = -1.0
    elif not key_states["w"] and key_states["s"]:
        y = 1.0
    message = {"username": request.sid, "action": "move", "x": x, "y": y}
    send_to_scala(message)


@app.route('/')
def index():
    return send_from_directory("static", 'game.html')


# @app.route('/<path:filename>  ')
# def static_files(filename):
#     return send_from_directory('static', filename)


print("Listening on port 8080")
socket_server.run(app, port=8080)
