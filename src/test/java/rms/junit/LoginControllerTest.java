//package rms.junit;
//
//import static org.junit.Assert.*;
//
//import rms.Apply;
//import rms.web.login.LoginController;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplyContext;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//
///**
// * ログイン画面コントローラーテスト
// * @author
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = Apply.class)
//public class LoginControllerTest {
//
//    @Autowired
//    protected ApplyContext context;
//
//    protected MockHttpServletRequest request;
//    protected MockHttpServletResponse response;
//    protected RequestMappingHandlerAdapter adapter;
//
//    @InjectMocks
//    private LoginController controller;
//
//    @Before
//    public void setUp() {
//        request = new MockHttpServletRequest();
//        response = new MockHttpServletResponse();
//        response.setOutputStreamAccessAllowed(true);
//        adapter = new RequestMappingHandlerAdapter();
//    }
//
//    /**
//     * {@link rms.web.login.LoginController#init(rms.web.login.LoginForm, org.springframework.ui.Model)} のためのテスト・メソッド。
//     * @throws Exception
//     */
//    @Test
//    public void testInit() throws Exception {
//
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/").param("name", ""))
//                    .andDo(MockMvcResultHandlers.print())
//                    .andExpect(MockMvcResultMatchers.status().isOk())
//                    .andExpect(MockMvcResultMatchers.model().hasErrors())
//                    .andExpect(MockMvcResultMatchers.view().name("index"))
//                    .andExpect(MockMvcResultMatchers.model().attribute("message", "こんにちは世界"));
//    }
//
//    /**
//     * {@link rms.web.login.LoginController#login(rms.web.login.LoginForm, org.springframework.validation.BindingResult, org.springframework.ui.Model)} のためのテスト・メソッド。
//     */
//    @Test
//    public void testLogin() {
//        fail("まだ実装されていません");
//    }
//
//    /**
//     * {@link rms.web.login.LoginController#loginError(rms.web.login.LoginForm, org.springframework.validation.BindingResult, org.springframework.ui.Model)} のためのテスト・メソッド。
//     */
//    @Test
//    public void testLoginError() {
//        fail("まだ実装されていません");
//    }
//
//}
