
## Get pre-commit:

To get them running for the first time (meaning doing this once), run:

```sh
$ npx dwmkerr/standard-version --first-release --packageFiles pom.xml --bumpFiles pom.xml
```

## Run Tests:

```ssh
# project root
$ cd /integrated-project && cp env-example .env

$ docker compose up -d

# get inside the container
$ docker compose exec backend bash
$ mvn test
```

#### you should see something similar:
```
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 19.166 s - in com.example.back.BackApplicationTests
[INFO] Running com.example.back.ProductControllerTest
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.662 s - in com.example.back.ProductControllerTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 14, Failures: 0, Errors: 0, Skipped: 0
[INFO]

[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 14, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  43.339 s
[INFO] Finished at: 2023-11-16T16:44:52Z
[INFO] ------------------------------------------------------------------------
```

