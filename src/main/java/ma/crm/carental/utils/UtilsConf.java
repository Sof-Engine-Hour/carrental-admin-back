package ma.crm.carental.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;

@Configuration
public class UtilsConf {
    


    @Bean
    public OkHttpClient okHttpClient(){
        return new OkHttpClient().newBuilder()
                                 .retryOnConnectionFailure(true)
                                 .build() ;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper() ;
    }


    @Bean
    public JsonConverter jsonConverter(@Autowired ObjectMapper objectMapper) {
        return new JsonConverter(objectMapper);
    }
}
