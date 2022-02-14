package com.example.demo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static java.util.Arrays.asList;

@Controller
public class FileUploadController {
	@PostMapping("/")
	public ResponseEntity<Object> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		HashMap<String, Integer> hashMap = new HashMap<>();

		asList(new String(file.getBytes(), StandardCharsets.UTF_8)
				.toLowerCase()
				.replaceAll("[^a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ ]", "")
				.split(" "))
				.forEach(word -> {
					if (hashMap.containsKey(word)){
						hashMap.put(word, hashMap.get(word) + 1);
					} else {
						hashMap.put(word,1);
					}
				});

		Comparator<Entry<String, Integer>> valueComparator
				= (e1, e2) -> {
					Integer v1 = e1.getValue();
					Integer v2 = e2.getValue();
					return v1.compareTo(v2);
				};

		return new ResponseEntity<>(hashMap.entrySet().stream().sorted(valueComparator.reversed()).collect(Collectors.toList()), HttpStatus.OK);
	}

}
