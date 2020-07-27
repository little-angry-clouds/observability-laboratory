#!/usr/bin/env python3

from random import randint
from time import sleep
from flask import jsonify
from pyms.flask.app import Microservice
from prometheus_client import Gauge

ms = Microservice()
app = ms.create_app()
gauge = Gauge("python_microservice_wait_time", "Returns the waited time", ["service"])


@app.route("/")
def root():
    wait_time = randint(1, 3)
    sleep(wait_time)
    gauge.labels("pyms-example").set(wait_time)
    return jsonify({"message": "Hello World!"})


if __name__ == "__main__":
    app.run(host="0.0.0.0", port="8080")
