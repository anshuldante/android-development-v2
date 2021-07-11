# Hilt

* Application class is required and should have the @HiltAndroidApp annotation.
  * This is the parent component of the app. All other components can access the dependencies that it provides.
* Activity, Fragment, View, Service, and BroadcastReceiver can use @AndroidEntryPoint to get dependencies injected.
* Application has to use @HiltAndroidApp, and ViewModel should use @HiltViewModel for it.
* If a class is annotated as above, all of the classes that depend on it should also be annotated.
* ![Component Hierarchy](resources/component-hierarchy.svg)

## Component Mappings

| Component                 | Injector for                      | Scope                      | Created At           | Destroyed At          | Default Bindings                       |
| ------------------------- | --------------------------------- | -------------------------- | -------------------- | --------------------- | -------------------------------------- |
| SingletonComponent        | Application                       | @Singleton                 | Application.onCreate | Application.onDestroy | Application                            |
| ActivityRetainedComponent | Application                       | @ActivityRetainedComponent | Activity.onCreate    | Activity.onDestroy    | Application                            |
| ViewModelComponent        | ViewModel                         | @ViewModelScoped           | View created         | ViewModel Destroyed   | SavedStateHandle                       |
| ActivityComponent         | Activity                          | @ActivityScoped            | Activity.onCreate    | Activity.onDestroy    | Application, Activity                  |
| FragmentComponent         | Fragment                          | @FragmentScoped            | Fragment.onAttach    | Fragment/onDestroy    | Application, Activity, Fragment        |
| ViewComponent             | View (with @WithFragmentBindings) | @ViewScoped                | View.super           | View Destroyed        | Application, Activity,. Fragment, View |
| ViewWithFragmentComponent | View                              | @ViewScoped                | View.super           | View destroyed        | Application, Activity, Fragment, View  |
| ServiceComponent          | Service                           | @ServiceScoped             | Service.onCreate     | Service.onDestroy     | Application, Service                   |

* Hilt Managed View models must be created via the ViewModelProvider API.
* ![Annotation Cheatsheet](resources/hilt-cheatsheet.svg)
