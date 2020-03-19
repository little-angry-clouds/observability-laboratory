// Imports
local grafana = import '../grafonnet-lib/grafonnet/grafana.libsonnet';
local dashboard = grafana.dashboard;
local row = grafana.row;
local singlestat = grafana.singlestat;
local graph = grafana.graphPanel;
local prometheus = grafana.prometheus;
local template = grafana.template;
local tablePanel = grafana.tablePanel;

local defaultConfig = {
  dashboard: {
    name: "Blackbox",
    tags: ['blackbox'],
    time_range: 'now-30m',
  },
};

// Don't touch this
local myConfig = {};
local config = if ! std.objectHas(myConfig, "dashboard") then defaultConfig;

dashboard.new(
  config.dashboard.name,
  tags=config.dashboard.tags,
  time_from=config.dashboard.time_range,
  editable=true,
)

// Datasource
.addTemplate(
  template.datasource(
    'datasource',
    'prometheus',
    '',
    hide='all',
    label='Datasource',
  )
)

.addRow(
  row.new(
    title='Blackbox Exporter',
    collapse=false,
  )
  .addPanel(
    graph.new(
      'Probe Success',
      datasource='$datasource',
      span=12/2,
      height=400,
    )
    .addTarget(
      prometheus.target(
        'probe_success',
      ),
    )
  )
  .addPanel(
    graph.new(
      'Probe Latecy',
      datasource='$datasource',
      span=12/2,
      height=400,
    )
    .addTarget(
      prometheus.target(
        'probe_duration_seconds',
      ),
    )
  )
)
