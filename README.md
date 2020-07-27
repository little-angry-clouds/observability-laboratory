# Observability Laboratory

This repository contains a docker compose configuration that will make possible
for you to develop grafana dashboards with a prometheus datasource.

One of the hardest things whern trying to learn how prometheus works is having
consistent metrics. All the exporters are different, you have to configure them,
etc. That why in this laboratory the metrics are created arbitrarly.

This is done with
[prometheus-data-generator](https://github.com/little-angry-clouds/prometheus-data-generator),
fake Prometheus exporter that let's you create arbitrary metrics.

The laboratory consists in:

- grafana
- prometheus
- prometheus-data-generator
- confimap-reloader for prometheus and for prometheus-data-generator

## Metrics

You may edit the `prometheus-data-generator/config.yml` to configure it. The
changes will be detected live, so you don't have to do anything more than edit it. You may
get more details on how to configure this exporter in it's
[repository](https://github.com/little-angry-clouds/prometheus-data-generator).

## Alerts

You may edit the `prometheus/rules.yml`. It will also be detected automatically
and prometheus will reload its configuration.

## Grafana

It will import any json dashboard that grafana detects in the
`grafana/dashboards/` directory. The dashboards will be imported and detected
automatically.

## Linux node exporter

There's a grafana dashboard.

## Windows node exporter

It can't be dockerized, so there's none.

## Microservices

### Python

There's to python microservices, one with plain [flask](https://flask.palletsprojects.com/en/1.1.x/)
and the other with [pyms](https://github.com/python-microservices/pyms).

### Java

There a java microservice with SpringBoot.

## How to use it

Clone the repository (with the `recursive` parameter if you want
[grafonnet](https://github.com/grafana/grafonnet-lib)):

```bash
git clone --recursive https://github.com/little-angry-clouds/observability-laboratory
```

Launch the containers:

```bash
docker-compose up
```

And that's it. You only have to edit the metrics, the rules and the dashboards
and access the services:

- [Grafana](http://localhost:3000): Default credentials are admin:admin
- [Prometheus](http://localhost:9090)
