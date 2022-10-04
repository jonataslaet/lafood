package com.github.jonataslaet.lafood.cadastro;

import java.util.HashMap;
import java.util.Map;
import org.testcontainers.containers.PostgreSQLContainer;
import io.quarkus.test.common.QuarkusTestResourceConfigurableLifecycleManager;

public class CadastroTestLifeCycleManager implements QuarkusTestResourceConfigurableLifecycleManager {
	public static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:12.2");

	@Override
	public Map<String, String> start() {
		POSTGRES.start();

		Map<String, String> propriedades = new HashMap<String, String>();
		
		propriedades.put("quarkus.datasource.url", POSTGRES.getJdbcUrl());
		propriedades.put("quarkus.datasource.username", "cadastro");
		propriedades.put("quarkus.datasource.password", "cadastro");
		return propriedades;
	}

	@Override
	public void stop() {
		if (POSTGRES != null && POSTGRES.isRunning()) {
			POSTGRES.stop();
		}
	}

}
