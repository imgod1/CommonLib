# CommonLib
基础lib

使用步骤
1.把项目下载下来.导入as 能正常运行
2.创建自己的项目 导入刚才下载的项目里面的commonlib  module
3.此时运行 会报错
4.这个时候 按照demo 项目的build文件来写
  // Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    ext.kotlin_version = '1.3.31'
    ext {
        compileSdk = 28
        buildTools = "28.0.3"
        minSdk = 16
        targetSdk = 22
        libSupport = "28.0.0"

        retrofit = "2.2.0"
        rxAndroid = "2.0.0"
        arouterCompiler = "1.1.2"
        arouterApi = "1.2.1"
    }
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.4.0'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        classpath "com.tencent.bugly:tinker-support:1.1.5"
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url "https://raw.githubusercontent.com/Pgyer/mvn_repo_pgyer/master" }
        maven { url "https://jitpack.io" }
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

5.在自己的项目使用分包 因为commonlib依赖的东西挺多.直接过65k问题
 https://www.cnblogs.com/ccddy/p/5479854.html
 6.自己项目的build.gradle文件里面添加默认常量配置
   manifestPlaceholders = [
                MTA_APPKEY : "",
                MTA_CHANNEL: ""
        ]

 7.接下来问题就不大了 至于网络请求 mvp之类的 按照demo里面写的 来写就ok
 
