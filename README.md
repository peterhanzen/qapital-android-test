#Qapital Android Test Task

###Libraries used in the project

* [Retrofit](http://square.github.io/retrofit) Turning the HTTP API into a Java interface
* [OkHttp](http://square.github.io/okhttp) Efficient and reliable HTTP networking
* [Gson](https://github.com/google/gson) POJO <=> JSON
* [Timber](https://github.com/JakeWharton/timber) Lazy logging
* [Stetho](http://facebook.github.io/stetho) Debug bridge for Chrome ...
* [Picasso](http://square.github.io/picasso) Async image fetching, caching, ...
* [RoboSpock](https://github.com/robospock/RoboSpock) Unit testing (Spock + Groovy)
* [RxJava](https://github.com/ReactiveX/RxJava) For asynchronous and event-based applications


###Running the app

* cd ?
* git clone ...
* cd qapital-android-test/Qapital
* git checkout develop
* ``` ./gradlew clean build```
* _Start your emulator or connect phone_
* adb install QapitalApp/build/outputs/apk/debug/QapitalApp-debug.apk

###Completion

I got the task on Friday, and as earlier mentioned to Örjan, I have my daughter (3 yrs) this week, so time is
a bit limited. Might be able to finish something later in the week, not sure. For this reason, I haven't
included Google Databinding (or the optional DB or UI testing).

Had a quick look at binding and ran in to some problems with list-items, not sure yet how to solve it.
At a first glance, it didn't seem worthwile when only reading from model -> view, I'd probably prefer
using e.g. Butterknife to simplify binding view <-> holder and boil a bit (: But I realize it's a test
and it's probably neat when data is going both ways, model <-> view.

Also had a glance CRUDb using greenDAO (but should probably be using ObjectBox) and got something close
to working, but the structure got too convoluted with extra wrapper objects to objectify lists.
I'm probably just a bit too tired, normally I'm pretty good with RDBMs.

Anyways, a project written in Java, using RxJava, containing unit test (and Retrofit, OkHttp, Gson, 
Timber, Stetho, Gson, Picasso). The structure of the project is a bit overkill, considering the scope
of the app, but it had bigger things in mind (;

BR
Peter