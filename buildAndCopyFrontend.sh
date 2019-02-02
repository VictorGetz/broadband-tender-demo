#!/usr/bin/env bash
rm -R build/resources/main/static/*
cd frontend;
npm install && npm run build;
cd ..;
cp -R frontend/dist/* build/resources/main/static

