# TestStackExchange

This simple android app connects to the StackExchangeApi users, showcasing jetpack compose and a simple dependency injection using Hilt, complete with unit tests.

## Features
- Fetches and displays a list of Stack Overflow users.
- User search/filtering by display name.
- User details view with reputation and badge counts.
- Profile image loading using Glide.
- Network error handling with a retry option.
- Pull-to-refresh.

## Tech Stack
- **Jetpack Compose**: For the UI layer.
- **Hilt**: For Dependency Injection.
- **Retrofit & OkHttp**: For network requests.
- **Glide**: For image loading.
- **KSP**: For annotation processing.
- **Mockito**: For unit testing.
- **Coroutines & Flow**: For asynchronous programming.

## Testing
The project includes unit tests for:
- `UserRepositoryImpl`: Verifying network data mapping.
- `MainViewModel`: Verifying state management, filtering logic, and error handling.
