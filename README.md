# ys sdk

### installation
```
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    compile("com.github.noob9527:ys-sdk:master-SNAPSHOT")
    // ...
}
```
### getting started
if you are using spring boot, related service are autoconfigure, otherwise you have to create them on your own

### configuration
| key | required | default |
| - | - | - |
| ys.app-key    | true ||
| ys.app-secret | true ||
| ys.api-url    | true | https://open.ys7.com/api/lapp/ |
| ys.log-level  | true | NONE |
