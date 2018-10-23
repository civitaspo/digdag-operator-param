# digdag-operator-param
[![Jitpack](https://jitpack.io/v/pro.civitaspo/digdag-operator-param.svg)](https://jitpack.io/#pro.civitaspo/digdag-operator-param) [![CircleCI](https://circleci.com/gh/civitaspo/digdag-operator-param.svg?style=shield)](https://circleci.com/gh/civitaspo/digdag-operator-param) [![Digdag](https://img.shields.io/badge/digdag-v0.9.30-brightgreen.svg)](https://github.com/treasure-data/digdag/releases/tag/v0.9.30)

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
  echo>: "hoge: ${typeof(hoge) == 'undefined' ? 'None' : hoge}, a.b: ${typeof(a) == 'undefined' ? 'None' : typeof(a.b) == 'undefined' ? 'None' : a.b}"

+store:
  param_store>:
    hoge: fuga
    a:
      b: c

+show2:
  echo>: "hoge: ${typeof(hoge) == 'undefined' ? 'None' : hoge}, a.b: ${typeof(a) == 'undefined' ? 'None' : typeof(a.b) == 'undefined' ? 'None' : a.b}"

+reset:
  +hoge:
    param_reset>: hoge
  +a.b:
    param_reset>: a.b

+show3:
  echo>: "hoge: ${typeof(hoge) == 'undefined' ? 'None' : hoge}, a.b: ${typeof(a) == 'undefined' ? 'None' : typeof(a.b) == 'undefined' ? 'None' : a.b}"

+eval:
  _export:
    a: aaa
    b: bbb
    c: ccc
    d: ${a}-${b}-${c}

  +a:
    _export:
      e: ${d}
      f:
        g: ${d}
        h:
          - ${d}
    +b:
      echo>: ${d}
    +c:
      echo>: ${e}
    +d:
      echo>: ${f.g}
    +e:
      param_eval>: f.g
    +f:
      echo>: ${f.g}
    +g:
      +h:
        for_each>: {i: "${f.h}"}
        _do:
          echo>: ${i}
      +i:
        echo>: ${f.h}
    +j:
      param_eval>: f.h
    +k:
      +l:
        for_each>: {i: "${f.h}"}
        _do:
          echo>: ${i}
      +m:
        echo>: ${f.h}

```

# Configuration

## Configuration for `param_store>` operator

### Options

- **param_store>**: Params to store. (string to object map, required)

## Configuration for `param_reset>` operator

### Options

- **param_reset>**: Param name to reset. (string, required)

## Configuration for `param_eval>` operator

### Options

- **param_eval>**: Param name to eval. (string, required)
  - **NOTE**: This operator is a workaround for the issue: [Exported vars are not evaluated recursively in the context of nested params](https://github.com/treasure-data/digdag/issues/862)

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

