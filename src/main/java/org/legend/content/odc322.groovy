import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.jsonpath.JsonPath
import com.jayway.jsonpath.ReadContext
import net.grinder.script.GTest
import net.grinder.scriptengine.groovy.junit.GrinderRunner
import net.grinder.scriptengine.groovy.junit.annotation.BeforeProcess
import net.grinder.scriptengine.groovy.junit.annotation.BeforeThread
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.legend.bean.Student
import org.ngrinder.http.HTTPRequest
import org.ngrinder.http.HTTPRequestControl
import org.ngrinder.http.HTTPResponse
import org.ngrinder.http.cookie.Cookie
import org.ngrinder.http.cookie.CookieManager

import java.nio.charset.Charset

import static net.grinder.script.Grinder.grinder
import static org.hamcrest.Matchers.is
import static org.junit.Assert.assertThat

@RunWith(GrinderRunner)
class odc322 {
    public static GTest test
    public static HTTPRequest request
    public static Map<String, String> headers_GET = ["Accept"         : "*/*",
                                                 "Accept-Encoding": "gzip, deflate",
                                                 "Accept-Language": "zh-CN,zh;q=0.9",
                                                 "Connection"     : "keep-alive",
//                                                 "Content-Length": "94",
//                                                 "Content-Type"   : "application/json;charset=UTF-8",
                                                 "Origin"         : "http://localhost:8989",
                                                 "Referer"        : "http://localhost:8989/index.html",
                                                 "User-Agent"     : "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) odc/2.4.1 Chrome/85.0.4183.121 Electron/10.4.2 Safari/537.36"
    ]
    public static Map<String, String> headers_POST = ["Accept": "application/json",
                                                 "Accept-Encoding": "gzip, deflate",
                                                 "Accept-Language": "zh-CN,zh;q=0.9",
                                                 "Connection": "keep-alive",
                                                 "Content-Length": "94",
                                                 "Content-Type": "application/json;charset=UTF-8",
                                                 "Origin": "http://localhost:8989",
                                                 "Referer": "http://localhost:8989/index.html",
                                                 "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) odc/2.4.1 Chrome/85.0.4183.121 Electron/10.4.2 Safari/537.36"
    ]
    public static Map<String, Object> params = [
            "username": "admin",
            "password": "aaAA11__"
    ]
    public static List<Cookie> cookies = []
    public static String protocol = "http://"
    public static String host = "localhost:8989"

    @BeforeProcess
    static void beforeProcess() {
        HTTPRequestControl.setConnectionTimeout(300000);
        test = new GTest(1,"ODCTest");
        request = new HTTPRequest();
        grinder.logger.info("before process.");
    }

    @BeforeThread
    void beforeThread() {
        test.record(this, "getList")
        grinder.statistics.delayReports = true
        grinder.logger.info("before thread.")
    }

    @Before
    void before() {
        request.setHeaders(headers_GET)
//        CookieManager.addCookie(cookies)
        grinder.logger.info("before. init headers and cookies")
        grinder.firstProcessNumber
    }

    /**
     * @Test???????????????????????????????????????
     * ??????????????????????????????JUnit?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     */
    @Test
    void login() {
        HTTPResponse response = request.POST(protocol + host + "/api/v2/iam/login?ignoreError=true", params, headers_POST)
        if (response.statusCode == 301 || response.statusCode == 302) {
            grinder.logger.warn("Warning. The response may not be correct. The response code was {}.", response.statusCode)
        } else
            println response.getBodyText()
        def result = '{"data": "ok","durationMillis": null,"httpStatus": "OK","requestId": null,"server": null,"successful": true,"timestamp": null,"traceId": null}'
        Reader ctx = JsonPath.parse(result)
//            ctx.read()
        assertThat(response.statusCode, is(200))
    }
    @Test
    void getList() {
        HTTPResponse response = request.GET(protocol + host + "/api/v1/session-label/list", headers_GET)
        if (response.statusCode == 301 || response.statusCode == 302) {
            grinder.logger.warn("Warning. The response may not be correct. The response code was {}.", response.statusCode)
        } else {
            ObjectMapper mapper = new ObjectMapper()
            ReadContext ctx = JsonPath.parse(response.getBodyText(Charset.forName("UTF-8")))
            println(ctx.json())
//            def result = '{"data": "ok","durationMillis": null,"httpStatus": "OK","requestId": null,"server": null,"successful": true,"timestamp": null,"traceId": null}'
//            ReadContext ctx = JsonPath.parse(result)
//            println(ctx.json())
//            ctx.read()
            assertThat(response.statusCode, is(200))
        }
    }
}