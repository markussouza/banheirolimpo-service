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
import com.inova.banheirolimpo.enums.Situacao;
import com.inova.banheirolimpo.model.Banheiro;
import com.inova.banheirolimpo.model.Cliente;
import com.inova.banheirolimpo.model.Empresa;
import com.inova.banheirolimpo.model.EscalaTrabalho;
import com.inova.banheirolimpo.model.Funcao;
import com.inova.banheirolimpo.model.Funcionario;
import com.inova.banheirolimpo.model.Usuario;
import com.inova.banheirolimpo.repository.BanheiroRepository;
import com.inova.banheirolimpo.repository.ClienteRepository;
import com.inova.banheirolimpo.repository.EmpresaRepository;
import com.inova.banheirolimpo.repository.EscalaTrabalhoRepository;
import com.inova.banheirolimpo.repository.FuncaoRepository;
import com.inova.banheirolimpo.repository.FuncionarioRepository;
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
	CommandLineRunner init(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
			EmpresaRepository empresaRepository, ClienteRepository clienteRepository,
			BanheiroRepository banheiroRepository, FuncaoRepository funcaoRepository,
			EscalaTrabalhoRepository escalaTrabalhoRepository, FuncionarioRepository funcionarioRepository) {
		return args -> {
			this.initUsuario(usuarioRepository, passwordEncoder);
			this.initEmpresa(empresaRepository);
			this.initCliente(clienteRepository, empresaRepository);
			this.initBanheiro(banheiroRepository, clienteRepository);
			this.initFuncao(funcaoRepository);
			this.initEscalaTrabalho(escalaTrabalhoRepository);
			this.initFuncionario(funcionarioRepository, escalaTrabalhoRepository, funcaoRepository, clienteRepository);
		};
	}
	
	private void initUsuario(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		Usuario usuario = new Usuario();
		usuario.setNomeUsuario("admin");
		usuario.setSenha(passwordEncoder.encode("123456"));
		usuario.setPerfil(ProfileEnum.ROLE_ADMINISTRADOR);
		
		Usuario find = usuarioRepository.findOne(1L);
		if (find == null) {
			usuarioRepository.save(usuario);
		}
	}
	
	private void initEmpresa(EmpresaRepository empresaRepository) {
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
	}
	
	private void initCliente(ClienteRepository clienteRepository, EmpresaRepository empresaRepository) {
		Empresa empresa = empresaRepository.findAll().get(0);
		Cliente cliente = new Cliente();
		cliente.setRazaoSocial("VITOR E LARA LIMPEZA ME");
		cliente.setNomeFantasia("VILA LIMPEZA");
		cliente.setCnpj("46.738.344/0001-01");
		cliente.setEndereco("QNN 30 ÁREA ESPECIAL G LOTE 480");
		cliente.setBairro("CEILÂNDIA SUL");
		cliente.setCidade("BRASÍLIA");
		cliente.setUf(Estados.DF);
		cliente.setCep("72220-307");
		cliente.setCentroDeCusto("LPI001");
		cliente.setSituacao(Situacao.ATIVO);
		cliente.setNomeWifi("VILA546");
		cliente.setEmpresa(empresa);
		
		Cliente find = clienteRepository.findOne(1L);
		if (find == null) {
			clienteRepository.save(cliente);
		}
	}
	
	private void initBanheiro(BanheiroRepository banheiroRepository, ClienteRepository clienteRepository) {
		Cliente cliente = clienteRepository.findAll().get(0);
		Banheiro banheiro = new Banheiro();
		banheiro.setNome("TANF");
		banheiro.setLocalizacao("TÉRREO ALA NORTE");
		banheiro.setQuantidadeBoxes(3);
		banheiro.setLimitadorLimpeza(20);
		banheiro.setCodigoSensor("123456");
		banheiro.setCliente(cliente);
		
		Banheiro find = banheiroRepository.findOne(1L);
		if (find == null) {
			banheiroRepository.save(banheiro);
		}
	}
	
	private void initFuncao(FuncaoRepository funcaoRepository) {
		Funcao funcao = new Funcao();
		funcao.setDescricao("ENCARREGADO");
		funcao.setSituacao(Situacao.ATIVO);
		
		Funcao find = funcaoRepository.findByDescricaoIgnoreCase("ENCARREGADO");
		if (find == null) {
			funcaoRepository.save(funcao);
		}
	}
	
	private void initEscalaTrabalho(EscalaTrabalhoRepository escalaTrabalhoRepository) {
		EscalaTrabalho escalaTrabalho = new EscalaTrabalho();
		escalaTrabalho.setDescricao("5x2");
		escalaTrabalho.setSituacao(Situacao.ATIVO);
		
		EscalaTrabalho find = escalaTrabalhoRepository.findOne(1L);
		if (find == null) {
			escalaTrabalhoRepository.save(escalaTrabalho);
		}
	}
	
	private void initFuncionario(FuncionarioRepository funcionarioRepository,
			EscalaTrabalhoRepository escalaTrabalhoRepository, FuncaoRepository funcaoRepository,
			ClienteRepository clienteRepository) {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("LUCIANO MAEDA");
		funcionario.setPrimeiroNome("LUCIANO");
		funcionario.setUltimoNome("MAEDA");
		funcionario.setMatricula("VLL001");
		funcionario.setTelegramChatId(462491517L);
		funcionario.setSituacao(Situacao.ATIVO);
		funcionario.setFuncao(funcaoRepository.findByDescricaoIgnoreCase("ENCARREGADO"));
		funcionario.setEscalaTrabalho(escalaTrabalhoRepository.findAll().get(0));
		funcionario.setCliente(clienteRepository.findAll().get(0));

		Funcionario find = funcionarioRepository.findOne(1L);
		if (find == null) {
			funcionarioRepository.save(funcionario);
		}
	}
	
}
