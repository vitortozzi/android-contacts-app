# Simple Contacts

A clean and intuitive Android application for managing your personal contacts, built with modern Android development tools and best practices.

## Features

- **Contact List:** View all your saved contacts in a sleek, scrollable list.
- **Contact Details:** See detailed information for each contact.
- **Persistent Storage:** Your contacts are saved locally and persist across app restarts.
- **Modern UI:** Built entirely with Jetpack Compose and Material 3 design principles.

## Tech Stack

- **Language:** [Kotlin](https://kotlinlang.org/)
- **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Dependency Injection:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Local Database:** [Room](https://developer.android.com/training/data-storage/room)
- **Navigation:** [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- **Architecture:** MVVM (Model-View-ViewModel) with a Repository pattern.
- **Build System:** Gradle Kotlin DSL

## Project Structure

The project follows a clean architecture approach, organized by layer:
- `data`: Database definitions (Room), DAOs, and repository implementations.
- `di`: Dependency Injection modules (Hilt).
- `domain`: Core business logic, entities, and repository interfaces.
- `ui`: Presentation layer with Compose screens and ViewModels.
- `ui/theme`: App-wide theming and styling.

## Getting Started

1. Clone this repository.
2. Open the project in **Android Studio (Ladybug or newer)**.
3. Sync the project with Gradle files.
4. Run the app on an emulator or a physical device.

---

Built with ❤️ using Jetpack Compose.
