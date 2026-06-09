#!/usr/bin/env bash
set -euo pipefail

APP_URL="${APP_URL:-http://localhost:8080}"
ERROR_COUNT="${ERROR_COUNT:-10}"

echo "Triggering ${ERROR_COUNT} simulated errors against ${APP_URL}/api/error ..."
for i in $(seq 1 "${ERROR_COUNT}"); do
  curl -s -o /dev/null -w "Request ${i}: HTTP %{http_code}\n" "${APP_URL}/api/error" || true
done

echo ""
echo "Done. Wait ~1 minute, then check:"
echo "  Prometheus alerts: http://localhost:9090/alerts"
echo "  Grafana alerting:  http://localhost:3000/alerting/list (admin/admin)"
echo "  Kibana logs:       http://localhost:5601 (filter: level: \"ERROR\")"
