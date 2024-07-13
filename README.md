# build-conventions

Common gradle's build conventions that I use.

## Setup

First, add the reposity as git submodule.

```shell
git submodule add --branch=master git@github.com:mpetuska/build-conventions.git
```

Then create symbolic links for shared files.

### Gradle Wrapper

```shell
mkdir -p ./gradle
cd ./gradle
ln -s ../build-conventions/gradle/wrapper ./wrapper
cd ..
ln -s ./build-conventions/gradlew ./gradlew
```

### License

```shell
ln -s ./build-conventions/LICENSE ./LICENSE
```

### Dependabot

```shell
mkdir -p ./.github
cd ./.github
ln -s ../build-conventions/.github/dependabot.yml ./dependabot.yml
cd ..
```
