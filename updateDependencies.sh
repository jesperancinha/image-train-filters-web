#!/bin/bash

set -e

dependency_updates=$(sbt dependencyUpdates)

updates=$(echo "$dependency_updates" | grep ' -> ')

while IFS= read -r line; do
  current_dependency=$(echo "$line" | awk -F' -> ' '{print $1}')
  new_version=$(echo "$line" | awk -F' -> ' '{print $2}' | xargs)
  group=$(echo "$current_dependency" | sed 's/\[info\] //g' | sed 's/\(.*\) : .*/\1/' | sed 's/:/ \% /g' | sed 's/\(.*\) \% test/\1/' | sed 's/\([a-z.-]*\) % \([a-z.-]*\)/\1/g' | xargs )
  dependency=$(echo "$current_dependency" | sed 's/\[info\] //g' | sed 's/\(.*\) : .*/\1/' | sed 's/:/ \% /g' | sed 's/\(.*\) \% test/\1/' | sed 's/\([a-z.-]*\) % \([a-z.-]*\)/\2/g' | xargs )
  echo $group
  echo $dependency
  current_dependency_escaped=$(echo "\"$group\" \%\% \"$dependency\"")
  current_dependency_escaped_single=$(echo "\"$group\" \% \"$dependency\"")
  sed -i "s/$current_dependency_escaped \% \".*\"/$current_dependency_escaped \% \"$new_version\"/" build.sbt
  sed -i "s/$current_dependency_escaped_single \% \".*\"/$current_dependency_escaped_single \% \"$new_version\"/" build.sbt
done <<< "$updates"