package com.example.nakayoshi.Application;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class NakayoshiApplication {

	public static void main(String[] args) throws IOException {
		FileInputStream serviceAccount = new FileInputStream(Paths.get("").toAbsolutePath() + "\\src\\config\\serviceAccountKey.json");

		FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
						.build();

		FirebaseApp.initializeApp(options);

		SpringApplication.run(NakayoshiApplication.class, args);
	}
}
