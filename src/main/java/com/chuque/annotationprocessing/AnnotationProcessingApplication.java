package com.chuque.annotationprocessing;

import com.chuque.annotationprocessing.annotation.TimedMillis;
import com.chuque.annotationprocessing.annotation.TimedSeconds;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Method;
import java.util.Set;

@SpringBootApplication
public class AnnotationProcessingApplication {

	public static void main(String[] args) {
		validateAnnotations();
		SpringApplication.run(AnnotationProcessingApplication.class, args);
	}

	private static void validateAnnotations() {
		Set<Method> annotatedMethods =
				new Reflections("com.chuque", new MethodAnnotationsScanner())
						.getMethodsAnnotatedWith(TimedMillis.class);

		annotatedMethods.stream()
				.filter(method -> method.getDeclaredAnnotationsByType(TimedSeconds.class).length > 0)
				.findFirst()
				.ifPresent(method -> {
					throw new IllegalStateException(
							String.format(
									"Invalid annotations for method %s. The same method cannot be annotated with %s and %s at the same time.",
									method,
									TimedMillis.class.getSimpleName(),
									TimedSeconds.class.getSimpleName()
							)
					);
				});
	}

}
