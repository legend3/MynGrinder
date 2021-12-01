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
    public static Map<String, String> headers = [:]
    public static Map<String, Object> params = [:]
    public static List<Cookie> cookies = []
    public static String protocol = "http://"
    public static String host = "100.69.100.202:8969"

    @BeforeProcess
    public static void beforeProcess() {
        HTTPRequestControl.setConnectionTimeout(300000);
        test = new GTest(1,"login");
        request = new HTTPRequest();
        grinder.logger.info("before process.");
    }

    @BeforeThread
    public void beforeThread() {
        test.record(this, "test")
        grinder.statistics.delayReports = true
        grinder.logger.info("before thread.")
    }

    @Before
    public void before() {
        request.setHeaders(headers)
        CookieManager.addCookies(cookies)
        grinder.logger.info("before. init headers and cookies")
    }

    /**
     * @Test注释的方法可能会重复执行。
     * 如下所示，您可以使用JUnit断言来断言测试结果。如果断言失败，则绑定到此线程的最后一个测试将被评估为失败。
     */
    @Test
    void test() {
        HTTPResponse response = request.POST(protocol + host + "/api/v2/iam/login?ignoreError=true", params)
        if (response.statusCode == 301 || response.statusCode == 302) {
            grinder.logger.warn("Warning. The response may not be correct. The response code was {}.", response.statusCode)
        } else {
            assertThat(response.statusCode, is(200))
        }
    }

}
