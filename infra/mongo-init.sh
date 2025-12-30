#!/bin/bash
set -e

echo "Starting MongoDB initialization script..."

mongosh --username mongoadmin --password secret --authenticationDatabase admin <<EOF
use ms-beautique-query;

db.dropUser("ms-beautique-query-user");
db.dropUser("ms-sync")

db.createUser({
  user: "ms-sync",
  pwd: "ms-sync",
  roles: [{ role: "dbOwner", db: "ms-beautique-query" }]
});

db.createUser({
  user: "ms-beautique-query",
  pwd: "ms-beautique-query",
  roles: [{ role: "read", db: "ms-beautique-query" }]
});

EOF

echo "MongoDB initialization script completed."