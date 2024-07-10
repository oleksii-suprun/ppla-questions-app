package com.osuprun.pplaquestionsapp.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.resources
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok


@Configuration
@EnableCaching
class ApplicationConfig {

    @Bean
    fun htmlRouter(@Value("classpath:static/index.html") html: Resource): RouterFunction<ServerResponse> {
        return route(GET("/")) { _ -> ok().contentType(MediaType.TEXT_HTML).bodyValue(html) }
    }

    @Bean
    fun resourcesRouter(): RouterFunction<ServerResponse> {
        return resources("/**", ClassPathResource("/static"))
    }
}
