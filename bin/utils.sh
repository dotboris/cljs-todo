#!/bin/bash

# colors
echo_failure() {
  echo -e 1>&2 "\e[31m$*\e[0m"
}

echo_success() {
  echo -e "\e[32m$*\e[0m"
}

assert_commands() {
  local failure=false

  for command in "$@"; do
    command -v "$command" > /dev/null 2>&1
    if [ $? -ne 0 ]; then
      echo_failure "ERROR: Command not found '$command'"
      failure=true
    fi
  done

  if $failure; then
    exit 1
  fi
}
