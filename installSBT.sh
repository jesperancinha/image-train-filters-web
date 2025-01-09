#!/usr/bin/env bash
SBT_VERSION=$(curl -s https://api.github.com/repos/sbt/sbt/releases/latest | grep "tag_name" | awk '{print $2}' | tr -d '",' | sed 's/^v//');
curl -L https://github.com/sbt/sbt/releases/download/v"$SBT_VERSION"/sbt-"$SBT_VERSION".tgz | tar xz;
sudo mv sbt/bin/* /usr/local/bin/