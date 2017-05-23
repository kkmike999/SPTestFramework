# SPTestFramework

[![](https://www.bintray.com/docs/images/bintray_badge_color.png)](https://bintray.com/kkmike999/maven/sptest?source=watch)

SPTestFramework是一款简洁的Android SharedPreferences单元测试框架。

## 引用

1.在project build.gradle增加maven仓库地址：
```
allprojects {
    repositories {
        jcenter()
        maven { url 'https://dl.bintray.com/kkmike999/maven' }
    }
}
```

2.在mudle build.gradle加入引用：

```
dependencies {
    testCompile 'net.kkmike.sptest:sptest:0.5'
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