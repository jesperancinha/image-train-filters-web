#!/bin/bash
sed "s+{this}+"$(pwd)"+g" omni-config-original.json > omni-config.json
