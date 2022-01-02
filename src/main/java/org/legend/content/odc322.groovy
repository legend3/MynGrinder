import net.grinder.script.GTest
import net.grinder.scriptengine.groovy.junit.GrinderRunner
import net.grinder.scriptengine.groovy.junit.annotation.BeforeProcess
import net.grinder.scriptengine.groovy.junit.annotation.BeforeThread
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.ngrinder.http.HTTPRequest
import org.ngrinder.http.HTTPRequestControl
import org.ngrinder.http.HTTPResponse
import org.ngrinder.http.cookie.Cookie
import org.ngrinder.http.cookie.CookieManager

import static net.grinder.script.Grinder.grinder
import static org.hamcrest.Matchers.is
import static org.junit.Assert.assertThat

@RunWith(GrinderRunner)
class odc322 {
    public static GTest test;
    public static HTTPRequest request;
    public static Map<String, String> headers = ["Accept": "application/json",
                                                 "Accept-Encoding": "gzip, deflate",
                                                 "Accept-Language": "zh-CN,zh;q=0.9",
                                                 "Connection": "keep-alive",
                                                 "Content-Length": "94",
                                                 "Content-Type": "application/json;charset=UTF-8",
                                                 "Origin": "http://100.69.100.202:8969",
                                                 "Referer": "http://100.69.100.202:8969/index.html",
                                                 "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) odc/2.4.1 Chrome/85.0.4183.121 Electron/10.4.2 Safari/537.36"
    ]
    public static Map<String, Object> params = [
            "username": "admin",
            "password": "aaAA11__"
    ]
    public static List<Cookie> cookies = []
    public static String protocol = "http://"
    public static String host = "100.69.100.202:8969"

    @BeforeProcess
     static void beforeProcess() {
        HTTPRequestControl.setConnectionTimeout(300000);
        test = new GTest(1,"login");
        request = new HTTPRequest();
        grinder.logger.info("before process.");
    }

    @BeforeThread
     void beforeThread() {
        test.record(this, "test")
        grinder.statistics.delayReports = true
        grinder.logger.info("before thread.")
    }

    @Before
     void before() {
        request.setHeaders(headers)
        CookieManager.addCookie(cookies)
        grinder.logger.info("before. init headers and cookies")
        grinder.firstProcessNumber
    }

    /**
     * @Test注释的方法可能会重复执行。
     * 如下所示，您可以使用JUnit断言来断言测试结果。如果断言失败，则绑定到此线程的最后一个测试将被评估为失败。
     */
    @Test
    void test() {
        HTTPResponse response = request.POST(protocol + host + "/api/v2/iam/login?ignoreError=true", params, headers)
        if (response.statusCode == 301 || response.statusCode == 302) {
            grinder.logger.warn("Warning. The response may not be correct. The response code was {}.", response.statusCode)
        } else
            println response.getBodyText()
            def result = '{"data": "ok","durationMillis": null,"httpStatus": "OK","requestId": null,"server": null,"successful": true,"timestamp": null,"traceId": null}'
            Reader ctx = JsonPath.parse(result)
//            ctx.read()
        assertThat(response.statusCode, is(200))
        }
    }
