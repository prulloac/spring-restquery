mvn clean verify sonar:sonar \
  -Dsonar.projectKey=springdata-extras \
  -Dsonar.host.url=${SONAR_URL} \
  -Dsonar.login=${SONAR_TOKEN}
