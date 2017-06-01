# SPTestFramework

[![](https://www.bintray.com/docs/images/bintray_badge_color.png)](https://bintray.com/kkmike999/maven/sptest?source=watch)

SPTestFramework是一款简洁的Android SharedPreferences单元测试框架。

## 引用

1.在project build.gradle增加maven仓库地址：
```
allprojects {
    repositories {
        jcenter()
        
        // 目前0.5版本已发布jcenter，可以不加bintray url
        // maven { url 'https://dl.bintray.com/kkmike999/maven' }
    }
}
```

2.在mudle build.gradle加入引用：

```
dependencies {
    compile 'org.mockito:mockito-all:2.0.2-beta'
    compile 'junit:junit:4.12'
    
    testCompile('net.kkmike.sptest:sptest:0.5') {
        transitive = true
        exclude module: 'junit', group: 'junit'
        exclude module: 'mockito-all', group: 'org.mockito'
        exclude module: 'mockito-core', group: 'org.mockito'
        exclude module: 'support-annotations', group: 'com.android.support'
    }
}
```

## Java代码

单元测试：
```
public class MyTest extends SharedPrefCase {

    SharedPreferences mSharedPref;
    
    @Before
    public void setUp() throws Exception {
        mSharedPref = getInstance("name");
        // 或者 :
        // mSharedPref = SharedPreferencesHelper.newInstance();
    }
}
```
