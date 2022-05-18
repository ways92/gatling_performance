package com.myGatlingTest

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class filterAndEdit2 extends Simulation {

	val httpProtocol = http
		.baseUrl("https://computer-database.gatling.io")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9,id;q=0.8,ru;q=0.7,de;q=0.6")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36")





	val scn = scenario("filterAndEdit2")
		.exec(http("All product page")
			.get("/computers")
			)
//		.pause(3)
		.exec(http("Filter product")
			.get("/computers?f=12")
			)
		.pause(1)
		.exec(http("Select Product")
			.get("/computers/263")
			)
		.pause(5)
		.exec(http("Edit Product")
			.post("/computers/263")
			.formParam("name", "PDP-13")
			.formParam("introduced", "")
			.formParam("discontinued", "")
			.formParam("company", "10"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}