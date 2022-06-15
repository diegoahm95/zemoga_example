# zemoga_example
Zemoga project

This is a test repository for zemoga application. Created app will fetch data from a test API and show it in a list. Users can enter to detail screen,
set items as favorites or remove them.
There are two floating buttons in main screen, a 'Remove All' button and a 'Restore All' button.

Architecture:
-App uses MVVM. 
-Fragments are extending a BaseFragment capable of inflating views and removing some boilerplate from screens. They also observe a UIState which determinates how view
  behaves. Per time issues only adapters for list use databinding for showing views, they also extend from a generic BaseAdapter.
-Viewmodel extends ViewModel class from Lifecycle Jetpack library. They use coroutines to access repositories and request data in an async way. 
  They contain a single livedata item which holds UIState.
-Model is structured by repositories and their respective datasources (a remote one using Retrofit and a local one using Room).
-Most of dependencies are provided using Dagger.
-Navigation is done using Jetpack's Navigation Component. Boilerplate for fragment transactions is inexistent. 

Some pros:
-Views handle data but don't get it or manipulate it. This allows that 'observe' of UIState acts as the only entry point for view. Also, this contribute to 
  properly update views from a single object which can be easily copied in order to avoid lost information when modifying state.
-ViewModels are handling most of process with coroutines, so it is easier to have more predictable process even with a lot of 'async' calls. As coroutines handle
  this without any callbacks, process are linear and easy to read and understand.
-Single UIState per screen allows viewmodels to care only about a livedata object providing a more readable and manageable code. Kotlin allows a very useful way
  to update this state through 'copy' method.  
-Repositories only cares about data. They are required to get some data and easily determinate which would be the best source depending on the case. Also they are
  easily injected through dagger and use coroutines to be manipulated on different threads.
-Base classes such as BaseAdapter and BaseFragment provides an easy way to start developing particular features. Abstract methods and interfaces are also used to
  make sure that child classes properly set required information.
  
Some considerations:
- Fragments are not using databinding. However, this could be a good step further since could improve usability of xml files and reduce unnecesary manual bindings.
- Instead of directly adding favorites and deletions to Post classes, there was created a particular class for every case. This could be improved, however it was
  created like this since API didn't provide endpoints for similar process. Deletion particular case could be handled in a better way, and could be a good improvement
  if added + a cache solution for posts.
