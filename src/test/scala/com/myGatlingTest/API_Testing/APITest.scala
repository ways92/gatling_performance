package com.myGatlingTest.API_Testing

import io.gatling.core.Predef.{Simulation, _}
import io.gatling.http.Predef._

class APITest extends Simulation{

//  PROTOCOL
  val httpProtocol = http.baseUrl("https://reqres.in/api")

//  SCENARIO
  val GET_singleUser = scenario("get single user")
    .exec(
      http("get single user request")
        .get("/users/4")
        .check(status.is(200))
        .check(jsonPath("$.data.first_name").is("Eve"))
    )
    .pause(1)

  val GET_listUser = scenario("get list user")
    .exec(
      http("get list user request")
        .get("/users?page=2")
        .check(status.is(200))
        .check(jsonPath("$.data[1].last_name").is("Ferguson"))
    )
    .pause(1)

  val GET_singleUserNotFound = scenario("get single user not found")
    .exec(
    http("get single user not found request")
      .get("/users/24")
      .check(status.is(404))
    )
    .pause(1)

  val GET_listResource = scenario("get list resource")
    .exec(
      http("get list resource request")
        .get("/unknown")
        .check(status.is(200))
        .check(jsonPath("$.data[0].name").is("cerulean"))
    )
    .pause(1)

  val GET_singleResource = scenario("get single resource")
    .exec(
      http("get single resource request")
        .get("/unknown/3")
        .check(status.is(200))
        .check(jsonPath("$.data.name").is("true red"))
    )
    .pause(1)

  val GET_singleResourceNotFound = scenario("get single resource not found")
    .exec(
      http("get single resource not found request")
        .get("/unknown/23")
        .check(status.is(404))
    )
    .pause(1)

  val POST_createUser = scenario("post user")
    .exec(
      http("post user request")
        .post("/users")
        .header("content-type", "application/json").asJson
        .body(RawFileBody("data/createUser.json")).asJson
        .check(status.is(201))
        .check(jsonPath("$.name").is("Ways"))
    )

  val PUT_updateUser = scenario("put user")
    .exec(
      http("put user request")
        .put("/users/2")
        .header("content-type", "application/json").asJson
        .body(RawFileBody("data/putUser.json")).asJson
        .check(status.is(200))
        .check(jsonPath("$.name").is("Ways AL"))
    )

  val PATCH_updateUser = scenario("patch user")
    .exec(
      http("patch user request")
        .patch("/users/2")
        .header("content-type", "application/json").asJson
        .body(RawFileBody("data/patchUser.json")).asJson
        .check(status.is(200))
        .check(jsonPath("$.name").is("Alex Ferguso"))
    )

  val DELETE_user = scenario("delete user")
    .exec(
      http("delete user request")
        .delete("/users/2")
        .check(status.is(204))
    )

  val POST_registerSuccessful = scenario("post register successful")
    .exec(
      http("post register successful")
        .post("/register")
        .header("content-type", "application/json").asJson
        .body(RawFileBody("data/registerSuccessful.json")).asJson
        .check(status.is(200))
    )

  val POST_registerUnsuccessful = scenario("post register unsuccessful")
    .exec(
      http("post register unsuccessful")
        .post("/register")
        .header("content-type", "application/json").asJson
        .body(RawFileBody("data/registerUnsuccessful.json")).asJson
        .check(status.is(400))
    )

  val POST_loginSuccessful = scenario("post login successful")
    .exec(
      http("post login successful")
        .post("/login")
        .header("content-type", "application/json").asJson
        .body(RawFileBody("data/registerSuccessful.json")).asJson
        .check(status.is(200))
    )

  val POST_loginUnsuccessful = scenario("post login unsuccessful")
    .exec(
      http("post login unsuccessful")
        .post("/login")
        .header("content-type", "application/json").asJson
        .body(RawFileBody("data/registerUnsuccessful.json")).asJson
        .check(status.is(400))
    )

  val GET_delayedResponse = scenario("get delayed response")
    .exec(
      http("get delayed response")
        .get("/users?delay=3")
        .check(status.is(200))
    )

  //  SETUP
  setUp(
    GET_singleUser.inject(rampUsers(8).during(3)),
    GET_listUser.inject(rampUsers(8).during(3)),
    GET_singleUserNotFound.inject(rampUsers(8).during(3)),
    GET_listResource.inject(rampUsers(8).during(3)),
    GET_singleResource.inject(rampUsers(8).during(3)),
    GET_singleResourceNotFound.inject(rampUsers(8).during(3)),
    POST_createUser.inject(rampUsers(8).during(3)),
    PUT_updateUser.inject(rampUsers(8).during(3)),
    PATCH_updateUser.inject(rampUsers(8).during(3)),
    DELETE_user.inject(rampUsers(8).during(3)),
    POST_registerSuccessful.inject(rampUsers(8).during(3)),
    POST_registerUnsuccessful.inject(rampUsers(8).during(3)),
    POST_loginSuccessful.inject(rampUsers(8).during(3)),
    POST_loginUnsuccessful.inject(rampUsers(8).during(3)),
    GET_delayedResponse.inject(rampUsers(8).during(3))

  )
    .protocols(httpProtocol)

}

