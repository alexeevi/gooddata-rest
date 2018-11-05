# GoodData REST API homework by Ivan Alekseev

How to start the application
---

1. Run `mvn clean install` to build the application
2. Start application with `java -jar target/gooddata-rest-1.0-SNAPSHOT.jar server`
3. To check that the application is running enter url `http://localhost:8080`

Available API
---

**Words**

`POST /words/{category}/{word}` adds given _word_ into the dictionary with the given _category_. Where _category_ is one of these: `noun`, `verb`, `adjective`.
Responses with JSON for the given word:
* `word` - string representation of the word
* `category` - dictionary category
Responses with 400 status if provided category is not correct.

`GET /words` provides set of available words in the dictionary.

`GET /words/{word}` provides information about the given _word_.
Responses with 404 status if given word was not found.


**Sentences**

`POST /sentences/generate` generates new sentence based on one noun, verb and adjective randomly taken from dictionary of known words.
Responses with JSON representing the sentence:
* `id` - identifier (long number)
* `displayCount` - quantity of cases when the sentence was displayed after specific `GET` request
* `reGenerateCount` - quantity of cases when the same sentence was generated
* `dateCreated` - date/time of creation
* `noun` - word of noun category
* `verb` - word of verb category
* `adjective` - word of adjective category
Responses with 400 status if sentence could not be generated due to absence of at least one word category in the dictionary. 

`GET /sentences` provides set of previously generated sentences.

`GET /sentences/{id}` provides information for the sentence with the given _id_.
Responses with 404 status if sentence with given id was not found.

`GET /sentences/{id}/yodaTalk` provides information for the sentence with the given _id_, but with Yoda-style order of words.
