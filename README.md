# Android MVVM Architecture: FCTAndroidCodeChallenge

This is a movie and series application, where you can see the top rated movies, random trailer and select genre to see the list of series.

## The application has the following packages per stream:

<img width="3014" alt="image" src="https://github.com/luiseos-B2/App-Movies/assets/46486737/b3a3cf03-81a9-4a2e-b579-31448865b082">

1.  **data**: It contains all the data accessing and manipulating components.
2.  **di**: Dependency providing classes using Koin.
3.  **domain**: Contains all use cases for your application.
4.  **presentation**: View classes along with their corresponding ViewModel.

## In the project we have two packages following the architecture:

- **core**: contains features and structure that will be shared between feature packs

- **home**: we have our first feature, containing movie screen, series screen and details screen

## Future project improvements:

### Modularization:
The project is just starting, and we don't have many problems for now. But thinking about the future having modules for features will help a lot, having a performance at build time, separate features and building module buildSrc to manage dependencies.

### Design System
Have a well-defined design system, with color palettes, typrography and components

### UI tests

Create a framework for unit tests and UI tests with roboletric.

### Structure CI/CD
Create and organize CI/CD, IC running project and tests on project development and CD with application distribution process 