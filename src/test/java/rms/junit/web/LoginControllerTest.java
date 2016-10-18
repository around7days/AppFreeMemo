// package rms.junit.web;
//
// import static org.junit.Assert.*;
//
// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.InjectMocks;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
// import org.springframework.context.ApplyContext;
// import org.springframework.mock.web.MockHttpServletRequest;
// import org.springframework.mock.web.MockHttpServletResponse;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
// import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
//
// import rms.web.app.system.login.LoginController;
//
/// **
// * ログイン画面コントローラーテスト
// * @author
// */
// @RunWith(SpringRunner.class)
// @SpringBootTest
// public class LoginControllerTest {
//
// @Autowired
// protected ApplyContext context;
//
// protected MockHttpServletRequest request;
// protected MockHttpServletResponse response;
// protected RequestMappingHandlerAdapter adapter;
//
// @InjectMocks
// private LoginController controller;
//
// @Before
// public void setUp() {
// request = new MockHttpServletRequest();
// response = new MockHttpServletResponse();
// response.setOutputStreamAccessAllowed(true);
// adapter = new RequestMappingHandlerAdapter();
// }
//
// /**
// * {@link rms.web.login.LoginController#init(rms.web.login.LoginForm, org.springframework.ui.Model)} のためのテスト・メソッド。
// * @throws Exception
// */
// @Test
// public void testInit() throws Exception {
//
// this.mockMvc.perform(MockMvcRequestBuilders.post("/").param("name", ""))
// .andDo(MockMvcResultHandlers.print())
// .andExpect(MockMvcResultMatchers.status().isOk())
// .andExpect(MockMvcResultMatchers.model().hasErrors())
// .andExpect(MockMvcResultMatchers.view().name("index"))
// .andExpect(MockMvcResultMatchers.model().attribute("message", "こんにちは世界"));
// }
//
// /**
// * {@link rms.web.login.LoginController#login(rms.web.login.LoginForm, org.springframework.validation.BindingResult, org.springframework.ui.Model)} のためのテスト・メソッド。
// */
// @Test
// public void testLogin() {
// fail("まだ実装されていません");
// }
//
// /**
// * {@link rms.web.login.LoginController#loginError(rms.web.login.LoginForm, org.springframework.validation.BindingResult, org.springframework.ui.Model)} のためのテスト・メソッド。
// */
// @Test
// public void testLoginError() {
// fail("まだ実装されていません");
// }
//
// }
