# Communication Service

A domain designed communication service built with Java and Spring Boot. This service is designed to be event driven; however, is not configured due to budget limitations. This service uses [Doppler](https://www.doppler.com/) for secure environment variable management and is containerized using Docker.

---

## 🧱 Tech Stack

- Java 21
- Spring Boot
- Gradle
- Docker
- Doppler (for secret management)

---

## 🚀 Getting Started

### Prerequisites

- [Docker](https://docs.docker.com/get-docker/)
- A valid [Doppler token](https://docs.doppler.com/docs/enclave-tokens)

---

### 🐳 Build and Run with Docker

```bash
docker build -t communication-service \
  --build-arg DOPPLER_TOKEN=<your-doppler-token> \
  --build-arg SPRING_PROFILES_ACTIVE=prod \
  --build-arg CONTACT_EMAIL_GROUP=support@yourdomain.com \
  .
```

Then run the container:

```bash
docker run -p 8080:8080 communication-service
```

The service will be available at `http://localhost:8080`.

---

## 🌱 Required Environment Variables (via Doppler)

| Variable                         | Description                                 |
|----------------------------------|---------------------------------------------|
| `MONGO_URI`                      | MongoDB connection string                   |
| `SENDGRID_API_KEY`               | SendGrid API key for sending emails         |
| `CONTACT_EMAIL_GROUP`           | Recipient email address for personal emails |
| `TWILIO_SID`                     | Twilio Account SID                          |
| `TWILIO_MESSAGING_SERVICE_SID`   | Twilio Messaging Service SID                |
| `TWILIO_AUTH_TOKEN`              | Twilio Auth Token                           |
| `OPENAI_API_KEY`                 | OpenAI API key for content moderation       |
| `AUTH_ISSUER`                    | Okta issuer URL                             |
| `AUTH_AUDIENCE`                  | Okta audience ID                            |

These variables are securely injected using Doppler at both build and runtime.

---

### 🧪 Local Development (Optional)

To run the app outside of Docker for testing:

```bash
doppler run -- ./gradlew bootRun
```

---

## 📦 Build Output

The application is built into a JAR file located at:

```
build/libs/Communication-Service-0.0.1-SNAPSHOT.jar
```

This JAR is what gets executed inside the container.

---

## 🧼 Cleanup

To reduce image size, the Dockerfile removes Gradle files after building:

```dockerfile
RUN rm -rf gradle
```

---

## 📬 Contact

Maintained by [Trenton Rush](https://www.trentonrush.com)

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
