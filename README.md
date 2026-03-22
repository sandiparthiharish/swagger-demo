# 🚀 Swagger Demo — Kotlin + Spring Boot + OpenAPI

A modern **Spring Boot (Kotlin)** project demonstrating how to design, generate, and implement APIs using **OpenAPI (Swagger)** with support for **polymorphic request/response models**.

---

## ✨ Features

* 🧩 OpenAPI-first API design
* 🔄 Code generation using OpenAPI Generator (`kotlin-spring`)
* 🔀 Polymorphic models using **discriminator + inheritance (`allOf`)**
* ⚡ Spring Boot 4 + Kotlin + Jakarta support
* 🧪 Clean separation between generated DTOs and business logic
* 📦 Ready for REST API development with validation

---

## 🏗️ Tech Stack

* **Kotlin**
* **Spring Boot 4**
* **OpenAPI 3**
* **OpenAPI Generator (kotlin-spring)**
* **Jackson (Kotlin module)**
* **MongoDB (optional integration)**

---

## 📁 Project Structure

```
swagger-demo/
├── src/main/kotlin/com/harish/
│   ├── api/           # Generated API interfaces
│   ├── model/         # Generated DTOs
│   ├── controller/    # Your implementations
│   ├── service/       # Business logic
│   └── config/        # Configurations (Jackson, etc.)
│
├── src/main/resources/
│   └── swagger.yml    # OpenAPI specification
│
└── pom.xml            # Maven configuration
```

---

## 🔧 OpenAPI Code Generation

This project uses **OpenAPI Generator** to generate:

* API interfaces
* Request/Response DTOs
* Validation annotations

### ▶️ Generate Code

```bash
mvn clean generate-sources
```

Generated code will be available under:

```
target/generated-sources/openapi
```

---

## 🧠 API Design Approach

### ❗ Key Design Decisions

* ❌ Avoid `oneOf` in endpoints (causes model flattening in Kotlin)
* ✅ Use `discriminator` + base schema
* ✅ Use `allOf` for inheritance

---

### ✅ Example: Polymorphic Request

```yaml
PaymentRequest:
  type: object
  required:
    - type
    - amount
  properties:
    type:
      type: string
    amount:
      type: number
  discriminator:
    propertyName: type
```

---

### ✅ Subtypes

```yaml
CardPaymentRequest:
  allOf:
    - $ref: '#/components/schemas/PaymentRequest'
    - type: object
      properties:
        cardNumber:
          type: string
```

---

## 🔀 Polymorphism Handling

Generated models are interfaces, so we recommend:

### ✅ Use a sealed domain model

```kotlin
sealed interface PaymentRequest {
    val type: String
    val amount: Double
}
```

### 🔁 Map DTO → Domain

```kotlin
fun CreatePaymentRequest.toDomain(): PaymentRequest = when (this) {
    is CardPaymentRequest -> CardPayment(type, amount, cardNumber)
    is PaypalPaymentRequest -> PaypalPayment(type, amount, email)
    else -> error("Unknown type")
}
```

---

## ▶️ Running the Application

```bash
mvn spring-boot:run
```

---

## 📬 API Testing

Once the app is running:

👉 Swagger UI available at:

```
http://localhost:8080/swagger-ui.html
```

---

## ⚠️ Common Pitfalls

| Issue                          | Fix                         |
| ------------------------------ | --------------------------- |
| `javax` imports                | Enable `useJakartaEe=true`  |
| Broken polymorphism            | Avoid `oneOf`               |
| Jackson deserialization errors | Add `jackson-module-kotlin` |
| Missing discriminator          | Ensure `type` is required   |

---

## 🧪 Sample Request

```json
{
  "type": "card",
  "amount": 100,
  "cardNumber": "1234"
}
```
---

## 🤝 Contributing

Feel free to fork the repo and raise PRs. Suggestions and improvements are always welcome!

---

## 💡 Final Note

This project highlights real-world challenges when combining:

* OpenAPI
* Kotlin
* Spring Boot

…and shows **practical patterns to avoid common pitfalls** and build clean, maintainable APIs.

---

Happy coding! 🚀
