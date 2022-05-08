package com.myGatlingTest

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class JavaAgro2 extends Simulation {

	val httpProtocol = http
		.baseUrl("https://staging.javaagroglobalindo.com")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("application/json, text/plain, */*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9,id;q=0.8,ru;q=0.7,de;q=0.6")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36")



	val scn = scenario("JavaAgro2")
		.exec(http("Home page")
			.options("/highlights")

			.resources(http("request_1")
			.options("/products")
			,
            http("request_2")
			.get("/products")
			,
            http("request_3")
			.get("/highlights")
			))
		.pause(1)
		.exec(http("Product")
			.get("/products")

			.resources(http("request_5")
			.get("/highlights")
			))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}