## Android-LockScreen ##

### 应用图标 ###

![image](https://github.com/aspook/Android-LockScreen/raw/master/screenshot/ic_launcher.png)

### 实现原理 ###

参考百度锁屏、Go锁屏的原理，实现了上述各版本通用的锁屏示例app。支持“屏蔽”Android 4.0+的Home键，注意并非是真正的屏蔽，因为4.0之后，系统不允许在应用层屏蔽Home键，这里只是采用了一种巧妙的方式来绕过，就像是屏蔽了Home键一样。

PS：Android 6.0上，此方法Home键也不能完美起作用，百度锁屏在6.0上跟本demo效果类似，而Go锁屏在6.0上直接崩溃（测试机Nexus 6P）。

详细的原理及实现可参考[本人博客](http://blog.csdn.net/ahence/article/details/25400911)。

### 锁屏效果 ###

 ![image](https://github.com/aspook/Android-LockScreen/raw/master/screenshot/android_lock_screen.jpg)
