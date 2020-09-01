# Observability Laboratory

This repository contains a docker compose configuration that will make possible
for you to develop grafana dashboards with a prometheus datasource.

One of the hardest things when trying to learn how prometheus works is having
consistent metrics. All the exporters are different, you have to configure them,
etc. That's why in this laboratory the metrics can be created arbitrarly, even 
though there's some exporters and instrumented programs.

The fake metrics are done with
[prometheus-data-generator](https://github.com/little-angry-clouds/prometheus-data-generator).
It's a fake Prometheus exporter that let's you create the metrics.

The laboratory consists in:

- grafana
- prometheus
- prometheus-data-generator
- python and java example microservices
- linux node exporter
- blackbox exporter
- confimap-reloader

## Prometheus

### Generate Metrics

You can generate arbitrary metrics. To do so, you may edit the `prometheus-data-generator/config.yml` to configure it. The
changes will be detected live, so you don't have to do anything more than edit it. You may
get more details on how to configure this exporter in it's
[repository](https://github.com/little-angry-clouds/prometheus-data-generator).

### Alerts

You may edit the `prometheus/rules.yml`. It will also be detected automatically
and prometheus will reload its configuration.

## Grafana

It will import any json dashboard that grafana detects in the
`grafana/dashboards/` directory. The dashboards will be imported and detected
automatically.

It has the [grafonnet](https://github.com/grafana/grafonnet-lib))submodule, in
case you want to generate the dashboards with it. The blackbox dashboard is an
example of it.

## Linux node exporter

There's a grafana dashboard.

## Windows node exporter

It can't be dockerized, so there's none.

## Microservices

### Python

There's two python microservices, one with plain [flask](https://flask.palletsprojects.com/en/1.1.x/)
and the other with [pyms](https://github.com/python-microservices/pyms).

### Java

There's a java microservice with SpringBoot.

### Blackbox exporter

Used to make requests to the microservices and create metrics.

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

- [Grafana](http://localhost:10000): Default credentials are admin:admin
- [Prometheus](http://localhost:10010)
- [Prometheus Data Generator](http://localhost:10020)
- [Linux Node Exporter](http://localhost:10030)
- [Python Flask](http://localhost:10040)
- [Python Pyms](http://localhost:10050)
- [Java Springboot](http://localhost:10060)
- [Blackbox Exporter](http://localhost:10070)
