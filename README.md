# Tasklist Debugging

Example repository for debugging learning
from [this video](https://www.youtube.com/watch?v=FGbzHhioARQ)

Here are two problems in this example code. Let's examine bug reports and fix
them.

## Problem: Bug during login

### Description

When I try to register user, it is registered. When I try to login to this user,
I see "Unauthorized1"
from [ApplicationConfig:60](src/main/java/com/example/tasklist/config/ApplicationConfig.java).

### Steps to reproduce:

1. Run `docker compose up` to run a database
2. Run application
3. Register user - POST request to `http://localhost:8080/api/v1/auth/register`
   with JSON body:

```
{
    "name": "mark",
    "username": "markov",
    "password": "12345",
    "passwordConfirmation": "12345"
}
```

4. Login to this user - POST request
   to `http://localhost:8080/api/v1/auth/login`
   with JSON body:

```
{
    "username": "mark
    "password": "12345"
}
```

5. See "Unauthorized1"

### Expected behavior

Get `JwtResponse` object with tokens.

### Actual behavior

Get `Unauthorized1`

***

Try to solve this problem during debugging process. I have some hints for you:

1) Create `@RestControllerAdvice` class, handle exception and print stack trace
2) Find causes and code lines in stack trace
3) Fix the first problem
   in [UserRowMapper](src/main/java/com/example/tasklist/repository/mappers/UserRowMapper.java)
4) You need to add `if (resultSet.next()) {` just before line `user.setId(resultSet.getLong("user_id"));`
5) Try to login again - you get new exception
6) Fix the second problem
   in [JwtTokenProvider](src/main/java/com/example/tasklist/web/security/JwtTokenProvider.java).
   See actual
   code [here](https://github.com/SpringBootCourses/tasklist/blob/main/src/main/java/com/example/tasklist/web/security/JwtTokenProvider.java)
7) Try to login again - all should work!

See [this video](https://www.youtube.com/watch?v=FGbzHhioARQ) for more details.