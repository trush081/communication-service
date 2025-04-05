FROM gradle:jdk21

LABEL authors="trentonrush"
WORKDIR /app

COPY . .

# Set up Doppler
RUN curl -sLf --compressed "https://cli.doppler.com/install.sh" | sh

ARG DOPPLER_TOKEN
ENV DOPPLER_TOKEN=${DOPPLER_TOKEN}

# Set Up other env variables
ARG SPRING_PROFILES_ACTIVE
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
ARG CONTACT_EMAIL_GROUP
ENV CONTACT_EMAIL_GROUP=${CONTACT_EMAIL_GROUP}

RUN doppler run -- gradle build

EXPOSE 8080

RUN rm -rf gradle

CMD ["doppler", "run", "--", "java", "-jar", "build/libs/Communication-Service-0.0.1-SNAPSHOT.jar"]