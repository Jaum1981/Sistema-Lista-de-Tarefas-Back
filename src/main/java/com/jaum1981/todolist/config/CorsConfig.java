package com.jaum1981.todolist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todas as rotas do backend
                .allowedOrigins(
                        "http://localhost:3000", // Para desenvolvimento local
                        "https://sistema-lista-de-tarefas-29mtkekho-jaum1981s-projects.vercel.app" // URL do frontend na Vercel
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowedHeaders("*") // Permite todos os cabeçalhos
                .allowCredentials(true); // Permite envio de cookies (se necessário)
    }
}
