package com.inova.banheirolimpo;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import com.inova.banheirolimpo.enums.Estados;
import com.inova.banheirolimpo.enums.ProfileEnum;
import com.inova.banheirolimpo.model.Empresa;
import com.inova.banheirolimpo.model.Usuario;
import com.inova.banheirolimpo.repository.EmpresaRepository;
import com.inova.banheirolimpo.repository.UsuarioRepository;

@SpringBootApplication
public class BanheirolimpoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanheirolimpoApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   return builder.build();
	}
	
	@Bean
	CommandLineRunner initUsuario(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			Usuario usuario = new Usuario();
			usuario.setNomeUsuario("admin");
			usuario.setSenha(passwordEncoder.encode("123456"));
			usuario.setPerfil(ProfileEnum.ROLE_ADMINISTRADOR);
			
			Usuario find = usuarioRepository.findOne(1L);
			if (find == null) {
				usuarioRepository.save(usuario);
			}
		};
	}
	
	@Bean
	CommandLineRunner initEmpresa(EmpresaRepository empresaRepository) {
		return args -> {
			Empresa empresa = new Empresa();
			empresa.setRazaoSocial("MARKUS DO C SOUZA ME");
			empresa.setNomeFantasia("MKS TECNOLOGIA");
			empresa.setCnpj("20.828.917/0001-96");
			empresa.setEndereco("QUADRA 101 LT 6 BL B AP 810");
			empresa.setBairro("ÁGUAS CLARAS");
			empresa.setCidade("BRASÍLIA");
			empresa.setUf(Estados.DF);
			empresa.setCep("71907-180");
			
			Empresa find = empresaRepository.findOne(1L);
			if (find == null) {
				empresaRepository.save(empresa);
			}
		};
	}
	
}
