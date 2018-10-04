# digdag-operator-param
[![Jitpack](https://jitpack.io/v/pro.civitaspo/digdag-operator-param.svg)](https://jitpack.io/#pro.civitaspo/digdag-operator-param) [![CircleCI](https://circleci.com/gh/civitaspo/digdag-operator-param.svg?style=shield)](https://circleci.com/gh/civitaspo/digdag-operator-param) [![Digdag](https://img.shields.io/badge/digdag-v0.9.27-brightgreen.svg)](https://github.com/treasure-data/digdag/releases/tag/v0.9.28)

digdag plugin just for resetting params.


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

+store:
  param.store>:
    hoge: fuga
    hogo: '1'

+show1:
  echo>: "${hoge} ${typeof(hogo)}"

+cast:
  param.cast>:
    hogo: integer

+show2:
  echo>: "${hoge} ${typeof(hogo)}"

+reset:
  param.reset>: hoge

+show-if-exists:
  echo>: "${typeof(hoge) == 'undefined' ? 'None' : hoge}"

```

# Configuration

## Configuration for `param.store>` operator

### Options

- **param.store>**: Params to store. (string to string map, required)

## Configuration for `param.cast>` operator

### Options

- **param.cast>**: Param name and the type map. (string to string map, required)
  - The below types are available.
    - string
    - number
    - double
    - boolean

## Configuration for `param.reset>` operator

### Options

- **param.reset>**: Param name to reset. (string, required)

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

