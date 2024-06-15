#!/bin/bash

set -e

# Run sbt dependencyUpdates and capture the output
dependency_updates=$(sbt dependencyUpdates)

# Extract the dependencies that need updates
updates=$(echo "$dependency_updates" | grep ' -> ')

# Update build.sbt with the new versions
while IFS= read -r line; do

# Extract the current dependency and the new version
current_dependency=$(echo "$line" | awk -F' -> ' '{print $1}')
new_version=$(echo "$line" | awk -F' -> ' '{print $2}' | xargs)

# Escape special characters in current dependency for sed
group=$(echo "$current_dependency" | sed 's/\[info\] //g' | sed 's/\(.*\) : .*/\1/' | sed 's/:/ \% /g' | sed 's/\(.*\) \% test/\1/' | sed 's/\([a-z.-]*\) % \([a-z.-]*\)/\1/g' | xargs )
dependency=$(echo "$current_dependency" | sed 's/\[info\] //g' | sed 's/\(.*\) : .*/\1/' | sed 's/:/ \% /g' | sed 's/\(.*\) \% test/\1/' | sed 's/\([a-z.-]*\) % \([a-z.-]*\)/\2/g' | xargs )

echo $group
echo $dependency

current_dependency_escaped=$(echo "\"$group\" %% \"$dependency\"")
current_dependency_escaped_single=$(echo "\"$group\" % \"$dependency\"")

echo "s/$current_dependency_escaped % \".*\"/$current_dependency_escaped % \"$new_version\"/"
# Update the version in build.sbt
sed -i "s/$current_dependency_escaped % \".*\"/$current_dependency_escaped % \"$new_version\"/" build.sbt
sed -i "s/$current_dependency_escaped_single % \".*\"/$current_dependency_escaped_single % \"$new_version\"/" build.sbt
done <<< "$updates"