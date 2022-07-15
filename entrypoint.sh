#!/bin/bash

echo "Waiting for db..."
while ! nc -z test_db 3306; do
  sleep 0.1
done
echo "db started"

echo "
O———————————————————————O
     Project started…
O———————————————————————O
"
exec "$@"