
# 🏠 Navigation Drawer Demo App

A modern Android demo app demonstrating a **Navigation Drawer** that fetches its content dynamically from an API. Built with **Jetpack Compose**, **Clean Architecture**, and **modern Kotlin practices**.

The drawer displays the **user profile** and a list of **apps**, initially showing **four items** in the "Apps" section with a **"See More"** option to expand the list. Other sections display all items fetched from the API.  

---

## 📱 Features

* **Dynamic Navigation Drawer:** Fetches menu items and profile info from API.  
* **Profile Section:** Shows the user’s name and profile picture.  
* **Apps Section:** Initially displays 4 apps, expandable via **"See More"**.  
* **Seamless UI:** Built entirely with **Jetpack Compose**.  
* **Reactive State Management:** Uses **StateFlow** and **ViewModel** to manage UI state.  
* **Clean Architecture:** Strict separation of **Data, Domain, and Presentation** layers.  
* **Secure Token Handling:** API requests include an **Authorization token** stored securely in `BuildConfig`.  
* **Robust API Handling:** Retrofit with structured error handling ensures reliable API communication.  

---

## 🛠 Technical Expertise & Best Practices

### **Core Android & Kotlin**
* **Kotlin & Modern API:** Sealed classes for state modeling, extension functions, scope functions.  
* **Jetpack Compose:** Declarative UI, state hoisting, recomposition management.  
* **ViewModel + StateFlow:** Reactive state updates to UI, handling loading, success, and error states.  

### **Architecture & Engineering**
* **Clean Architecture (MVVM):** Decoupled layers for testable and maintainable code.  
* **Domain Layer (Use Cases):** Handles business logic outside the UI.  
* **Dependency Injection:** Hilt for injecting Repositories, API services, and interceptors.  
* **Error Handling Strategy:** Generic `Resource`/`Result` wrapper for consistent error and loading handling.  

### **Networking & Security**
* **Retrofit & OkHttp:** API integration with interceptors for secure token management.  
* **Secure Token & Base URL:** Stored in `BuildConfig` for safety and flexibility.  
* **Sealed Classes:** Encapsulate API states (Loading, Success, Error) for clean UI logic.  

---

## 📱 Screenshots 

### 🏠 Home Screen

| ![Screenshot1](https://github.com/user-attachments/assets/e1c5ff1b-7244-46df-9707-cbea0fe8576e) | ![Screenshot2](https://github.com/user-attachments/assets/99d02363-af89-49b1-bc51-95e38f78e9e8) | ![Screenshot3](https://github.com/user-attachments/assets/2c6f0b6f-44ae-4963-ae6e-cfca7a499d64) |
| :---: | :---: | :---: |







