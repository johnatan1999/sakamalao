##!/bin/bash
#
#set -e  # stop on first error
#
#REMOTE_USER="D.Manandraibe"
#REMOTE_HOST="192.168.1.86"
#REMOTE_DIR="C:/karylahy-apps/sakamalao"
#
#cd docker
#
#echo "🐳 Building docker images with docker-compose..."
##docker-compose build
#
#echo "📦 Saving image sakamalao-backend:latest to tar..."
##docker save -o sakamalao.tar sakamalao-backend:latest
#
#echo "📤 Copying tar to remote machine..."
#scp docker-compose.yml $REMOTE_USER@$REMOTE_HOST:$REMOTE_DIR
#scp sakamalao.tar $REMOTE_USER@$REMOTE_HOST:$REMOTE_DIR
#
#echo "🖥 Running commands on remote host $REMOTE_HOST..."
#
#ssh $REMOTE_USER@$REMOTE_HOST "
#  echo '📁 Moving into $REMOTE_DIR'
#  cd $REMOTE_DIR
#
#  echo '📦 Loading docker image from tar...'
#  docker load -i sakamalao.tar
#
#  echo '🚀 Starting docker-compose...'
#  docker-compose up -d
#
#  echo '✅ Deployment finished on remote host'
#"
#
#echo "🎉 ALL DONE"

#!/bin/bash

set -euo pipefail  # stop on first error, unset variables are errors, pipeline fails on first error

REMOTE_USER="D.Manandraibe"
REMOTE_HOST="192.168.1.86"
REMOTE_DIR="C:/karylahy-apps/sakamalao"

LOCAL_DOCKER_DIR="docker"
IMAGE_NAME="sakamalao-backend"
TAR_FILE="${IMAGE_NAME}.tar"
COMPOSE_FILE="docker-compose.yml"

echo "📁 Ensuring local docker directory exists..."
cd "$LOCAL_DOCKER_DIR"

# Optional: build docker images locally
echo "🐳 Building docker images with docker-compose..."
# docker-compose build

# Save Docker image to tar
echo "📦 Saving Docker image $IMAGE_NAME to $TAR_FILE..."
#docker save -o "$TAR_FILE" "$IMAGE_NAME:latest"

# Copy files to remote
echo "📤 Copying files to remote host $REMOTE_HOST..."
#scp "$COMPOSE_FILE" "$REMOTE_USER@$REMOTE_HOST:$REMOTE_DIR"
#scp "$TAR_FILE" "$REMOTE_USER@$REMOTE_HOST:$REMOTE_DIR"
#scp ../.env $REMOTE_USER@$REMOTE_HOST:$REMOTE_DIR

# Run commands on remote host
echo "🖥 Executing deployment on remote host..."
ssh $REMOTE_USER@$REMOTE_HOST "
  echo '📁 Moving into $REMOTE_DIR'
  cd $REMOTE_DIR

  echo '📦 Loading docker image from tar...'
  docker load -i $TAR_FILE

  echo '🚀 Starting docker-compose...'
  docker-compose up -d

  echo '✅ Deployment finished on remote host'
"

echo "🎉 ALL DONE!"
