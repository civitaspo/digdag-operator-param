# digdag-operator-param
[![Jitpack](https://jitpack.io/v/pro.civitaspo/digdag-operator-param.svg)](https://jitpack.io/#pro.civitaspo/digdag-operator-param) [![CircleCI](https://circleci.com/gh/civitaspo/digdag-operator-param.svg?style=shield)](https://circleci.com/gh/civitaspo/digdag-operator-param) [![Digdag](https://img.shields.io/badge/digdag-v0.9.27-brightgreen.svg)](https://github.com/treasure-data/digdag/releases/tag/v0.9.28)

digdag plugin for operating params.


# Overview

- Plugin type: operator

# Usage

```yaml

_export:
  plugin:
    repositories:
      - https://jitpack.io
    dependencies:
      - pro.civitaspo:digdag-operator-param:0.0.1

+show1:
  echo>: "${typeof(hoge) == 'undefined' ? 'None' : hoge}"

+store:
  param_store>:
    hoge: fuga

+show2:
  echo>: "${hoge}"

+reset:
  param_reset>: hoge

+show3:
  echo>: "${typeof(hoge) == 'undefined' ? 'None' : hoge}"

```

# Configuration

## Configuration for `param_store>` operator

### Options

- **param_store>**: Params to store. (string to object map, required)

## Configuration for `param_reset>` operator

### Options

- **param_reset>**: Param name to reset. (string, required)

# Development

## Run an Example

### 1) build

```sh
./gradlew publish
```

Artifacts are build on local repos: `./build/repo`.

### 2) get your aws profile

```sh
aws configure
```

### 3) run an example

```sh
./example/run.sh
```

## (TODO) Run Tests

```sh
./gradlew test
```

# ChangeLog

[CHANGELOG.md](./CHANGELOG.md)

# License

[Apache License 2.0](./LICENSE.txt)

# Author

@civitaspo

