====================
AndroidAppWithVolley
====================

Volleyを使ったサンプルアプリ

Volleyの設定
=============

.. code-block::

 $ git submodule add https://android.googlesource.com/platform/frameworks/volley volley
 $ git submodule init
 $ git submodule update

app/build.gradle

.. code-block::

 diff --git a/app/build.gradle b/app/build.gradle
 index b61e017..800394b 100644
 --- a/app/build.gradle
 +++ b/app/build.gradle
 @@ -21,4 +21,5 @@ android {
  
  dependencies {
      compile fileTree(dir: 'libs', include: ['*.jar'])
 +    compile project(':volley')
  }

settings.gradle

.. code-block::

 diff --git a/settings.gradle b/settings.gradle
 index e7b4def..53c6f39 100644
 --- a/settings.gradle
 +++ b/settings.gradle
 @@ -1 +1,2 @@
  include ':app'
 +include ':volley'

volley/build.gradle

.. code-block::

   compileSdkVersion 19
  +buildToolsVersion = "19.1.0"
  -buildToolsVersion = 19

参考サイト
==========

http://d.hatena.ne.jp/gfx/20140219/1392789428
