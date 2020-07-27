#!/usr/bin/env python3

from random import randint
from time import sleep
from flask import Flask, jsonify, Response
from prometheus_client import Gauge, generate_latest

app = Flask(__name__)
gauge = Gauge("python_microservice_wait_time", "Returns the waited time", ["service"])


@app.route("/")
def root():
    wait_time = randint(1, 3)
    sleep(wait_time)
    gauge.labels("flask-example").set(wait_time)
    return jsonify({"message": "Hello World!"})


@app.route("/metrics")
def get_metrics():
    metrics = generate_latest()

    return Response(
        metrics, mimetype="text/plain", content_type="text/plain; charset=utf-8"
    )


if __name__ == "__main__":
    app.run(host="0.0.0.0", port="8080")
