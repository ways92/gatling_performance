package com.myGatlingTest

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class pickAndDelete extends Simulation {

	val httpProtocol = http
		.baseUrl("https://computer-database.gatling.io")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9,id;q=0.8,ru;q=0.7,de;q=0.6")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36")


	val getAllData = exec(http("request_0")
		.get("/computers")
	)
		.pause(2)

	val pickData = exec(http("request_1")
		.get("/computers/381")
	)
		.pause(2)

	val deleteData = exec(http("request_2")
		.post("/computers/381/delete")
	)

	val scn = scenario("pickAndDelete").exec(getAllData, pickData, deleteData)
//		.exec(http("request_0")
//			.get("/computers")
//			)
//		.pause(2)


//		.exec(http("request_1")
//			.get("/computers/381")
//			)
//		.pause(2)


//		.exec(http("request_2")
//			.post("/computers/381/delete")
//			)

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}