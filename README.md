# README
## Bank Api app
Java version 22 \
Spring boot version - 3.3.1

## Open swagger docs

http://localhost:8080/swagger-ui/index.html#

## Examples queries
input date format for all queries- yyyy-MM-dd

http://localhost:8080/api/v1/preload?date=2020-08-03 \
Response: \
    Success : Success preload rates\
    Error : Something went wrong

http://localhost:8080/api/v1/rate?date=2024-03-03&currencyCode=USD \
Response: \
Success : 2024-03-03 за 1 доллар сша надо было отдать 3.2179 рублей\
Error : Something went wrong