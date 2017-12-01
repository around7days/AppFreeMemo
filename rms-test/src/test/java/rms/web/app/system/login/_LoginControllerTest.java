package rms.web.app.system.login;

// package rms.test.junit.web;
//
// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.mock.web.MockHttpServletRequest;
// import org.springframework.mock.web.MockHttpServletResponse;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.test.web.ModelAndViewAssert;
// import org.springframework.web.servlet.ModelAndView;
// import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
//
// import rms.web.app.system.login.LoginController;
//
// @RunWith(SpringRunner.class)
// @SpringBootTest
// public class LoginControllerTest extends AbstractControllerTest {
//
// @Autowired
// public LoginController controller;
//
// protected MockHttpServletRequest request;
// protected MockHttpServletResponse response;
// protected RequestMappingHandlerAdapter adapter;
//
// @Before
// public void setUp() {
// request = new MockHttpServletRequest();
// response = new MockHttpServletResponse();
// response.setOutputStreamAccessAllowed(true);
// adapter = new RequestMappingHandlerAdapter();
// }
//
// @Test
// public void validate() throws Exception {
// request.setMethod("GET");
// request.setRequestURI("/login?validate");
// request.addParameter("userId", "user01");
// ModelAndView mv = adapter.handle(request, response, controller);
// ModelAndViewAssert.assertViewName(mv, "home");
// }
// }
