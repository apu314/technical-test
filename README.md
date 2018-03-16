# technical-test
A rest service created with Spring Boot (Java 8) in the backend, and Nuxtjs (Vuejs with ES6) with Vuetifyjs for the frontend

## Setup Vue.js & Spring Boot

### Prerequisites

#### MacOSX

```
brew install node
npm install --global vue-cli
```

#### Linux

```
sudo apt update
sudo apt install node
npm install --global vue-cli
```

#### Windows

```
choco install npm
npm install --global vue-cli
```

(More info about Nodejs: https://nodejs.org/en/download/)

## Project setup

```
technical-test
├─┬ backend     → backend module with Spring Boot stuff
│ ├── src
│ └── pom.xml
├─┬ frontend    → frontend module with Vue.js stuff
│ └── pom.xml
└── pom.xml     → Maven parent pom with modules
```

## First App run

```
mvn clean install
```

Run our complete Spring Boot App:

```
mvn --projects backend spring-boot:run
```

Spring API server running in http://localhost:8088/

Run our Vuejs (Nuxtjs) app with ssr:

```
cd frontend
npm install (if /frontend/node_modules directory not exists)
npm run nuxt build 
npm run nuxt start
```
