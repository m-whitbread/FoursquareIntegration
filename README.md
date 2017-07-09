### MVP:
I made use of the MVP architectural pattern. I tend to use this for projects due to how it makes unit testing of business logic significantly easier.

The style of MVP I use is based on the one recommended by Google through their android architecture samples ( https://github.com/googlesamples/android-architecture ).

### UI:
I conciously focused on the functional requirement for this test and didn't put too much focus on UI design.

### Checkstyle:
I added a checkstyle.xml from the start which ensures all code adheres to the agreed coding style. Builds will immediately fail with a checkstyle error if there is a problem.

### RxJava:
I make use of RxJava 2 Observables and Subjects for handling some asynchronous behaviour, including network responses.

I also use Subjects instead of onclicklistener callbacks.

### Testing:
The core logic of the presenter is unit tested, and I also added an Espresso test for checking that comics are being displayed to the screen, which ensures the end-to-end flow from presenter to UI is working.

I make use of MockWebServer and Jake Wharton's okhttp3 idling resource to mock network responses and to also avoid flaky tests that would otherwise be caused by the asynchronous responses.

I also use a custom rule to set all RxJava schedulers to run on the main thread during testing, which also avoids problems with asynchronous testing.


### What I would do next:
- The app currently treats all network request errors as the same. The next step would be to wrap the response in a Response object so that the app can provide more detailed errors to the user - for example, notify them if they do not have a network connection, if there was a server error, etc.

Also the app currently depends on the user inputting a valid location. If an invalid location is provided, a '400 Bad Request' error is shown. I would update this to be more descriptive.

### Issues / Improvements:
- At the moment the main network logic is in the _VenueSearchPresenter_, however as the app grows, this would move into a seperate service layer where common code can be extracted (such as error handling and threading).

- If this was a real project, I would set up a Continuous Integration server once the first screen was finished with tests, ensuring that the server is building the app (in both debug and release buildtypes) and that the tests are also being run correctly.
