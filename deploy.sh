#!/bin/bash

set -euo pipefail  # stop on first error, unset variables are errors, pipeline fails on first error

REMOTE_USER="D.Manandraibe"
REMOTE_HOST="192.168.1.86"
REMOTE_DIR="C:/karylahy-apps/sakamalao/sakamalao-backend"

LOCAL_DOCKER_DIR="docker"
IMAGE_NAME="sakamalao-backend"
CONTAINER_NAME="sakamalao-backend"
TAR_FILE="${IMAGE_NAME}.tar"
COMPOSE_FILE="docker-compose.yml"

mvn clean package -DskipTests

echo "📁 Ensuring local docker directory exists..."
cd "$LOCAL_DOCKER_DIR"

# Optional: build docker images locally
echo "🐳 Building docker images with docker-compose..."
#docker-compose build
docker build -t $IMAGE_NAME:latest -f Dockerfile ..

# Save Docker image to tar
echo "📦 Saving Docker image $IMAGE_NAME to $TAR_FILE..."
docker save -o "$TAR_FILE" "$IMAGE_NAME:latest"

# Copy files to remote
echo "📤 Copying files to remote host $REMOTE_HOST..."
ssh $REMOTE_USER@$REMOTE_HOST "
  if (!(Test-Path '$REMOTE_DIR')) {
    echo 'Remote dir created! $REMOTE_DIR'
    New-Item -ItemType Directory -Path '$REMOTE_DIR'
  }
"
scp "$COMPOSE_FILE" "$REMOTE_USER@$REMOTE_HOST:$REMOTE_DIR"
scp "$TAR_FILE" "$REMOTE_USER@$REMOTE_HOST:$REMOTE_DIR"
scp ./.env $REMOTE_USER@$REMOTE_HOST:$REMOTE_DIR

# Run commands on remote host
echo "🖥 Executing deployment on remote host..."
ssh $REMOTE_USER@$REMOTE_HOST "
  echo '📁 Moving into $REMOTE_DIR'
  cd $REMOTE_DIR

  echo '📦 Loading docker image from tar...'
  docker load -i $TAR_FILE

  if (docker ps -a -q -f name=$CONTAINER_NAME) {
    docker stop $CONTAINER_NAME
    docker rm $CONTAINER_NAME
  }
#  echo '🚀 Starting docker-compose...'
#  docker run -d --env-file .env --name $CONTAINER_NAME $IMAGE_NAME:latest

  echo "🧹 Stopping existing containers..."
  docker compose down

  echo "🚀 Starting containers..."
  docker compose up -d

  echo '✅ Deployment finished on remote host'
"

echo "🎉 ALL DONE!"
