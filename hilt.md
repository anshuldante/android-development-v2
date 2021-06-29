# Hilt

* Application class is required and should have the @HiltAndroidApp annotation.
  * This is the parent component of the app,all other components can access the dependencies that it provides.
* Activity, Fragment, View, Service, and BroadcastReceiver can use @AndroidEntryPoint to get dependencies injected.
* Application has to use @HiltAndroidApp, and ViewModel should use @HiltViewModel for it.
* If a class is annotated as above, all of the classes that depend on it should also be annotated.
* 