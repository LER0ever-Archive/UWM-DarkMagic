#!/bin/bash

# http
rm -rf dest-wisc/*
cp blog-source/source-wisc/meta.toml.http blog-source/source-wisc/meta.toml
Lumos build --source="blog-source/source-wisc" --dest="dest-wisc" --theme="theme/duena"
cp README.md dest-wisc/~rong
cp dest-wisc/favicon.ico dest-wisc/~rong/
rsync -avH --delete --progress dest-wisc/~rong/ rong@best-linux.cs.wisc.edu:/afs/cs.wisc.edu/u/r/o/rong/public/html

# https
rm -rf dest-wisc/*
cp blog-source/source-wisc/meta.toml.https blog-source/source-wisc/meta.toml
Lumos build --source="blog-source/source-wisc" --dest="dest-wisc" --theme="theme/duena"
cp README.md dest-wisc/~rong
cp dest-wisc/favicon.ico dest-wisc/~rong/
rsync -avH --delete --progress dest-wisc/~rong/ rong@best-linux.cs.wisc.edu:/afs/cs.wisc.edu/u/r/o/rong/public/html-s

# www
rm -rf dest-wisc/*
cp blog-source/source-wisc/meta.toml.www blog-source/source-wisc/meta.toml
Lumos build --source="blog-source/source-wisc" --dest="dest-wisc" --theme="theme/duena"
cp README.md dest-wisc/
rsync -avH --delete --progress dest-wisc/ rong@best-linux.cs.wisc.edu:/afs/cs.wisc.edu/u/r/o/rong/public/html-www
