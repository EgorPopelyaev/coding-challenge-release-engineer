# eBayK Release Engineer challenge - Market place application backend

## Introduction
Most tasks will be related to a marketplace application. Your exercise is to analyze and test the application. Additionally, you will create a release pipeline. There are 3 independent required tasks and one optional task (4) that should be solved. You can adapt everything, add libraries and other tools if this makes your life easier.

## Preparation (~15min)

1. Make sure that the following is installed and ready to be used:

    - Java Development Kit version >=8
    - Maven
    - Your preferred IDE/editor
    - Docker

2. Check if your backend service can properly run

    ```
    mvn spring-boot:run
    ```

The application runs if you get a valid response from [http://localhost:8080/ad-single](http://localhost:8080/ad-single). Alternatively, you can also start the main class `com.ebayk.Application` in your preferred IDE.

Before you start, keep in mind that you can skip parts of the challenge if you are stuck. If you have anything to comment then feel free to add comments at certain places. You can also add comments at the end of this file about general things, problems and remarks. If the time is short, it's also useful to describe quickly what you would have done in a required task that was not completely implemented.

After **5 hours** you have to upload the result as .zip file to a public file hoster like [https://filebin.net/](https://filebin.net/). You have 5 hours to solve the challenge. The exercises are feasible in less time but we added some buffer to make it less stressful. Don't panic if you are not able to solve all challenges, just try to find the best solution with your knowledge/experience.

No need to be hyper-punctual (e.g. 10 minutes earlier or later won't make a difference).


## Tasks

### 1. Write a unit test and improve testability (~45m)

Start the server and open [http://localhost:8080/users/3/rated-users](http://localhost:8080/users/3/rated-users). There, you see a list of all users that were rated by the user with id `3`.

The business functionality is implemented in `com.ebayk.service.RatingAnalyzer` and the Rest controller is located in `com.ebayk.UserController`.
Implement unit tests for both classes. Refactor the code as is suits you, especially to improve testability.

### 2.1. Create a pipeline for building the docker image for the application (~60m)

There is already a `Dockerfile` present in `src/main/docker`, which you may adjust as you need. Start a local Jenkins and set up a build job in Jenkins that builds the application and Docker image locally (no need to push it to a remote repository). Document how you started the Jenkins locally, the setup steps you needed (if any) and add the Jenkinsfile of the pipeline (as you created it for this task) to the solution.

### 2.2. Add end-to-end tests (~60m)
Complete the build pipeline with automated end-to-end tests, that test the API of the service's `rated-users` endpoint. You may adjust the service to facilitate testing. Please also include
all steps you would need to bring the application to production using this pipeline. Deployments can be faked by printing
some messages or starting and stopping the Docker container. Please add the resulting Jenkinsfile to the solution (with a different name than the one from the task before).

### 3. Writing browser-based tests (~30m)
Write a browser-based test, that verifies that users can reach and see the login form on <https://www.ebay-kleinanzeigen.de>. You can use your any programming language for this, if unsure you can default to Java. You can even include it in the tests/pipeline for the marketplace application, if this makes your life easier. Just make sure to document how to run the test.

### 4. Optional: complete the pipeline
Get creative and think about what may be missing in your deployment pipeline to make it production-ready. Write down what steps could be added and outline how you would integrate them.

# Notes

This space intentionally left blank for you notes and feedback!

### 1.
I