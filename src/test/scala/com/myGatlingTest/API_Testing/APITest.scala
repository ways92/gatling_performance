package com.myGatlingTest.API_Testing

import io.gatling.core.Predef.{Simulation, _}
import io.gatling.http.Predef._

class APITest extends Simulation{
//  protocol
  val httpProtocol = http.baseUrl("https://reqres.in/api")

//  scenario
  val GET_singleUser = scenario("get single user")
    .exec(
      http("get single user request")
        .get("/users/4")
        .check(status.is(200))
        .check(jsonPath("$.data.first_name").is("Eve"))
    )
    .pause(2)

  val GET_listUser = scenario("get list user")
    .exec(
      http("get list user request")
        .get("/users?page=2")
        .check(status.is(200))
        .check(jsonPath("$.data[1].last_name").is("Ferguson"))
    )
    .pause(2)
//  setup
  setUp(
    GET_singleUser.inject(rampUsers(10).during(5)),
    GET_listUser.inject(rampUsers(10).during(5))
  )
    .protocols(httpProtocol)

}

