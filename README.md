# Github Profile
Github Profile is a simple Android application which uses Github API that allows users to view the list of Github users and their detail information.
- User list screen (with pagination)
- User detail screen

## **1. Architecture**
Using Clean Architecture and MVVM, the application provides a clean way to maintain and easy to scale up as well as writing unit test. It contains 3 layers:
- Data
- Domain
- Presentation (app)

The overview architect look like this:

![image](https://user-images.githubusercontent.com/21035435/69536839-9f4c8e80-0fa0-11ea-85ee-d7823e5a46b0.png)

## **2. Third-party libraries and technique**
Below is the list of third-party libraries and technique that I used in the project:

- **AndroidX**
- **Jetpack Compose**
- **Coroutines**
- **Retrofit**
- **OkHttp**
- **Moshi**
- **Dagger Hilt**
- **Room**
- **Coil**
- **Timber**
- **Chucker**
- **Junit**
- **Mockk**
- **Turbine**
- **Firebase**

## **3. Build the project on local**
- Please contact me to get the `google-services.json` and paste it in the `app` folder
- After cloning the repo, open Android Studio and update your BASE_API_URL in `environment.properties` file.
- Run build

Thank you and have a nice day!
