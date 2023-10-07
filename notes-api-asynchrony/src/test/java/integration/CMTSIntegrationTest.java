package integration;

import com.spectrum.notes.NotesApi;
import com.spectrum.notes.config.InfluxdbConfig;
import com.spectrum.notes.config.SecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {InfluxdbConfig.class, SecurityConfig.class, NotesApi.class})
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test-source")
public class CMTSIntegrationTest {

//    @LocalServerPort
//    private String port;

//    @Autowired
//    private WebApplicationContext wac;

//    private MockMvc mockMvc;

//    @Before
    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
//                .apply(SecurityMockMvcConfigurers.springSecurity())
//                .build();
    }

    @Test
    public void bogusTest() {

    }

//    @WithMockUser("P1000")
//    @Test
    public void returns200WithCMTSDataJsonBody() throws Exception {
//        MvcResult result = mockMvc.perform(get("http://localhost:" + port + "/api/cmts?id=DNVRCO28WAZ"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        assertThat(result.getResponse().getContentType()).containsSequence(MediaType.APPLICATION_JSON.getType());
    }

//    @WithMockUser("P1000")
//    @Test
    public void returns200WithCmtsCableModemFiveMinJsonBody() throws Exception {
//        MvcResult result = mockMvc.perform(get("http://localhost:" + port + "/api/cmts/cm5?id=ATLNGAQSDS0"))
//                .andExpect(status().isOk())
//                .andReturn();
//        assertThat(result.getResponse().getContentType()).containsSequence(MediaType.APPLICATION_JSON.getType());
    }

//    @WithMockUser("P1000")
//    @Test
    public void returns200WithCmtsCableModemDailyJsonBody() throws Exception {
//        MvcResult result = mockMvc.perform(get("http://localhost:" + port + "/api/cmts/cmDaily?id=ATLNGAQSDS0"))
//                .andExpect(status().isOk())
//                .andReturn();
//        assertThat(result.getResponse().getContentType()).containsSequence(MediaType.APPLICATION_JSON.getType());
    }

//    @WithMockUser("P1000")
//    @Test
    public void returns200WithCmtsCableModemJsonBody() throws Exception {
//        MvcResult result = mockMvc.perform(get("http://localhost:" + port + "/api/cmts/cm?id=ATLNGAQSDS0"))
//                .andExpect(status().isOk())
//                .andReturn();
//        assertThat(result.getResponse().getContentType()).containsSequence(MediaType.APPLICATION_JSON.getType());
    }
}
