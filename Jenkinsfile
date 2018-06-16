node {
    def DOCKERHUB_REPO = "dpatel42/trucker"
    def DOCKER_SERVICE_ID = "trucker-service"
    def DOCKER_IMAGE_VERSION = ""

    stage("clean workspace") {
        deleteDir()
    }

    stage("git checkout") {
        checkout scm

        def GIT_COMMIT = sh(returnStdout: true, script: "git rev-parse HEAD").trim().take(7)
        DOCKER_IMAGE_VERSION = "${BUILD_NUMBER}-${GIT_COMMIT}"
    }

    stage("mvn build") {
        sh "mvn clean install"
    }

    stage("docker build") {
        sh "sudo docker build -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_VERSION} ."
    }

    stage("docker push") {
        withDockerRegistry(credentialsId: 'dockerhub2') {
            sh "sudo docker push ${DOCKERHUB_REPO}:${DOCKER_IMAGE_VERSION}"
        }
    }

    stage("docker service") {
        try {
            // Create the service if it doesn't exist otherwise just update the image
            sh """
                if [ \$(sudo docker service ls --filter name=${DOCKER_SERVICE_ID} --quiet | wc -l) -eq 0 ]; then
                  sudo docker service create \
                    --replicas 1 \
                    --name ${DOCKER_SERVICE_ID} \
                    --publish 8080:8080 \
                    --secret spring.datasource.url \
                    --secret spring.datasource.username \
                    --secret spring.datasource.password \
                    ${DOCKERHUB_REPO}:${DOCKER_IMAGE_VERSION}
                else
                  sudo docker service update \
                    --image ${DOCKERHUB_REPO}:${DOCKER_IMAGE_VERSION} \
                    ${DOCKER_SERVICE_ID}
                fi
            """
        }
        catch (e) {
            sh "sudo docker service update --rollback ${DOCKER_SERVICE_ID}"
            error "Service update failed. Rolling back ${DOCKER_SERVICE_ID}"
        }
        finally {
            sh "sudo docker container prune -f"
            sh "sudo docker image prune -af"
        }
    }
}