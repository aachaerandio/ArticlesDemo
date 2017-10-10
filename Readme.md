
This Android app shows a list of items and its details. 

I architected the app following the MVP pattern, having a presenter layer that serves the data from the Domain (API) to the view. 
I have used Google's Dagger 2 for dependency injection as it is recommended and RxJava 2 for a reactive approach.

Unit tested the presentation layer of the MVP, mocking out the dependencies using Mockito.

### Improvements
- Add more testing.
- Use data binding like Android data binding. I normally use Butterknife, but it is a third party library.
- Custom error messages per error type. 