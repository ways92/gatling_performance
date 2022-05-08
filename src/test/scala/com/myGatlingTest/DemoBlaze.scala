package com.myGatlingTest

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class DemoBlaze extends Simulation {

	val httpProtocol = http
		.baseUrl("https://api.demoblaze.com")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())

	val headers_0 = Map(
		"accept" -> "*/*",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-US,en;q=0.9,id;q=0.8,ru;q=0.7,de;q=0.6",
		"access-control-request-headers" -> "content-type",
		"access-control-request-method" -> "POST",
		"origin" -> "https://www.demoblaze.com",
		"sec-fetch-dest" -> "empty",
		"sec-fetch-mode" -> "cors",
		"sec-fetch-site" -> "same-site",
		"user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36")

	val headers_1 = Map(
		"accept" -> "*/*",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-US,en;q=0.9,id;q=0.8,ru;q=0.7,de;q=0.6",
		"content-type" -> "application/json",
		"origin" -> "https://www.demoblaze.com",
		"sec-ch-ua" -> """ Not A;Brand";v="99", "Chromium";v="101", "Google Chrome";v="101""",
		"sec-ch-ua-mobile" -> "?0",
		"sec-ch-ua-platform" -> "Windows",
		"sec-fetch-dest" -> "empty",
		"sec-fetch-mode" -> "cors",
		"sec-fetch-site" -> "same-site",
		"user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36")

	val headers_2 = Map(
		"accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-US,en;q=0.9,id;q=0.8,ru;q=0.7,de;q=0.6",
		"cache-control" -> "max-age=0",
		"if-none-match" -> "TTo8pA",
		"sec-ch-ua" -> """ Not A;Brand";v="99", "Chromium";v="101", "Google Chrome";v="101""",
		"sec-ch-ua-mobile" -> "?0",
		"sec-ch-ua-platform" -> "Windows",
		"sec-fetch-dest" -> "document",
		"sec-fetch-mode" -> "navigate",
		"sec-fetch-site" -> "same-origin",
		"sec-fetch-user" -> "?1",
		"upgrade-insecure-requests" -> "1",
		"user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36")

	val headers_3 = Map(
		"accept" -> "application/json, text/javascript, */*; q=0.01",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-US,en;q=0.9,id;q=0.8,ru;q=0.7,de;q=0.6",
		"if-none-match" -> "TTo8pA",
		"sec-ch-ua" -> """ Not A;Brand";v="99", "Chromium";v="101", "Google Chrome";v="101""",
		"sec-ch-ua-mobile" -> "?0",
		"sec-ch-ua-platform" -> "Windows",
		"sec-fetch-dest" -> "empty",
		"sec-fetch-mode" -> "cors",
		"sec-fetch-site" -> "same-origin",
		"user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36",
		"x-requested-with" -> "XMLHttpRequest")

	val headers_8 = Map(
		"accept" -> "*/*",
		"accept-encoding" -> "gzip, deflate, br",
		"accept-language" -> "en-US,en;q=0.9,id;q=0.8,ru;q=0.7,de;q=0.6",
		"origin" -> "https://www.demoblaze.com",
		"sec-ch-ua" -> """ Not A;Brand";v="99", "Chromium";v="101", "Google Chrome";v="101""",
		"sec-ch-ua-mobile" -> "?0",
		"sec-ch-ua-platform" -> "Windows",
		"sec-fetch-dest" -> "empty",
		"sec-fetch-mode" -> "cors",
		"sec-fetch-site" -> "same-site",
		"user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36")

    val uri1 = "https://hls.demoblaze.com"
    val uri3 = "https://www.demoblaze.com"

	val scn = scenario("DemoBlaze")
		.exec(http("request_0")
			.options("/login")
			.headers(headers_0)
			.resources(http("request_1")
			.post("/login")
			.headers(headers_1)
			.body(RawFileBody("com/myGatlingTest/demoblaze/0001_request.json")),
            http("request_2")
			.get(uri3 + "/index.html")
			.headers(headers_2),
            http("request_3")
			.get(uri3 + "/config.json")
			.headers(headers_3),
            http("request_4")
			.get(uri1 + "/index.m3u8"),
            http("request_5")
			.get(uri1 + "/about_demo_hls_600k.m3u8"),
            http("request_6")
			.get(uri1 + "/about_demo_hls_600k00000.ts"),
            http("request_7")
			.options("/check")
			.headers(headers_0),
            http("request_8")
			.get("/entries")
			.headers(headers_8),
            http("request_9")
			.post("/check")
			.headers(headers_1)
			.body(RawFileBody("com/myGatlingTest/demoblaze/0009_request.json"))))
		.pause(5)
		.exec(http("request_10")
			.get(uri3 + "/index.html")
			.resources(http("request_11")
			.get(uri3 + "/config.json"),
            http("request_12")
			.get(uri1 + "/index.m3u8"),
            http("request_13")
			.get(uri1 + "/about_demo_hls_600k.m3u8"),
            http("request_14")
			.get(uri1 + "/about_demo_hls_600k00000.ts"),
            http("request_15")
			.get("/entries")
			.headers(headers_8)))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}